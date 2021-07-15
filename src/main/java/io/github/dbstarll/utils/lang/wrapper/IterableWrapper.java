package io.github.dbstarll.utils.lang.wrapper;

import java.util.Iterator;

public class IterableWrapper<E> implements Iterable<E> {
    private final Iterator<E> iterator;

    public static final <E> IterableWrapper<E> wrap(Iterator<E> iterator) {
        return new IterableWrapper<E>(iterator);
    }

    protected IterableWrapper(Iterator<E> iterator) {
        this.iterator = iterator;
    }

    @Override
    public final Iterator<E> iterator() {
        return iterator;
    }
}
