package io.github.dbstarll.utils.lang.security;

import java.security.*;
import java.security.cert.Certificate;

public final class SignatureBuilder extends AbstractSecurityBuilder<Signature, SignatureAlgorithm> {
    public SignatureBuilder(SignatureAlgorithm algorithm) throws NoSuchAlgorithmException, InstanceException {
        super(Signature.class, algorithm);
    }

    public SignatureBuilder sign(PrivateKey privateKey, SecureRandom random) throws InvalidKeyException {
        getType().initSign(privateKey, random);
        return this;
    }

    public SignatureBuilder verify(PublicKey publicKey) throws InvalidKeyException {
        getType().initVerify(publicKey);
        return this;
    }

    public SignatureBuilder verify(Certificate certificate) throws InvalidKeyException {
        getType().initVerify(certificate);
        return this;
    }
}
