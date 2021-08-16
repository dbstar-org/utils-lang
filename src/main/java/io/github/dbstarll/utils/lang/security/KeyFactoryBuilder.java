package io.github.dbstarll.utils.lang.security;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;

public final class KeyFactoryBuilder extends AbstractSecurityBuilder<KeyFactory, KeyFactoryAlgorithm> {
    /**
     * 构造KeyFactoryBuilder.
     *
     * @param algorithm KeyFactoryAlgorithm
     * @throws NoSuchAlgorithmException 未知算法
     * @throws InstanceException        不能实例化
     */
    public KeyFactoryBuilder(final KeyFactoryAlgorithm algorithm) throws NoSuchAlgorithmException, InstanceException {
        super(KeyFactory.class, algorithm);
    }
}
