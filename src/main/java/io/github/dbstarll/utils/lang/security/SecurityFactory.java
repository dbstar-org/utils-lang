package io.github.dbstarll.utils.lang.security;

import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Security;

public final class SecurityFactory {
    public static int addProvider(final Provider provider) {
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

    public static CertificateFactoryBuilder builder(final CertificateFactoryAlgorithm algorithm)
            throws NoSuchAlgorithmException, InstanceException {
        return new CertificateFactoryBuilder(algorithm);
    }

    public static CipherBuilder builder(final CipherAlgorithm algorithm, final CipherAlgorithmMode mode,
                                        final CipherAlgorithmPadding padding) throws NoSuchAlgorithmException, InstanceException {
        return new CipherBuilder(algorithm, mode, padding);
    }

    // Configuration
    // ..[JavaLoginConfig]

    // KeyAgreement
    // ..[DH, ECDH]
    // ..........DH=[DiffieHellman]

    public static KeyFactoryBuilder builder(final KeyFactoryAlgorithm algorithm)
            throws NoSuchAlgorithmException, InstanceException {
        return new KeyFactoryBuilder(algorithm);
    }

    public static KeyGeneratorBuilder builder(final KeyGeneratorAlgorithm algorithm)
            throws NoSuchAlgorithmException, InstanceException {
        return new KeyGeneratorBuilder(algorithm);
    }

    // KeyInfoFactory
    // ..[DOM]

    public static KeyManagerFactoryBuilder builder(final KeyManagerFactoryAlgorithm algorithm)
            throws NoSuchAlgorithmException, InstanceException {
        return new KeyManagerFactoryBuilder(algorithm);
    }

    public static KeyPairGeneratorBuilder builder(final KeyPairGeneratorAlgorithm algorithm)
            throws NoSuchAlgorithmException, InstanceException {
        return new KeyPairGeneratorBuilder(algorithm);
    }

    public static KeyStoreBuilder builder(final KeyStoreAlgorithm algorithm)
            throws NoSuchAlgorithmException, InstanceException {
        return new KeyStoreBuilder(algorithm);
    }

    public static MacBuilder builder(final MacAlgorithm algorithm) throws NoSuchAlgorithmException, InstanceException {
        return new MacBuilder(algorithm);
    }

    public static MessageDigestBuilder builder(final MessageDigestAlgorithm algorithm)
            throws NoSuchAlgorithmException, InstanceException {
        return new MessageDigestBuilder(algorithm);
    }

    // Policy
    // ..[JavaPolicy]

    public static SslContextBuilder builder(final SslContextAlgorithm algorithm)
            throws NoSuchAlgorithmException, InstanceException {
        return new SslContextBuilder(algorithm);
    }

    // SaslClientFactory
    // ..[CRAM-MD5, DIGEST-MD5, EXTERNAL, GSSAPI, NTLM, PLAIN]

    // SaslServerFactory
    // ..[CRAM-MD5, DIGEST-MD5, GSSAPI, NTLM]

    public static SecretKeyFactoryBuilder builder(final SecretKeyFactoryAlgorithm algorithm)
            throws NoSuchAlgorithmException, InstanceException {
        return new SecretKeyFactoryBuilder(algorithm);
    }

    public static SecureRandomBuilder builder(final SecureRandomAlgorithm algorithm)
            throws NoSuchAlgorithmException, InstanceException {
        return new SecureRandomBuilder(algorithm);
    }

    public static SignatureBuilder builder(final SignatureAlgorithm algorithm)
            throws NoSuchAlgorithmException, InstanceException {
        return new SignatureBuilder(algorithm);
    }

    // TerminalFactory
    // ..[PC/SC]

    public static TrustManagerFactoryBuilder builder(final TrustManagerFactoryAlgorithm algorithm)
            throws NoSuchAlgorithmException, InstanceException {
        return new TrustManagerFactoryBuilder(algorithm);
    }

    // XMLSignatureFactory
    // ..[DOM]
}
