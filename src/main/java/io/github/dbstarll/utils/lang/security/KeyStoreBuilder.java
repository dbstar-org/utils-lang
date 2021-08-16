package io.github.dbstarll.utils.lang.security;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

public final class KeyStoreBuilder extends AbstractSecurityBuilder<KeyStore, KeyStoreAlgorithm> {
    public KeyStoreBuilder(KeyStoreAlgorithm algorithm) throws NoSuchAlgorithmException, InstanceException {
        super(KeyStore.class, algorithm);
    }

    public KeyStoreBuilder load(InputStream stream, char[] password)
            throws NoSuchAlgorithmException, CertificateException, IOException {
        getType().load(stream, password);
        return this;
    }
}
