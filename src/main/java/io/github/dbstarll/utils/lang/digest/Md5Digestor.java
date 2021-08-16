package io.github.dbstarll.utils.lang.digest;

import io.github.dbstarll.utils.lang.EncryptUtils;

import java.security.NoSuchAlgorithmException;

public class Md5Digestor extends AlgorithmDigestor {
    /**
     * 构造Md5Digestor.
     *
     * @throws NoSuchAlgorithmException 未知算法
     */
    public Md5Digestor() throws NoSuchAlgorithmException {
        super(EncryptUtils.ALGORITHM_MD5);
    }
}
