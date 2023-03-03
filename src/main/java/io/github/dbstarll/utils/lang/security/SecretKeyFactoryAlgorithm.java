package io.github.dbstarll.utils.lang.security;

import io.github.dbstarll.utils.lang.enums.EnumValue;

@EnumValue(method = "toString")
public enum SecretKeyFactoryAlgorithm {
    DES("DES"),
    DE_SEDE("DESede"),
    PBE_WITH_MD5_AND_DES("PBEWithMD5AndDES"),
    PBE_WITH_MD5_AND_TRIPLE_DES("PBEWithMD5AndTripleDES"),
    PBE_WITH_SHA_1_AND_DE_SEDE("PBEWithSHA1AndDESede"),
    PBE_WITH_SHA_1_AND_RC2_40("PBEWithSHA1AndRC2_40"),
    PBKDF_2_WITH_HMAC_SHA_1("PBKDF2WithHmacSHA1");

    private final String title;

    SecretKeyFactoryAlgorithm(final String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
