package io.github.dbstarll.utils.lang.security;

import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public final class KeyPairGeneratorBuilder
        extends AbstractSecurityBuilder<KeyPairGenerator, KeyPairGeneratorAlgorithm> {
    /**
     * 构造KeyPairGeneratorBuilder.
     *
     * @param algorithm KeyPairGeneratorAlgorithm
     * @throws NoSuchAlgorithmException 未知算法
     * @throws InstanceException        不能实例化
     */
    public KeyPairGeneratorBuilder(final KeyPairGeneratorAlgorithm algorithm)
            throws NoSuchAlgorithmException, InstanceException {
        super(KeyPairGenerator.class, algorithm);
    }

    /**
     * 设置key和随机数，并返回KeyPairGeneratorBuilder.
     *
     * @param keysize key大小
     * @param random  随机数
     * @return KeyPairGeneratorBuilder
     */
    public KeyPairGeneratorBuilder keySize(final int keysize, final SecureRandom random) {
        getType().initialize(keysize, random);
        return this;
    }
}
