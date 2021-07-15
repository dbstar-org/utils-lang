package io.github.dbstarll.utils.lang.digest;

import java.security.NoSuchAlgorithmException;

public class Sha1Digestor extends ShaDigestor {
    public Sha1Digestor() throws NoSuchAlgorithmException {
        super(1);
    }
}
