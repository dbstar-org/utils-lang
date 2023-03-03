package io.github.dbstarll.utils.lang.security;

import io.github.dbstarll.utils.lang.enums.EnumValue;

@EnumValue(method = "toString")
public enum SslContextAlgorithm {
    DEFAULT("Default"), TLS_V1("TLSv1"), TLS_V11("TLSv1.1"), TLS_V12("TLSv1.2");

    private final String title;

    SslContextAlgorithm(final String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
