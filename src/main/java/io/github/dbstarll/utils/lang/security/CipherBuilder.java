package io.github.dbstarll.utils.lang.security;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.AlgorithmParameterSpec;

public final class CipherBuilder extends AbstractSecurityBuilder<Cipher, CipherAlgorithm> {
  public CipherBuilder(CipherAlgorithm algorithm, CipherAlgorithmMode mode, CipherAlgorithmPadding padding)
          throws NoSuchAlgorithmException, InstanceException {
    super(Cipher.class, new TransformationInstancer(algorithm, mode, padding));
  }

  public CipherBuilder encrypt(Key key, SecureRandom random) throws InvalidKeyException {
    type.init(Cipher.ENCRYPT_MODE, key, random);
    return this;
  }

  public CipherBuilder decrypt(Key key, SecureRandom random) throws InvalidKeyException {
    type.init(Cipher.DECRYPT_MODE, key, random);
    return this;
  }

  public CipherBuilder decrypt(Key key, AlgorithmParameters parameters, SecureRandom random)
          throws InvalidKeyException, InvalidAlgorithmParameterException {
    type.init(Cipher.DECRYPT_MODE, key, parameters, random);
    return this;
  }

  public CipherBuilder decrypt(Key key, AlgorithmParameterSpec spec, SecureRandom random)
          throws InvalidKeyException, InvalidAlgorithmParameterException {
    type.init(Cipher.DECRYPT_MODE, key, spec, random);
    return this;
  }

  private static class TransformationInstancer implements Instancer<Cipher> {
    private final String transformation;

    public TransformationInstancer(CipherAlgorithm algorithm, CipherAlgorithmMode mode,
                                   CipherAlgorithmPadding padding) {
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
    public Cipher getInstance(Class<Cipher> typeClass) throws Exception {
      return Cipher.getInstance(transformation);
    }
  }
}
