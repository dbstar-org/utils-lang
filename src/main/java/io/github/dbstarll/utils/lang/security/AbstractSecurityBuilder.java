package io.github.dbstarll.utils.lang.security;

import java.security.NoSuchAlgorithmException;

public abstract class AbstractSecurityBuilder<T, A extends Enum<?>> implements SecurityBuilder<T> {
    private final T type;

    protected AbstractSecurityBuilder(final Class<T> typeClass, final A algorithm)
            throws NoSuchAlgorithmException, InstanceException {
        this(typeClass, new AlgorithmInstancer<>(algorithm, null));
    }

    protected AbstractSecurityBuilder(final Class<T> typeClass, final Instancer<T> instancer)
            throws NoSuchAlgorithmException, InstanceException {
        try {
            this.type = instancer.getInstance(typeClass);
        } catch (InstanceException ex) {
            if (ex.getCause() instanceof NoSuchAlgorithmException) {
                throw (NoSuchAlgorithmException) ex.getCause();
            } else {
                throw ex;
            }
        }
    }

    protected final T getType() {
        return type;
    }

    @Override
    public final T build() {
        return type;
    }
}
