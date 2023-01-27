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
        } catch (Exception ex) {
            if (NoSuchAlgorithmException.class.isInstance(ex)) {
                throw (NoSuchAlgorithmException) ex;
            } else {
                throw new InstanceException("getInstance failed for: " + typeClass.getName(), ex);
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
