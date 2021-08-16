package io.github.dbstarll.utils.lang.security;

import javax.net.ssl.KeyManagerFactory;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;

public final class KeyManagerFactoryBuilder
        extends AbstractSecurityBuilder<KeyManagerFactory, KeyManagerFactoryAlgorithm> {
    /**
     * 构造KeyManagerFactoryBuilder.
     *
     * @param algorithm KeyManagerFactoryAlgorithm
     * @throws NoSuchAlgorithmException 未知算法
     * @throws InstanceException        不能实例化
     */
    public KeyManagerFactoryBuilder(final KeyManagerFactoryAlgorithm algorithm)
            throws NoSuchAlgorithmException, InstanceException {
        super(KeyManagerFactory.class, algorithm);
    }

    /**
     * 设置keyStore并返回KeyManagerFactoryBuilder.
     *
     * @param ks       KeyStore
     * @param password 密码
     * @return KeyManagerFactoryBuilder
     * @throws UnrecoverableKeyException thrown if a key in the keystore cannot be recovered
     * @throws KeyStoreException         KeyStore访问异常
     * @throws NoSuchAlgorithmException  未知算法
     */
    public KeyManagerFactoryBuilder keyStore(final KeyStore ks, final char[] password)
            throws UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException {
        getType().init(ks, password);
        return this;
    }
}
