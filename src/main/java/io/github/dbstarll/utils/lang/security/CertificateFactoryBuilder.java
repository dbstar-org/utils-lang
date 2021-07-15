package io.github.dbstarll.utils.lang.security;

import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateFactory;

public final class CertificateFactoryBuilder
        extends AbstractSecurityBuilder<CertificateFactory, CertificateFactoryAlgorithm> {
    public CertificateFactoryBuilder(CertificateFactoryAlgorithm algorithm)
            throws NoSuchAlgorithmException, InstanceException {
        super(CertificateFactory.class, algorithm);
    }
}
