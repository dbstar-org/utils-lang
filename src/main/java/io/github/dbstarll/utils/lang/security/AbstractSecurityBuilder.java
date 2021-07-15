package io.github.dbstarll.utils.lang.security;

import java.security.NoSuchAlgorithmException;

public abstract class AbstractSecurityBuilder<T, A extends Enum<?>> implements SecurityBuilder<T> {
    protected final T type;

    protected AbstractSecurityBuilder(Class<T> typeClass, A algorithm)
            throws NoSuchAlgorithmException, InstanceException {
        this(typeClass, new AlgorithmInstancer<T, A>(algorithm, null));
    }

    protected AbstractSecurityBuilder(Class<T> typeClass, Instancer<T> instancer)
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

    @Override
    public final T build() {
        return type;
    }
}
