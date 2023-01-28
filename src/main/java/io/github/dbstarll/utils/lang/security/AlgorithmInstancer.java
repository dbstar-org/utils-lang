package io.github.dbstarll.utils.lang.security;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class AlgorithmInstancer<T, A extends Enum<?>> implements Instancer<T> {
    private final A algorithm;
    private final String provider;

    /**
     * @param algorithm 算法
     * @param provider  算法提供者
     */
    public AlgorithmInstancer(final A algorithm, final String provider) {
        this.algorithm = algorithm;
        this.provider = provider;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T getInstance(final Class<T> typeClass) throws InstanceException {
        final Method method;
        try {
            if (StringUtils.isBlank(provider)) {
                method = typeClass.getMethod("getInstance", String.class);
            } else {
                method = typeClass.getMethod("getInstance", String.class, String.class);
            }
        } catch (NoSuchMethodException e) {
            throw new InstanceException("getInstance failed for: " + typeClass.getName(), e);
        }
        try {
            if (StringUtils.isBlank(provider)) {
                return (T) method.invoke(null, algorithm.toString());
            } else {
                return (T) method.invoke(null, algorithm.toString(), provider);
            }
        } catch (InvocationTargetException e) {
            throw new InstanceException("getInstance failed for: " + typeClass.getName(), e.getCause());
        } catch (IllegalAccessException e) {
            throw new InstanceException("getInstance failed for: " + typeClass.getName(), e);
        }
    }
}
