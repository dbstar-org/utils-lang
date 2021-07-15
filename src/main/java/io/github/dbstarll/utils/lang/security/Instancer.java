package io.github.dbstarll.utils.lang.security;

public interface Instancer<T> {
    T getInstance(Class<T> typeClass) throws Exception;
}
