package io.github.dbstarll.utils.lang.digest;

import io.github.dbstarll.utils.lang.EncryptUtils;

import java.security.NoSuchAlgorithmException;

public class ShaDigestor extends AlgorithmDigestor {
    private final int strength;

    public ShaDigestor(final int strength) throws NoSuchAlgorithmException {
        super(EncryptUtils.ALGORITHM_SHA + "-" + strength);
        this.strength = strength;
    }

    @Override
    public final String toString() {
        return "ShaDigestor [strength=" + strength + "]";
    }
}
