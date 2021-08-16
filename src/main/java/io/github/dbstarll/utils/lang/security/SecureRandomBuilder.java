package io.github.dbstarll.utils.lang.security;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public final class SecureRandomBuilder extends AbstractSecurityBuilder<SecureRandom, SecureRandomAlgorithm> {
    /**
     * 构造SecureRandomBuilder.
     *
     * @param algorithm SecureRandomAlgorithm
     * @throws NoSuchAlgorithmException 未知算法
     * @throws InstanceException        不能实例化
     */
    public SecureRandomBuilder(final SecureRandomAlgorithm algorithm)
            throws NoSuchAlgorithmException, InstanceException {
        super(SecureRandom.class, algorithm);
    }

    /**
     * 设置种子来初始化随机数.
     *
     * @param seed 随机数种子
     * @return SecureRandomBuilder
     */
    public SecureRandomBuilder seed(final byte[] seed) {
        getType().setSeed(seed);
        return this;
    }
}
