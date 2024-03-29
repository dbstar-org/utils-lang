package io.github.dbstarll.utils.lang.line;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MultiThreadLineOperateExecutor<E extends Comparable<E>> extends LineOperateExecutor<E> {
    private final ExecutorService executor;

    /**
     * 构建一个多线程行处理器.
     *
     * @param operator 单行处理器
     * @param thread   并发线程数量
     * @param capacity 队列容量
     */
    protected MultiThreadLineOperateExecutor(final LineOperator<E> operator, final int thread, final int capacity) {
        super(operator);
        this.executor = new ThreadPoolExecutor(thread, thread, 1, TimeUnit.MINUTES,
                new LinkedBlockingQueue<>(capacity));
    }

    /**
     * 构造MultiThreadLineOperateExecutor.
     *
     * @param operator 单行处理器
     * @param thread   并发线程数量
     * @param capacity 队列容量
     * @param <E>      单行处理器的返回类型
     * @return MultiThreadLineOperateExecutor
     */
    public static <E extends Comparable<E>> MultiThreadLineOperateExecutor<E> build(
            final LineOperator<E> operator,
            final int thread,
            final int capacity) {
        return new MultiThreadLineOperateExecutor<>(operator, thread, capacity);
    }

    @Override
    protected void operate(final String line) {
        Runnable task = () -> MultiThreadLineOperateExecutor.super.operate(line);
        try {
            executor.submit(task);
        } catch (Exception ex) {
            super.operate(line);
        }
    }

    @Override
    protected void shutdown() throws InterruptedException {
        this.executor.shutdown();
        this.executor.awaitTermination(1, TimeUnit.HOURS);
        super.shutdown();
    }
}
