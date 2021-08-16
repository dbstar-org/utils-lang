package io.github.dbstarll.utils.lang.security;

public interface SecurityBuilder<T> {
    /**
     * 构造指定类型的实例.
     *
     * @return 指定类型的实例
     */
    T build();
}
