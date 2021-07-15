package io.github.dbstarll.utils.lang.security;

import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Security;

public final class SecurityFactory {
    public static int addProvider(Provider provider) {
        return Security.addProvider(provider);
    }

    // AlgorithmParameterGenerator
    // ..[DH, DSA]
    // ..........DH=[DiffieHellman]

    // AlgorithmParameters
    // ..[AES, Blowfish, DES, DESede, DH, DSA, EC, OAEP, PBE, PBEWithMD5AndDES,
    // PBEWithMD5AndTripleDES, PBEWithSHA1AndDESede, PBEWithSHA1AndRC2_40, RC2]
    // ..........DESede=[TripleDES]
    // ..........EC=[EllipticCurve]
    // ..........AES=[Rijndael]
    // ..........DH=[DiffieHellman]

    // CertPathBuilder
    // ..[PKIX]

    // CertPathValidator
    // ..[PKIX]

    // CertStore
    // ..[Collection, LDAP]

    public static CertificateFactoryBuilder builder(CertificateFactoryAlgorithm algorithm)
            throws NoSuchAlgorithmException, InstanceException {
        return new CertificateFactoryBuilder(algorithm);
    }

    public static CipherBuilder builder(CipherAlgorithm algorithm, CipherAlgorithmMode mode,
                                        CipherAlgorithmPadding padding) throws NoSuchAlgorithmException, InstanceException {
        return new CipherBuilder(algorithm, mode, padding);
    }

    // Configuration
    // ..[JavaLoginConfig]

    // KeyAgreement
    // ..[DH, ECDH]
    // ..........DH=[DiffieHellman]

    public static KeyFactoryBuilder builder(KeyFactoryAlgorithm algorithm)
            throws NoSuchAlgorithmException, InstanceException {
        return new KeyFactoryBuilder(algorithm);
    }

    public static KeyGeneratorBuilder builder(KeyGeneratorAlgorithm algorithm)
            throws NoSuchAlgorithmException, InstanceException {
        return new KeyGeneratorBuilder(algorithm);
    }

    // KeyInfoFactory
    // ..[DOM]

    public static KeyManagerFactoryBuilder builder(KeyManagerFactoryAlgorithm algorithm)
            throws NoSuchAlgorithmException, InstanceException {
        return new KeyManagerFactoryBuilder(algorithm);
    }

    public static KeyPairGeneratorBuilder builder(KeyPairGeneratorAlgorithm algorithm)
            throws NoSuchAlgorithmException, InstanceException {
        return new KeyPairGeneratorBuilder(algorithm);
    }

    public static KeyStoreBuilder builder(KeyStoreAlgorithm algorithm)
            throws NoSuchAlgorithmException, InstanceException {
        return new KeyStoreBuilder(algorithm);
    }

    public static MacBuilder builder(MacAlgorithm algorithm) throws NoSuchAlgorithmException, InstanceException {
        return new MacBuilder(algorithm);
    }

    public static MessageDigestBuilder builder(MessageDigestAlgorithm algorithm)
            throws NoSuchAlgorithmException, InstanceException {
        return new MessageDigestBuilder(algorithm);
    }

    // Policy
    // ..[JavaPolicy]

    public static SslContextBuilder builder(SslContextAlgorithm algorithm)
            throws NoSuchAlgorithmException, InstanceException {
        return new SslContextBuilder(algorithm);
    }

    // SaslClientFactory
    // ..[CRAM-MD5, DIGEST-MD5, EXTERNAL, GSSAPI, NTLM, PLAIN]

    // SaslServerFactory
    // ..[CRAM-MD5, DIGEST-MD5, GSSAPI, NTLM]

    public static SecretKeyFactoryBuilder builder(SecretKeyFactoryAlgorithm algorithm)
            throws NoSuchAlgorithmException, InstanceException {
        return new SecretKeyFactoryBuilder(algorithm);
    }

    public static SecureRandomBuilder builder(SecureRandomAlgorithm algorithm)
            throws NoSuchAlgorithmException, InstanceException {
        return new SecureRandomBuilder(algorithm);
    }

    public static SignatureBuilder builder(SignatureAlgorithm algorithm)
            throws NoSuchAlgorithmException, InstanceException {
        return new SignatureBuilder(algorithm);
    }

    // TerminalFactory
    // ..[PC/SC]

    public static TrustManagerFactoryBuilder builder(TrustManagerFactoryAlgorithm algorithm)
            throws NoSuchAlgorithmException, InstanceException {
        return new TrustManagerFactoryBuilder(algorithm);
    }

    // XMLSignatureFactory
    // ..[DOM]
}