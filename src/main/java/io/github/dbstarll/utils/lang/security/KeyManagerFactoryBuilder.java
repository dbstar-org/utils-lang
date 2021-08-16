package io.github.dbstarll.utils.lang.security;

import javax.net.ssl.KeyManagerFactory;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;

public final class KeyManagerFactoryBuilder
        extends AbstractSecurityBuilder<KeyManagerFactory, KeyManagerFactoryAlgorithm> {
    public KeyManagerFactoryBuilder(KeyManagerFactoryAlgorithm algorithm)
            throws NoSuchAlgorithmException, InstanceException {
        super(KeyManagerFactory.class, algorithm);
    }

    public KeyManagerFactoryBuilder keyStore(KeyStore ks, char[] password)
            throws UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException {
        getType().init(ks, password);
        return this;
    }
}
