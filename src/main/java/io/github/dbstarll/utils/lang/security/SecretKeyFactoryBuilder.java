package io.github.dbstarll.utils.lang.security;

import javax.crypto.SecretKeyFactory;
import java.security.NoSuchAlgorithmException;

public final class SecretKeyFactoryBuilder
        extends AbstractSecurityBuilder<SecretKeyFactory, SecretKeyFactoryAlgorithm> {
    /**
     * 构造SecretKeyFactoryBuilder.
     *
     * @param algorithm SecretKeyFactoryAlgorithm
     * @throws NoSuchAlgorithmException 未知算法
     * @throws InstanceException        不能实例化
     */
    public SecretKeyFactoryBuilder(final SecretKeyFactoryAlgorithm algorithm)
            throws NoSuchAlgorithmException, InstanceException {
        super(SecretKeyFactory.class, algorithm);
    }
}
