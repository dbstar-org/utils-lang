package io.github.dbstarll.utils.lang.security;

import io.github.dbstarll.utils.lang.enums.EnumValue;

@EnumValue(method = "toString")
public enum MacAlgorithm {
    HMAC_MD5("HmacMD5"), HMAC_PBE_SHA_1("HmacPBESHA1"), HMAC_SHA_1("HmacSHA1"), HMAC_SHA_256("HmacSHA256"),
    HMAC_SHA_384("HmacSHA384"), HMAC_SHA_512("HmacSHA512"), SSL_MAC_MD5("SslMacMD5"), SSL_MAC_SHA_1("SslMacSHA1");

    private final String title;

    MacAlgorithm(final String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
