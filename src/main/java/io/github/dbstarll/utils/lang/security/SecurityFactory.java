package io.github.dbstarll.utils.lang.security;

import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Security;

public final class SecurityFactory {
    private SecurityFactory() {
        //隐藏构造器
    }

    /**
     * Adds a provider to the next position available.
     *
     * @param provider the provider to be added.
     * @return the preference position in which the provider was
     * added, or -1 if the provider was not added because it is
     * already installed.
     * @throws NullPointerException if provider is null
     * @throws SecurityException    if a security manager exists and its {@link
     *                              java.lang.SecurityManager#checkSecurityAccess} method
     *                              denies access to add a new provider
     */
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

    /**
     * 构造CertificateFactoryBuilder.
     *
     * @param algorithm CertificateFactoryAlgorithm
     * @return CertificateFactoryBuilder
     * @throws NoSuchAlgorithmException 算法不存在
     * @throws InstanceException        不能实例化
     */
    public static CertificateFactoryBuilder builder(final CertificateFactoryAlgorithm algorithm)
            throws NoSuchAlgorithmException, InstanceException {
        return new CertificateFactoryBuilder(algorithm);
    }

    /**
     * 构建CipherBuilder.
     *
     * @param algorithm 算法
     * @param mode      算法模式
     * @param padding   padding
     * @return CipherBuilder
     * @throws NoSuchAlgorithmException 算法不存在
     * @throws InstanceException        实例化失败
     */
    public static CipherBuilder builder(final CipherAlgorithm algorithm,
                                        final CipherAlgorithmMode mode,
                                        final CipherAlgorithmPadding padding)
            throws NoSuchAlgorithmException, InstanceException {
        return new CipherBuilder(algorithm, mode, padding);
    }

    // Configuration
    // ..[JavaLoginConfig]

    // KeyAgreement
    // ..[DH, ECDH]
    // ..........DH=[DiffieHellman]

    /**
     * 构造KeyFactoryBuilder.
     *
     * @param algorithm KeyFactoryAlgorithm
     * @return KeyFactoryBuilder
     * @throws NoSuchAlgorithmException 未知算法
     * @throws InstanceException        不能实例化
     */
    public static KeyFactoryBuilder builder(final KeyFactoryAlgorithm algorithm)
            throws NoSuchAlgorithmException, InstanceException {
        return new KeyFactoryBuilder(algorithm);
    }

    /**
     * 构造KeyGeneratorBuilder.
     *
     * @param algorithm KeyGeneratorAlgorithm
     * @return KeyGeneratorBuilder
     * @throws NoSuchAlgorithmException 未知算法
     * @throws InstanceException        不能实例化
     */
    public static KeyGeneratorBuilder builder(final KeyGeneratorAlgorithm algorithm)
            throws NoSuchAlgorithmException, InstanceException {
        return new KeyGeneratorBuilder(algorithm);
    }

    // KeyInfoFactory
    // ..[DOM]

    /**
     * 构造KeyManagerFactoryBuilder.
     *
     * @param algorithm KeyManagerFactoryAlgorithm
     * @return KeyManagerFactoryBuilder
     * @throws NoSuchAlgorithmException 未知算法
     * @throws InstanceException        不能实例化
     */
    public static KeyManagerFactoryBuilder builder(final KeyManagerFactoryAlgorithm algorithm)
            throws NoSuchAlgorithmException, InstanceException {
        return new KeyManagerFactoryBuilder(algorithm);
    }

    /**
     * 构造KeyPairGeneratorBuilder.
     *
     * @param algorithm KeyPairGeneratorAlgorithm
     * @return KeyPairGeneratorBuilder
     * @throws NoSuchAlgorithmException 未知算法
     * @throws InstanceException        不能实例化
     */
    public static KeyPairGeneratorBuilder builder(final KeyPairGeneratorAlgorithm algorithm)
            throws NoSuchAlgorithmException, InstanceException {
        return new KeyPairGeneratorBuilder(algorithm);
    }

    /**
     * 构造KeyStoreBuilder.
     *
     * @param algorithm KeyStoreAlgorithm
     * @return KeyStoreBuilder
     * @throws NoSuchAlgorithmException 未知算法
     * @throws InstanceException        不能实例化
     */
    public static KeyStoreBuilder builder(final KeyStoreAlgorithm algorithm)
            throws NoSuchAlgorithmException, InstanceException {
        return new KeyStoreBuilder(algorithm);
    }

    /**
     * 构造MacBuilder.
     *
     * @param algorithm MacAlgorithm
     * @return MacBuilder
     * @throws NoSuchAlgorithmException 未知算法
     * @throws InstanceException        不能实例化
     */
    public static MacBuilder builder(final MacAlgorithm algorithm) throws NoSuchAlgorithmException, InstanceException {
        return new MacBuilder(algorithm);
    }

    /**
     * 构造MessageDigestBuilder.
     *
     * @param algorithm 算法
     * @return MessageDigestBuilder
     * @throws NoSuchAlgorithmException 未知算法
     * @throws InstanceException        不能实例化
     */
    public static MessageDigestBuilder builder(final MessageDigestAlgorithm algorithm)
            throws NoSuchAlgorithmException, InstanceException {
        return new MessageDigestBuilder(algorithm);
    }

    // Policy
    // ..[JavaPolicy]

    /**
     * 构造SslContextBuilder.
     *
     * @param algorithm SslContextAlgorithm
     * @return SslContextBuilder
     * @throws NoSuchAlgorithmException 未知算法
     * @throws InstanceException        不能实例化
     */
    public static SslContextBuilder builder(final SslContextAlgorithm algorithm)
            throws NoSuchAlgorithmException, InstanceException {
        return new SslContextBuilder(algorithm);
    }

    // SaslClientFactory
    // ..[CRAM-MD5, DIGEST-MD5, EXTERNAL, GSSAPI, NTLM, PLAIN]

    // SaslServerFactory
    // ..[CRAM-MD5, DIGEST-MD5, GSSAPI, NTLM]

    /**
     * 构造SecretKeyFactoryBuilder.
     *
     * @param algorithm SecretKeyFactoryAlgorithm
     * @return SecretKeyFactoryBuilder
     * @throws NoSuchAlgorithmException 未知算法
     * @throws InstanceException        不能实例化
     */
    public static SecretKeyFactoryBuilder builder(final SecretKeyFactoryAlgorithm algorithm)
            throws NoSuchAlgorithmException, InstanceException {
        return new SecretKeyFactoryBuilder(algorithm);
    }

    /**
     * 构造SecureRandomBuilder.
     *
     * @param algorithm SecureRandomAlgorithm
     * @return SecureRandomBuilder
     * @throws NoSuchAlgorithmException 未知算法
     * @throws InstanceException        不能实例化
     */
    public static SecureRandomBuilder builder(final SecureRandomAlgorithm algorithm)
            throws NoSuchAlgorithmException, InstanceException {
        return new SecureRandomBuilder(algorithm);
    }

    /**
     * 构造SignatureBuilder.
     *
     * @param algorithm SignatureAlgorithm
     * @return SignatureBuilder
     * @throws NoSuchAlgorithmException 未知算法
     * @throws InstanceException        不能实例化
     */
    public static SignatureBuilder builder(final SignatureAlgorithm algorithm)
            throws NoSuchAlgorithmException, InstanceException {
        return new SignatureBuilder(algorithm);
    }

    // TerminalFactory
    // ..[PC/SC]

    /**
     * 构造TrustManagerFactoryBuilder.
     *
     * @param algorithm TrustManagerFactoryAlgorithm
     * @return TrustManagerFactoryBuilder
     * @throws NoSuchAlgorithmException 未知算法
     * @throws InstanceException        不能实例化
     */
    public static TrustManagerFactoryBuilder builder(final TrustManagerFactoryAlgorithm algorithm)
            throws NoSuchAlgorithmException, InstanceException {
        return new TrustManagerFactoryBuilder(algorithm);
    }

    // XMLSignatureFactory
    // ..[DOM]
}
