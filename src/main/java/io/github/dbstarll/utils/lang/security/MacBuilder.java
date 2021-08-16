package io.github.dbstarll.utils.lang.security;

import javax.crypto.Mac;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

public final class MacBuilder extends AbstractSecurityBuilder<Mac, MacAlgorithm> {
  public MacBuilder(final MacAlgorithm algorithm) throws NoSuchAlgorithmException, InstanceException {
      super(Mac.class, algorithm);
  }

    public MacBuilder key(final Key key) throws InvalidKeyException {
        getType().init(key);
        return this;
    }
}
