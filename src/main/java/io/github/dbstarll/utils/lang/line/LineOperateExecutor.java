package io.github.dbstarll.utils.lang.line;

/**
 * 行处理器执行接口.
 *
 * @param <E> 行处理返回的类型
 * @author dbstar
 */
public class LineOperateExecutor<E extends Comparable<E>> extends AbstractLineOperateExecutor<E> {
    private static final int REPORT_LINES = 10000;

    private final LineOperator<E> operator;

    protected LineOperateExecutor(final LineOperator<E> operator) {
        this.operator = operator;
    }

    /**
     * 构造一个LineOperateExecutor.
     *
     * @param operator 行处理器
     * @param <E>      行处理返回的类型
     * @return LineOperateExecutor
     */
    public static <E extends Comparable<E>> LineOperateExecutor<E> build(final LineOperator<E> operator) {
        return new LineOperateExecutor<E>(operator);
    }

    @Override
    protected final int operate(final Iterable<String> lines, final long startTime) {
        int count = 0;
        long lastTime = startTime;
        for (String line : lines) {
            operate(line);
            if (++count % REPORT_LINES == 0) {
                final long now = System.currentTimeMillis();
                report(count, lastTime, now, true);
                lastTime = now;
            }
        }
        return count;
    }

    /**
     * 处理单行数据.
     *
     * @param line 单行数据
     */
    protected void operate(final String line) {
        try {
            countResult(operator.operate(line));
        } catch (RuntimeException ex) {
            getLogger().warn("operate failed for line: " + line, ex);
        }
    }
}
