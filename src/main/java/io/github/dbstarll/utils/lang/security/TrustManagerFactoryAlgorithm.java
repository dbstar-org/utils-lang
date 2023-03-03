package io.github.dbstarll.utils.lang.security;

import io.github.dbstarll.utils.lang.enums.EnumValue;

@EnumValue(method = "toString")
public enum TrustManagerFactoryAlgorithm {
    PKIX("PKIX"), SUN_X509("SunX509");

    private final String title;

    TrustManagerFactoryAlgorithm(final String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
