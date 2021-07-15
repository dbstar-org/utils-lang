package io.github.dbstarll.utils.lang.security;

public enum SignatureAlgorithm {
    MD2withRSA, MD5andSHA1withRSA, MD5withRSA,

    NONEwithDSA, NONEwithECDSA,

    SHA1withDSA, SHA1withECDSA, SHA1withRSA,

    SHA256withECDSA, SHA256withRSA,

    SHA384withECDSA, SHA384withRSA,

    SHA512withECDSA, SHA512withRSA
}
