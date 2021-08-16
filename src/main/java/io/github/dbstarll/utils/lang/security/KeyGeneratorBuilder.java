package io.github.dbstarll.utils.lang.security;

import javax.crypto.KeyGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public final class KeyGeneratorBuilder extends AbstractSecurityBuilder<KeyGenerator, KeyGeneratorAlgorithm> {
    /**
     * 构造KeyGeneratorBuilder.
     *
     * @param algorithm KeyGeneratorAlgorithm
     * @throws NoSuchAlgorithmException 未知算法
     * @throws InstanceException        不能实例化
     */
    public KeyGeneratorBuilder(final KeyGeneratorAlgorithm algorithm)
            throws NoSuchAlgorithmException, InstanceException {
        super(KeyGenerator.class, algorithm);
    }

    /**
     * 设置key和随机数，并返回KeyGeneratorBuilder.
     *
     * @param keysize key大小
     * @param random  随机数
     * @return KeyGeneratorBuilder
     */
    public KeyGeneratorBuilder keySize(final int keysize, final SecureRandom random) {
        getType().init(keysize, random);
        return this;
    }
}
