package io.github.dbstarll.utils.lang.line;

import io.github.dbstarll.utils.lang.wrapper.EntryWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Comparator;
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
    private final ConcurrentMap<E, AtomicLong> counters = new ConcurrentHashMap<E, AtomicLong>();

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 开始处理多行数据.
     *
     * @param lines 所有待处理的行
     * @throws InterruptedException if interrupted while waiting shutdown
     */
    public final void operate(Iterable<String> lines) throws InterruptedException {
        final long startTime = System.currentTimeMillis();
        final int count = operate(lines, startTime);
        shutdown();
        report(count, startTime, System.currentTimeMillis(), false);
    }

    protected abstract int operate(Iterable<String> lines, long startTime);

    protected final void countResult(E result) {
        AtomicLong counter = counters.get(result);
        if (counter == null) {
            AtomicLong oldCounter = counters.putIfAbsent(result, counter = new AtomicLong(0));
            if (oldCounter != null) {
                counter = oldCounter;
            }
        }
        counter.incrementAndGet();
    }

    protected void report(int count, long startTime, long endTime, boolean debug) {
        if (debug) {
            logger.debug("process: {}, cost: {}, counters: {}", count, endTime - startTime, counters);
        } else {
            logger.info("process: {}, cost: {}, counters: {}", count, endTime - startTime, counters);
        }
    }

    @Override
    public final Iterator<Entry<E, Long>> iterator() {
        Collection<Entry<E, Long>> sort = new TreeSet<Entry<E, Long>>(new Comparator<Entry<E, Long>>() {
            @Override
            public int compare(Entry<E, Long> o1, Entry<E, Long> o2) {
                int result = o2.getValue().compareTo(o1.getValue());
                if (result == 0) {
                    result = o1.getKey().compareTo(o2.getKey());
                }
                return result;
            }
        });
        for (Entry<E, AtomicLong> entry : counters.entrySet()) {
            sort.add(EntryWrapper.wrap(entry.getKey(), entry.getValue().get()));
        }
        return sort.iterator();
    }

    protected void shutdown() throws InterruptedException {
    }
}
