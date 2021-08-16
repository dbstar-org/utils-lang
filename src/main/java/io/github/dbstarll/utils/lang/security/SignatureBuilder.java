package io.github.dbstarll.utils.lang.security;

import java.security.*;
import java.security.cert.Certificate;

public final class SignatureBuilder extends AbstractSecurityBuilder<Signature, SignatureAlgorithm> {
    public SignatureBuilder(final SignatureAlgorithm algorithm) throws NoSuchAlgorithmException, InstanceException {
        super(Signature.class, algorithm);
    }

    public SignatureBuilder sign(final PrivateKey privateKey, final SecureRandom random) throws InvalidKeyException {
        getType().initSign(privateKey, random);
        return this;
    }

    public SignatureBuilder verify(final PublicKey publicKey) throws InvalidKeyException {
        getType().initVerify(publicKey);
        return this;
    }

    public SignatureBuilder verify(final Certificate certificate) throws InvalidKeyException {
        getType().initVerify(certificate);
        return this;
    }
}
