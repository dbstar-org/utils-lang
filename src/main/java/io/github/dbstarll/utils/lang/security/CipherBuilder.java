package io.github.dbstarll.utils.lang.security;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.AlgorithmParameterSpec;

public final class CipherBuilder extends AbstractSecurityBuilder<Cipher, CipherAlgorithm> {
    public CipherBuilder(final CipherAlgorithm algorithm, final CipherAlgorithmMode mode, final CipherAlgorithmPadding padding)
            throws NoSuchAlgorithmException, InstanceException {
        super(Cipher.class, new TransformationInstancer(algorithm, mode, padding));
    }

    public CipherBuilder encrypt(final Key key, final SecureRandom random) throws InvalidKeyException {
        getType().init(Cipher.ENCRYPT_MODE, key, random);
        return this;
    }

    public CipherBuilder decrypt(final Key key, final SecureRandom random) throws InvalidKeyException {
        getType().init(Cipher.DECRYPT_MODE, key, random);
        return this;
    }

    public CipherBuilder decrypt(final Key key, final AlgorithmParameters parameters, final SecureRandom random)
            throws InvalidKeyException, InvalidAlgorithmParameterException {
        getType().init(Cipher.DECRYPT_MODE, key, parameters, random);
        return this;
    }

    public CipherBuilder decrypt(final Key key, final AlgorithmParameterSpec spec, final SecureRandom random)
            throws InvalidKeyException, InvalidAlgorithmParameterException {
        getType().init(Cipher.DECRYPT_MODE, key, spec, random);
        return this;
    }

    private static class TransformationInstancer implements Instancer<Cipher> {
        private final String transformation;

        public TransformationInstancer(final CipherAlgorithm algorithm, final CipherAlgorithmMode mode,
                                       final CipherAlgorithmPadding padding) {
            final StringBuilder builder = new StringBuilder(algorithm.name());
            if (mode != null) {
                builder.append('/').append(mode.name());
            }
            if (padding != null) {
                builder.append('/').append(padding.name());
            }
            this.transformation = builder.toString();
        }

        @Override
        public Cipher getInstance(final Class<Cipher> typeClass) throws Exception {
            return Cipher.getInstance(transformation);
        }
    }
}
