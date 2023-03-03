package io.github.dbstarll.utils.lang.security;

import io.github.dbstarll.utils.lang.enums.EnumValue;

@EnumValue(method = "toString")
public enum CipherAlgorithmPadding {
    NO_PADDING("NoPadding"), ISO10126_PADDING("ISO10126Padding"), OAEP_PADDING("OAEPPadding"),
    PKCS1_PADDING("PKCS1Padding"), PKCS5_PADDING("PKCS5Padding"), SSL3_PADDING("SSL3Padding");

    private final String title;

    CipherAlgorithmPadding(final String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
