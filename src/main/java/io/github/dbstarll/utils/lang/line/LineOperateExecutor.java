package io.github.dbstarll.utils.lang.line;

/**
 * 行处理器执行接口.
 *
 * @param <E> 行处理返回的类型
 * @author dbstar
 */
public class LineOperateExecutor<E extends Comparable<E>> extends AbstractLineOperateExecutor<E> {
    private final LineOperator<E> operator;

    protected LineOperateExecutor(LineOperator<E> operator) {
        this.operator = operator;
    }

    public static <E extends Comparable<E>> LineOperateExecutor<E> build(LineOperator<E> operator) {
        return new LineOperateExecutor<E>(operator);
    }

    @Override
    protected final int operate(final Iterable<String> lines, long startTime) {
        int count = 0;
        for (String line : lines) {
            operate(line);
            if (++count % 10000 == 0) {
                report(count, startTime, startTime = System.currentTimeMillis(), true);
            }
        }
        return count;
    }

    protected void operate(String line) {
        try {
            countResult(operator.operate(line));
        } catch (Throwable ex) {
            logger.warn("operate failed for line: " + line, ex);
        }
    }
}
