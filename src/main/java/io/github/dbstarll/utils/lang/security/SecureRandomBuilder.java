package io.github.dbstarll.utils.lang.security;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public final class SecureRandomBuilder extends AbstractSecurityBuilder<SecureRandom, SecureRandomAlgorithm> {
    public SecureRandomBuilder(SecureRandomAlgorithm algorithm) throws NoSuchAlgorithmException, InstanceException {
        super(SecureRandom.class, algorithm);
    }

    public SecureRandomBuilder seed(byte[] seed) {
        getType().setSeed(seed);
        return this;
    }
}
