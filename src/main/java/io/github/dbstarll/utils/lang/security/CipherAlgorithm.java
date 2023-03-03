package io.github.dbstarll.utils.lang.security;

import io.github.dbstarll.utils.lang.enums.EnumValue;

@EnumValue(method = "toString")
public enum CipherAlgorithm {
    AES("AES"), AES_WRAP("AESWrap"), BLOW_FISH("Blowfish"), DES("DES"), DE_SEDE("DESede"),
    DE_SEDE_WRAP("DESedeWrap"), PBE_WITH_MD5_AND_DES("PBEWithMD5AndDES"),
    PBE_WITH_MD5_AND_TRIPLE_DES("PBEWithMD5AndTripleDES"), PBE_WITH_SHA1_AND_DE_SEDE("PBEWithSHA1AndDESede"),
    PBE_WITH_SHA1_AND_RC2_40("PBEWithSHA1AndRC2_40"), RC2("RC2"), RC4("RC4"), RSA("RSA");

    private final String title;

    CipherAlgorithm(final String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
