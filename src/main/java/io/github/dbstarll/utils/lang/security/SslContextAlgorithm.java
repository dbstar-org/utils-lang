package io.github.dbstarll.utils.lang.security;

public enum SslContextAlgorithm {
    Default, TLSv1, TLSv11("TLSv1.1"), TLSv12("TLSv1.2");

    private final String algorithm;

    private SslContextAlgorithm() {
        this(null);
    }

    private SslContextAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    @Override
    public String toString() {
        return algorithm == null ? name() : algorithm;
    }
}
