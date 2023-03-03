package io.github.dbstarll.utils.lang.security;

import io.github.dbstarll.utils.lang.enums.EnumValue;

@EnumValue(method = "toString")
public enum KeyGeneratorAlgorithm {
    AES("AES"), BLOW_FISH("Blowfish"), DES("DES"), DE_SEDE("DESede"), HMAC_MD5("HmacMD5"),
    HMAC_SHA1("HmacSHA1"), HMAC_SHA256("HmacSHA256"), HMAC_SHA384("HmacSHA384"), HMAC_SHA512("HmacSHA512"),

    RC2("RC2"), RC4("RC4"),

    SUN_TLS12_PRF("SunTls12Prf"), SUN_TLS_KEY_MATERIAL("SunTlsKeyMaterial"),
    SUN_TLS_MASTER_SECRET("SunTlsMasterSecret"), SUN_TLS_PRF("SunTlsPrf"),
    SUN_TLS_RSA_PREMASTER_SECRET("SunTlsRsaPremasterSecret");

    private final String title;

    KeyGeneratorAlgorithm(final String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
