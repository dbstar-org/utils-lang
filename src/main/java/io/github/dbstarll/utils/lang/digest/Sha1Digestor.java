package io.github.dbstarll.utils.lang.digest;

import java.security.NoSuchAlgorithmException;

public class Sha1Digestor extends ShaDigestor {
    public static final int SHA1 = 1;

    /**
     * 构造Sha1Digestor.
     *
     * @throws NoSuchAlgorithmException 未知算法
     */
    public Sha1Digestor() throws NoSuchAlgorithmException {
        super(SHA1);
    }
}
