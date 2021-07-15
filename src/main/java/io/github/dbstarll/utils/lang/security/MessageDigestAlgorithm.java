package io.github.dbstarll.utils.lang.security;

public enum MessageDigestAlgorithm {
    MD2, MD5, SHA, SHA256("SHA-256"), SHA384("SHA-384"), SHA512("SHA-512");

    private final String algorithm;

    private MessageDigestAlgorithm() {
        this(null);
    }

    private MessageDigestAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    @Override
    public String toString() {
        return algorithm == null ? name() : algorithm;
    }
}
