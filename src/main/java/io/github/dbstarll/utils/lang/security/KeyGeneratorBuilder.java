package io.github.dbstarll.utils.lang.security;

import javax.crypto.KeyGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public final class KeyGeneratorBuilder extends AbstractSecurityBuilder<KeyGenerator, KeyGeneratorAlgorithm> {
  public KeyGeneratorBuilder(KeyGeneratorAlgorithm algorithm) throws NoSuchAlgorithmException, InstanceException {
    super(KeyGenerator.class, algorithm);
  }

  public KeyGeneratorBuilder keySize(int keysize, SecureRandom random) {
    getType().init(keysize, random);
    return this;
  }
}
