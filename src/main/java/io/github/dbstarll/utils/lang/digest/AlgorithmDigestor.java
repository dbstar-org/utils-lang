package io.github.dbstarll.utils.lang.digest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AlgorithmDigestor implements Digestor {
    private final MessageDigest digest;

    public AlgorithmDigestor(String algorithm) throws NoSuchAlgorithmException {
        this.digest = MessageDigest.getInstance(algorithm);
    }

    @Override
    public final synchronized byte[] digest(byte[] data) {
        digest.update(data);
        return digest.digest();
    }
}
