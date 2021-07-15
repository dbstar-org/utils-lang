package io.github.dbstarll.utils.lang.security;

import javax.crypto.SecretKeyFactory;
import java.security.NoSuchAlgorithmException;

public final class SecretKeyFactoryBuilder
        extends AbstractSecurityBuilder<SecretKeyFactory, SecretKeyFactoryAlgorithm> {
    public SecretKeyFactoryBuilder(SecretKeyFactoryAlgorithm algorithm)
            throws NoSuchAlgorithmException, InstanceException {
        super(SecretKeyFactory.class, algorithm);
    }
}
