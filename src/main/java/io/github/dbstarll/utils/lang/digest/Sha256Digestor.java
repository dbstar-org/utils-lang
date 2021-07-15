package io.github.dbstarll.utils.lang.digest;

import java.security.NoSuchAlgorithmException;

public class Sha256Digestor extends ShaDigestor {
    public Sha256Digestor() throws NoSuchAlgorithmException {
        super(256);
    }
}
