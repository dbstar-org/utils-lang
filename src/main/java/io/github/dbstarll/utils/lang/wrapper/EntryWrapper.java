package io.github.dbstarll.utils.lang.wrapper;

import java.util.Map.Entry;

public class EntryWrapper<K, V> implements Entry<K, V> {
    private final K key;
    private final V value;
    private final int hash;

    public static final <K, V> EntryWrapper<K, V> wrap(K key, V value) {
        return new EntryWrapper<K, V>(key, value);
    }

    protected EntryWrapper(K key, V value) {
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
    public final V setValue(V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public final int hashCode() {
        return hash;
    }

    @Override
    public final boolean equals(Object obj) {
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

    private static int getHashCode(Object key, Object value) {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((key == null) ? 0 : key.hashCode());
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return key + "=" + value;
    }
}
