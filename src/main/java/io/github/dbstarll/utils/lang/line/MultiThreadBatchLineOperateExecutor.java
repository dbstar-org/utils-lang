package io.github.dbstarll.utils.lang.line;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MultiThreadBatchLineOperateExecutor<E extends Comparable<E>> extends BatchLineOperateExecutor<E> {
    private final ExecutorService executor;

    protected MultiThreadBatchLineOperateExecutor(final BatchLineOperator<E> operator,
                                                  final int batch,
                                                  final int thread,
                                                  final int capacity) {
        super(operator, batch);
        this.executor = new ThreadPoolExecutor(thread, thread, 1, TimeUnit.MINUTES,
                new LinkedBlockingQueue<>(capacity));
    }

    /**
     * 构建一个多线程行批处理器.
     *
     * @param <E>      class of lineOperator's result
     * @param operator 行批处理器
     * @param batch    批处理规模
     * @param thread   并发线程数量
     * @param capacity the capacity of this queue
     * @return MultiThreadBatchLineOperateExecutor
     */
    public static <E extends Comparable<E>> MultiThreadBatchLineOperateExecutor<E> build(
            final BatchLineOperator<E> operator,
            final int batch,
            final int thread,
            final int capacity) {
        return new MultiThreadBatchLineOperateExecutor<>(operator, batch, thread, capacity);
    }

    @Override
    protected void operate(final String... lines) {
        final String[] batch = Arrays.copyOf(lines, lines.length);
        Runnable task = () -> MultiThreadBatchLineOperateExecutor.super.operate(batch);
        try {
            executor.submit(task);
        } catch (Exception ex) {
            super.operate(batch);
        }
    }

    @Override
    protected void shutdown() throws InterruptedException {
        this.executor.shutdown();
        this.executor.awaitTermination(1, TimeUnit.HOURS);
        super.shutdown();
    }
}
