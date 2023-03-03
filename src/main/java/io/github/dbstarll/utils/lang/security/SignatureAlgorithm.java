package io.github.dbstarll.utils.lang.security;

import io.github.dbstarll.utils.lang.enums.EnumValue;

@EnumValue(method = "toString")
public enum SignatureAlgorithm {
    MD2_WITH_RSA("MD2withRSA"), MD5_AND_SHA1_WITH_RSA("MD5andSHA1withRSA"), MD5_WITH_RSA("MD5withRSA"),

    NONE_WITH_DSA("NONEwithDSA"), NONE_WITH_ECDSA("NONEwithECDSA"),

    SHA1_WITH_DSA("SHA1withDSA"), SHA1_WITH_ECDSA("SHA1withECDSA"), SHA1_WITH_RSA("SHA1withRSA"),

    SHA256_WITH_ECDSA("SHA256withECDSA"), SHA256_WITH_RSA("SHA256withRSA"),

    SHA384_WITH_ECDSA("SHA384withECDSA"), SHA384_WITH_RSA("SHA384withRSA"),

    SHA512_WITH_ECDSA("SHA512withECDSA"), SHA512_WITH_RSA("SHA512withRSA");

    private final String title;

    SignatureAlgorithm(final String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
