package io.github.dbstarll.utils.lang.security;

import javax.net.ssl.TrustManagerFactory;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

public final class TrustManagerFactoryBuilder
        extends AbstractSecurityBuilder<TrustManagerFactory, TrustManagerFactoryAlgorithm> {
    /**
     * 构造TrustManagerFactoryBuilder.
     *
     * @param algorithm TrustManagerFactoryAlgorithm
     * @throws NoSuchAlgorithmException 未知算法
     * @throws InstanceException        不能实例化
     */
    public TrustManagerFactoryBuilder(final TrustManagerFactoryAlgorithm algorithm)
            throws NoSuchAlgorithmException, InstanceException {
        super(TrustManagerFactory.class, algorithm);
    }

    /**
     * 初始化KeyStore.
     *
     * @param ks KeyStore
     * @return TrustManagerFactoryBuilder
     * @throws KeyStoreException KeyStore异常
     */
    public TrustManagerFactoryBuilder keyStore(final KeyStore ks) throws KeyStoreException {
        getType().init(ks);
        return this;
    }
}
