package io.github.dbstarll.utils.lang.security;

import io.github.dbstarll.utils.lang.bytes.Bytes;
import io.github.dbstarll.utils.lang.bytes.BytesUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public final class Signer {
    public static final String HMAC_ALGORITHM_SHA1 = "HmacSHA1";
    public static final String HMAC_ALGORITHM_SHA256 = "HmacSHA256";

    /**
     * 签名并返回Bytes类型结果.
     *
     * @param secretKey 加密密钥
     * @param data      待加密的数据
     * @param algorithm 加密算法
     * @return 加密后的数据
     * @throws NoSuchAlgorithmException 没有指定的加密算法
     * @throws InvalidKeyException      加密密钥不正确
     */
    public Bytes sign(final Bytes secretKey, final Bytes data, final String algorithm)
            throws NoSuchAlgorithmException, InvalidKeyException {
        final SecretKeySpec signingKey = new SecretKeySpec(secretKey.get(), algorithm);
        final Mac mac = Mac.getInstance(signingKey.getAlgorithm());
        mac.init(signingKey);
        return new Bytes(mac.doFinal(data.get()));
    }

    /**
     * 签名并将结果转换为十六进制字符串.
     *
     * @param secretKey 加密密钥
     * @param data      待加密的数据
     * @param algorithm 加密算法
     * @return 加密后的数据
     * @throws NoSuchAlgorithmException 没有指定的加密算法
     * @throws InvalidKeyException      加密密钥不正确
     */
    public String sign(final String secretKey, final String data, final String algorithm)
            throws NoSuchAlgorithmException, InvalidKeyException {
        return BytesUtils.encodeHexString(sign(new Bytes(secretKey), new Bytes(data), algorithm).get());
    }
}
