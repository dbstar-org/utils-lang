package io.github.dbstarll.utils.lang.wrapper;

import java.util.Iterator;

public abstract class IteratorWrapper<I, O> implements Iterator<O> {
    /**
     * wrapped Iterator.
     */
    private final Iterator<I> iterator;

    protected IteratorWrapper(final Iterator<I> iterator) {
        this.iterator = iterator;
    }

    @Override
    public final boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public final O next() {
        return next(iterator.next());
    }

    protected abstract O next(I value);

    @Override
    public final void remove() {
        iterator.remove();
    }
}
