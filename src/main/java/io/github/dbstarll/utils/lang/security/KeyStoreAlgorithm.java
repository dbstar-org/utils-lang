package io.github.dbstarll.utils.lang.security;

import io.github.dbstarll.utils.lang.enums.EnumValue;

@EnumValue(method = "toString")
public enum KeyStoreAlgorithm {
    CASE_EXACT_JKS("CaseExactJKS"), JCEKS("JCEKS"), JKS("JKS"), KEYCHAIN_STORE("KeychainStore"), PKCS12("PKCS12");

    private final String title;

    KeyStoreAlgorithm(final String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
