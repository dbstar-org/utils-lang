package io.github.dbstarll.utils.lang.security;

import org.apache.commons.lang3.StringUtils;

public final class AlgorithmInstancer<T, A extends Enum<?>> implements Instancer<T> {
    private final A algorithm;
    private final String provider;

    /**
     * @param algorithm
     * @param provider
     */
    public AlgorithmInstancer(final A algorithm, final String provider) {
        this.algorithm = algorithm;
        this.provider = provider;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T getInstance(final Class<T> typeClass) throws Exception {
        if (StringUtils.isBlank(provider)) {
            return (T) typeClass.getMethod("getInstance", String.class).invoke(null, algorithm.toString());
        } else {
            return (T) typeClass.getMethod("getInstance", String.class, String.class).invoke(null, algorithm.toString(),
                    provider);
        }
    }
}
