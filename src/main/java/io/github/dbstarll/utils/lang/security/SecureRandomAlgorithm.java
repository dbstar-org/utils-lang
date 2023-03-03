package io.github.dbstarll.utils.lang.security;

import io.github.dbstarll.utils.lang.enums.EnumValue;

@EnumValue(method = "toString")
public enum SecureRandomAlgorithm {
    NATIVE_PRNG("NativePRNG"), SHA_1_PRNG("SHA1PRNG");

    private final String title;

    SecureRandomAlgorithm(final String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
