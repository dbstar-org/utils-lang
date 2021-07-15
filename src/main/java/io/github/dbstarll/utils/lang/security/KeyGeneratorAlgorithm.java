package io.github.dbstarll.utils.lang.security;

public enum KeyGeneratorAlgorithm {
    AES, Blowfish, DES, DESede,

    HmacMD5, HmacSHA1, HmacSHA256, HmacSHA384, HmacSHA512,

    RC2, RC4,

    SunTls12Prf, SunTlsKeyMaterial, SunTlsMasterSecret, SunTlsPrf, SunTlsRsaPremasterSecret
}
