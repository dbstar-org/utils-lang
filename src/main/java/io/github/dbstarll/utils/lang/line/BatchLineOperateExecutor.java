package io.github.dbstarll.utils.lang.line;

import java.util.Arrays;

/**
 * 行批处理器执行接口.
 *
 * @param <E> 行处理返回的类型
 * @author dbstar
 */
public class BatchLineOperateExecutor<E extends Comparable<E>> extends AbstractLineOperateExecutor<E> {
    private final BatchLineOperator<E> operator;
    private final int batch;

    protected BatchLineOperateExecutor(BatchLineOperator<E> operator, int batch) {
        this.operator = operator;
        this.batch = batch;
    }

    public static <E extends Comparable<E>> BatchLineOperateExecutor<E> build(BatchLineOperator<E> operator, int batch) {
        return new BatchLineOperateExecutor<E>(operator, batch);
    }

    @Override
    protected final int operate(Iterable<String> lines, long startTime) {
        int count = 0;
        final String[] ls = new String[batch];
        for (String line : lines) {
            ls[count++ % batch] = line;
            if (count % batch == 0) {
                operate(ls);
            }
            if (count % 10000 == 0) {
                report(count, startTime, startTime = System.currentTimeMillis(), true);
            }
        }
        if (count % batch > 0) {
            operate(Arrays.copyOf(ls, count % batch));
        }
        return count;
    }

    protected void operate(String... lines) {
        try {
            for (E result : operator.operate(lines)) {
                countResult(result);
            }
        } catch (Throwable ex) {
            logger.warn("operate failed for lines: " + Arrays.toString(lines), ex);
        }
    }
}
