package io.github.dbstarll.utils.lang.security;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;

public final class KeyFactoryBuilder extends AbstractSecurityBuilder<KeyFactory, KeyFactoryAlgorithm> {
    public KeyFactoryBuilder(KeyFactoryAlgorithm algorithm) throws NoSuchAlgorithmException, InstanceException {
        super(KeyFactory.class, algorithm);
    }
}
