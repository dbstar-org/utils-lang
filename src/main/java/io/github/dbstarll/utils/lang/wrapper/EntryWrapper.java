package io.github.dbstarll.utils.lang.wrapper;

import java.util.Map.Entry;

public class EntryWrapper<K, V> implements Entry<K, V> {
    /**
     * key of the entry.
     */
    private final K key;

    /**
     * value of the entry.
     */
    private final V value;

    /**
     * hash of the entry.
     */
    private final int hash;

    /**
     * create a wrapped entry.
     *
     * @param key   key of the entry
     * @param value value of the entry
     * @param <K>   type of key
     * @param <V>   type of value
     * @return the wrapped entry
     */
    public static final <K, V> EntryWrapper<K, V> wrap(final K key, final V value) {
        return new EntryWrapper<K, V>(key, value);
    }

    protected EntryWrapper(final K key, final V value) {
        this.key = key;
        this.value = value;
        this.hash = getHashCode(key, value);
    }

    @Override
    public final K getKey() {
        return key;
    }

    @Override
    public final V getValue() {
        return value;
    }

    @Override
    public final V setValue(final V newValue) {
        throw new UnsupportedOperationException();
    }

    @Override
    public final int hashCode() {
        return hash;
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (!Entry.class.isInstance(obj)) {
            return false;
        }

        @SuppressWarnings("rawtypes") Entry other = (Entry) obj;
        if (key == null) {
            if (other.getKey() != null) {
                return false;
            }
        } else if (!key.equals(other.getKey())) {
            return false;
        }
        if (value == null) {
            if (other.getValue() != null) {
                return false;
            }
        } else if (!value.equals(other.getValue())) {
            return false;
        }
        return true;
    }

    private static int getHashCode(final Object key, final Object value) {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((key == null) ? 0 : key.hashCode());
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        return result;
    }

    /**
     * @return toString.
     */
    @Override
    public String toString() {
        return key + "=" + value;
    }
}
