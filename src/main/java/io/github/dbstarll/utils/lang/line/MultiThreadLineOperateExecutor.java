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
     */
    protected MultiThreadLineOperateExecutor(LineOperator<E> operator, int thread, int capacity) {
        super(operator);
        this.executor = new ThreadPoolExecutor(thread, thread, 1, TimeUnit.MINUTES,
                new LinkedBlockingQueue<Runnable>(capacity));
    }

    public static <E extends Comparable<E>> MultiThreadLineOperateExecutor<E> build(LineOperator<E> operator, int thread,
                                                                                    int capacity) {
        return new MultiThreadLineOperateExecutor<E>(operator, thread, capacity);
    }

    @Override
    protected void operate(final String line) {
        Runnable task = new Runnable() {
            @Override
            public void run() {
                MultiThreadLineOperateExecutor.super.operate(line);
            }
        };
        try {
            executor.submit(task);
        } catch (Throwable ex) {
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
