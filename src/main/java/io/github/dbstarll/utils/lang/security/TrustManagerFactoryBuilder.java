package io.github.dbstarll.utils.lang.security;

import javax.net.ssl.TrustManagerFactory;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

public final class TrustManagerFactoryBuilder
        extends AbstractSecurityBuilder<TrustManagerFactory, TrustManagerFactoryAlgorithm> {
    public TrustManagerFactoryBuilder(final TrustManagerFactoryAlgorithm algorithm)
            throws NoSuchAlgorithmException, InstanceException {
        super(TrustManagerFactory.class, algorithm);
    }

    public TrustManagerFactoryBuilder keyStore(final KeyStore ks) throws KeyStoreException {
        getType().init(ks);
        return this;
    }
}
