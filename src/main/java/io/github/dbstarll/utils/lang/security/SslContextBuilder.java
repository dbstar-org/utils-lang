package io.github.dbstarll.utils.lang.security;

import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public final class SslContextBuilder extends AbstractSecurityBuilder<SSLContext, SslContextAlgorithm> {
    public SslContextBuilder(SslContextAlgorithm algorithm) throws NoSuchAlgorithmException, InstanceException {
        super(SSLContext.class, algorithm);
    }

    public SslContextBuilder init(KeyManager[] km, TrustManager[] tm, SecureRandom random) throws KeyManagementException {
        type.init(km, tm, random);
        return this;
    }
}
