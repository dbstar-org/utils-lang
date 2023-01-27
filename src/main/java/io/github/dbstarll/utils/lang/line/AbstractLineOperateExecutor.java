package io.github.dbstarll.utils.lang.line;

import io.github.dbstarll.utils.lang.wrapper.EntryWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 行处理器执行接口，提供处理结果统计功能.
 *
 * @param <E> 行处理返回的类型
 * @author dbstar
 */
public abstract class AbstractLineOperateExecutor<E extends Comparable<E>> implements Iterable<Entry<E, Long>> {
    /**
     * map of counters.
     */
    private final ConcurrentMap<E, AtomicLong> counters = new ConcurrentHashMap<>();

    /**
     * logger for report.
     */
    private final Logger logger = LoggerFactory.getLogger(getClass());

    protected final Logger getLogger() {
        return logger;
    }

    /**
     * 开始处理多行数据.
     *
     * @param lines 所有待处理的行
     * @throws InterruptedException if interrupted while waiting shutdown
     */
    public final void operate(final Iterable<String> lines) throws InterruptedException {
        final long startTime = System.currentTimeMillis();
        final int count = operate(lines, startTime);
        shutdown();
        report(count, startTime, System.currentTimeMillis(), false);
    }

    protected abstract int operate(Iterable<String> lines, long startTime);

    protected final void countResult(final E result) {
        counters.computeIfAbsent(result, e -> new AtomicLong(0)).incrementAndGet();
    }

    /**
     * 显示报告.
     *
     * @param count     处理总行数
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param debug     报告级别
     */
    protected void report(final int count, final long startTime, final long endTime, final boolean debug) {
        if (debug) {
            logger.debug("process: {}, cost: {}, counters: {}", count, endTime - startTime, counters);
        } else {
            logger.info("process: {}, cost: {}, counters: {}", count, endTime - startTime, counters);
        }
    }

    @Override
    public final Iterator<Entry<E, Long>> iterator() {
        Collection<Entry<E, Long>> sort = new TreeSet<>((o1, o2) -> {
            int result = o2.getValue().compareTo(o1.getValue());
            if (result == 0) {
                result = o1.getKey().compareTo(o2.getKey());
            }
            return result;
        });
        for (Entry<E, AtomicLong> entry : counters.entrySet()) {
            sort.add(EntryWrapper.wrap(entry.getKey(), entry.getValue().get()));
        }
        return sort.iterator();
    }

    protected void shutdown() throws InterruptedException {
    }
}
