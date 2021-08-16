package io.github.dbstarll.utils.lang.security;

import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateFactory;

public final class CertificateFactoryBuilder
        extends AbstractSecurityBuilder<CertificateFactory, CertificateFactoryAlgorithm> {
    /**
     * 构造CertificateFactoryBuilder.
     *
     * @param algorithm CertificateFactoryAlgorithm
     * @throws NoSuchAlgorithmException 算法不存在
     * @throws InstanceException        不能实例化
     */
    public CertificateFactoryBuilder(final CertificateFactoryAlgorithm algorithm)
            throws NoSuchAlgorithmException, InstanceException {
        super(CertificateFactory.class, algorithm);
    }
}
