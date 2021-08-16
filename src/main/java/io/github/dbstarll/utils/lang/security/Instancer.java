package io.github.dbstarll.utils.lang.security;

public interface Instancer<T> {
    /**
     * 创建指定类的实例.
     *
     * @param typeClass 待实例的类
     * @return 指定类的实例
     * @throws Exception 创建实例异常
     */
    T getInstance(Class<T> typeClass) throws Exception;
}
