package io.github.dbstarll.utils.lang.digest;

import java.security.NoSuchAlgorithmException;

public class Sha256Digestor extends ShaDigestor {
    public static final int SHA256 = 256;

    /**
     * 构造Sha256Digestor.
     *
     * @throws NoSuchAlgorithmException 未知算法
     */
    public Sha256Digestor() throws NoSuchAlgorithmException {
        super(SHA256);
    }
}
