package io.github.dbstarll.utils.lang.security;

import io.github.dbstarll.utils.lang.enums.EnumValue;

@EnumValue(method = "toString")
public enum MessageDigestAlgorithm {
    MD2("MD2"), MD5("MD5"), SHA("SHA"), SHA_256("SHA-256"), SHA_384("SHA-384"), SHA_512("SHA-512");

    private final String title;

    MessageDigestAlgorithm(final String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
