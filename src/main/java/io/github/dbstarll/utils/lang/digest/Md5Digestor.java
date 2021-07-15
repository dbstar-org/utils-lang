package io.github.dbstarll.utils.lang.digest;

import io.github.dbstarll.utils.lang.EncryptUtils;

import java.security.NoSuchAlgorithmException;

public class Md5Digestor extends AlgorithmDigestor {
    public Md5Digestor() throws NoSuchAlgorithmException {
        super(EncryptUtils.ALGORITHM_MD5);
    }
}
