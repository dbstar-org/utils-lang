package io.github.dbstarll.utils.lang.security;

import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public final class KeyPairGeneratorBuilder
        extends AbstractSecurityBuilder<KeyPairGenerator, KeyPairGeneratorAlgorithm> {
    public KeyPairGeneratorBuilder(KeyPairGeneratorAlgorithm algorithm)
            throws NoSuchAlgorithmException, InstanceException {
        super(KeyPairGenerator.class, algorithm);
    }

    public KeyPairGeneratorBuilder keySize(int keysize, SecureRandom random) {
        type.initialize(keysize, random);
        return this;
    }
}
