package io.github.dbstarll.utils.lang.security;

import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public final class SslContextBuilder extends AbstractSecurityBuilder<SSLContext, SslContextAlgorithm> {
    /**
     * 构造SslContextBuilder.
     *
     * @param algorithm SslContextAlgorithm
     * @throws NoSuchAlgorithmException 未知算法
     * @throws InstanceException        不能实例化
     */
    public SslContextBuilder(final SslContextAlgorithm algorithm) throws NoSuchAlgorithmException, InstanceException {
        super(SSLContext.class, algorithm);
    }

    /**
     * 初始化SSLContext.
     *
     * @param km     KeyManager数组
     * @param tm     TrustManager数组
     * @param random 随机数
     * @return SslContextBuilder
     * @throws KeyManagementException key异常
     */
    public SslContextBuilder init(final KeyManager[] km,
                                  final TrustManager[] tm,
                                  final SecureRandom random) throws KeyManagementException {
        getType().init(km, tm, random);
        return this;
    }
}
