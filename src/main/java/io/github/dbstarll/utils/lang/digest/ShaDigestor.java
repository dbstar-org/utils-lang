package io.github.dbstarll.utils.lang.digest;

import io.github.dbstarll.utils.lang.EncryptUtils;

import java.security.NoSuchAlgorithmException;

public class ShaDigestor extends AlgorithmDigestor {
    private final int strength;

    /**
     * 构造ShaDigestor.
     *
     * @param strength SHA强度
     * @throws NoSuchAlgorithmException 未知算法
     */
    public ShaDigestor(final int strength) throws NoSuchAlgorithmException {
        super(EncryptUtils.ALGORITHM_SHA + "-" + strength);
        this.strength = strength;
    }

    @Override
    public final String toString() {
        return "ShaDigestor [strength=" + strength + "]";
    }
}
