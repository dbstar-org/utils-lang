package io.github.dbstarll.utils.lang.security;

import org.apache.commons.lang3.StringUtils;

public class AlgorithmInstancer<T, A extends Enum<?>> implements Instancer<T> {
    private final A algorithm;
    private final String provider;

    public AlgorithmInstancer(A algorithm, String provider) {
        this.algorithm = algorithm;
        this.provider = provider;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T getInstance(Class<T> typeClass) throws Exception {
        if (StringUtils.isBlank(provider)) {
            return (T) typeClass.getMethod("getInstance", String.class).invoke(null, algorithm.toString());
        } else {
            return (T) typeClass.getMethod("getInstance", String.class, String.class).invoke(null, algorithm.toString(),
                    provider);
        }
    }
}
