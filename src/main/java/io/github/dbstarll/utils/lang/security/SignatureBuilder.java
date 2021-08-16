package io.github.dbstarll.utils.lang.security;

import java.security.*;
import java.security.cert.Certificate;

public final class SignatureBuilder extends AbstractSecurityBuilder<Signature, SignatureAlgorithm> {
    /**
     * 构造SignatureBuilder.
     *
     * @param algorithm SignatureAlgorithm
     * @throws NoSuchAlgorithmException 未知算法
     * @throws InstanceException        不能实例化
     */
    public SignatureBuilder(final SignatureAlgorithm algorithm) throws NoSuchAlgorithmException, InstanceException {
        super(Signature.class, algorithm);
    }

    /**
     * 设置用于签名的私钥和随机数.
     *
     * @param privateKey the private key of the identity whose signature
     *                   is going to be generated.
     * @param random     the source of randomness for this signature.
     * @return SignatureBuilder
     * @throws InvalidKeyException if the key is invalid.
     */
    public SignatureBuilder sign(final PrivateKey privateKey, final SecureRandom random) throws InvalidKeyException {
        getType().initSign(privateKey, random);
        return this;
    }

    /**
     * 设置用于校验的公钥.
     *
     * @param publicKey the public key of the identity whose signature is
     *                  going to be verified.
     * @return SignatureBuilder
     * @throws InvalidKeyException if the key is invalid.
     */
    public SignatureBuilder verify(final PublicKey publicKey) throws InvalidKeyException {
        getType().initVerify(publicKey);
        return this;
    }

    /**
     * 设置用于校验的证书.
     *
     * @param certificate the certificate of the identity whose signature is
     *                    going to be verified.
     * @return SignatureBuilder
     * @throws InvalidKeyException if the public key in the certificate
     *                             is not encoded properly or does not include required  parameter
     *                             information or cannot be used for digital signature purposes.
     */
    public SignatureBuilder verify(final Certificate certificate) throws InvalidKeyException {
        getType().initVerify(certificate);
        return this;
    }
}
