package io.github.dbstarll.utils.lang.line;

import java.util.Arrays;

/**
 * 行批处理器执行接口.
 *
 * @param <E> 行处理返回的类型
 * @author dbstar
 */
public class BatchLineOperateExecutor<E extends Comparable<E>> extends AbstractLineOperateExecutor<E> {
    private static final int BATCH_LINES = 10000;
    private final BatchLineOperator<E> operator;
    private final int batch;

    protected BatchLineOperateExecutor(final BatchLineOperator<E> operator, final int batch) {
        this.operator = operator;
        this.batch = batch;
    }

    /**
     * 构造一个BatchLineOperateExecutor.
     *
     * @param operator 行批处理器
     * @param batch    一次处理行数
     * @param <E>      type of LineOperator
     * @return BatchLineOperateExecutor
     */
    public static <E extends Comparable<E>> BatchLineOperateExecutor<E> build(
            final BatchLineOperator<E> operator, final int batch) {
        return new BatchLineOperateExecutor<E>(operator, batch);
    }

    @Override
    protected final int operate(final Iterable<String> lines, final long startTime) {
        int count = 0;
        long lastTime = startTime;
        final String[] ls = new String[batch];
        for (String line : lines) {
            ls[count++ % batch] = line;
            if (count % batch == 0) {
                operate(ls);
            }
            if (count % BATCH_LINES == 0) {
                final long now = System.currentTimeMillis();
                report(count, lastTime, now, true);
                lastTime = now;
            }
        }
        if (count % batch > 0) {
            operate(Arrays.copyOf(ls, count % batch));
        }
        return count;
    }

    /**
     * 批量处理多行数据.
     *
     * @param lines 多行数据
     */
    protected void operate(final String... lines) {
        try {
            for (E result : operator.operate(lines)) {
                countResult(result);
            }
        } catch (RuntimeException ex) {
            getLogger().warn("operate failed for lines: " + Arrays.toString(lines), ex);
        }
    }
}
