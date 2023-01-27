package io.github.dbstarll.utils.lang.wrapper;

import java.util.Iterator;

public class IterableWrapper<E> implements Iterable<E> {
    /**
     * wrapped iterator.
     */
    private final Iterator<E> iterator;

    /**
     * create a wrapped Iterable.
     *
     * @param iterator wrapped iterator
     * @param <E>      the type of elements returned by this iterator
     * @return the wrapped Iterable
     */
    public static final <E> IterableWrapper<E> wrap(final Iterator<E> iterator) {
        return new IterableWrapper<>(iterator);
    }

    protected IterableWrapper(final Iterator<E> iterator) {
        this.iterator = iterator;
    }

    @Override
    public final Iterator<E> iterator() {
        return iterator;
    }
}
