package io.github.dbstarll.utils.lang.security;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

public final class KeyStoreBuilder extends AbstractSecurityBuilder<KeyStore, KeyStoreAlgorithm> {
    /**
     * 构造KeyStoreBuilder.
     *
     * @param algorithm KeyStoreAlgorithm
     * @throws NoSuchAlgorithmException 未知算法
     * @throws InstanceException        不能实例化
     */
    public KeyStoreBuilder(final KeyStoreAlgorithm algorithm) throws NoSuchAlgorithmException, InstanceException {
        super(KeyStore.class, algorithm);
    }

    /**
     * 从输入流加载KeyStore.
     *
     * @param stream   输入流
     * @param password KeyStore密码
     * @return KeyStoreBuilder
     * @throws NoSuchAlgorithmException 未知算法
     * @throws CertificateException     证书异常
     * @throws IOException              流读取异常
     */
    public KeyStoreBuilder load(final InputStream stream, final char[] password)
            throws NoSuchAlgorithmException, CertificateException, IOException {
        getType().load(stream, password);
        return this;
    }
}
