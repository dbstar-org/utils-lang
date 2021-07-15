package io.github.dbstarll.utils.lang.line;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MultiThreadBatchLineOperateExecutor<E extends Comparable<E>> extends BatchLineOperateExecutor<E> {
    private final ExecutorService executor;

    protected MultiThreadBatchLineOperateExecutor(BatchLineOperator<E> operator, int batch, int thread, int capacity) {
        super(operator, batch);
        this.executor = new ThreadPoolExecutor(thread, thread, 1, TimeUnit.MINUTES,
                new LinkedBlockingQueue<Runnable>(capacity));
    }

    /**
     * 构建一个多线程行批处理器.
     *
     * @param operator 行批处理器
     * @param batch    批处理规模
     * @param thread   并发线程数量
     * @param capacity the capacity of this queue
     */
    public static <E extends Comparable<E>> MultiThreadBatchLineOperateExecutor<E> build(BatchLineOperator<E> operator,
                                                                                         int batch, int thread, int capacity) {
        return new MultiThreadBatchLineOperateExecutor<E>(operator, batch, thread, capacity);
    }

    @Override
    protected void operate(String... lines) {
        final String[] batch = Arrays.copyOf(lines, lines.length);
        Runnable task = new Runnable() {
            @Override
            public void run() {
                MultiThreadBatchLineOperateExecutor.super.operate(batch);
            }
        };
        try {
            executor.submit(task);
        } catch (Throwable ex) {
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
