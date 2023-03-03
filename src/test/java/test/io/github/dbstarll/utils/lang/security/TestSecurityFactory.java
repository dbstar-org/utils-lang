package test.io.github.dbstarll.utils.lang.security;

import io.github.dbstarll.utils.lang.security.CertificateFactoryAlgorithm;
import io.github.dbstarll.utils.lang.security.CipherAlgorithm;
import io.github.dbstarll.utils.lang.security.CipherAlgorithmMode;
import io.github.dbstarll.utils.lang.security.CipherAlgorithmPadding;
import io.github.dbstarll.utils.lang.security.InstanceException;
import io.github.dbstarll.utils.lang.security.KeyFactoryAlgorithm;
import io.github.dbstarll.utils.lang.security.KeyGeneratorAlgorithm;
import io.github.dbstarll.utils.lang.security.KeyManagerFactoryAlgorithm;
import io.github.dbstarll.utils.lang.security.KeyPairGeneratorAlgorithm;
import io.github.dbstarll.utils.lang.security.KeyStoreAlgorithm;
import io.github.dbstarll.utils.lang.security.MacAlgorithm;
import io.github.dbstarll.utils.lang.security.MessageDigestAlgorithm;
import io.github.dbstarll.utils.lang.security.SecretKeyFactoryAlgorithm;
import io.github.dbstarll.utils.lang.security.SecureRandomAlgorithm;
import io.github.dbstarll.utils.lang.security.SecurityFactory;
import io.github.dbstarll.utils.lang.security.SignatureAlgorithm;
import io.github.dbstarll.utils.lang.security.SslContextAlgorithm;
import io.github.dbstarll.utils.lang.security.TrustManagerFactoryAlgorithm;
import junit.framework.TestCase;

import java.security.NoSuchAlgorithmException;

public class TestSecurityFactory extends TestCase {
    public void testCertificateFactoryBuilder() throws InstanceException, NoSuchAlgorithmException {
        assertNotNull(SecurityFactory.builder(CertificateFactoryAlgorithm.X509).build());
    }

    public void testCipherBuilder() throws InstanceException, NoSuchAlgorithmException {
        assertNotNull(SecurityFactory.builder(CipherAlgorithm.AES, CipherAlgorithmMode.CBC, CipherAlgorithmPadding.PKCS5_PADDING).build());
    }

    public void testKeyFactoryBuilder() throws InstanceException, NoSuchAlgorithmException {
        assertNotNull(SecurityFactory.builder(KeyFactoryAlgorithm.RSA).build());
    }

    public void testKeyGeneratorBuilder() throws InstanceException, NoSuchAlgorithmException {
        assertNotNull(SecurityFactory.builder(KeyGeneratorAlgorithm.AES).build());
    }

    public void testKeyManagerFactoryBuilder() throws InstanceException, NoSuchAlgorithmException {
        assertNotNull(SecurityFactory.builder(KeyManagerFactoryAlgorithm.SUN_X509).build());
    }

    public void testKeyPairGeneratorBuilder() throws InstanceException, NoSuchAlgorithmException {
        assertNotNull(SecurityFactory.builder(KeyPairGeneratorAlgorithm.RSA).keySize(2048,
                SecurityFactory.builder(SecureRandomAlgorithm.SHA1_PRNG).build()).build());
    }

    public void testKeyStoreBuilder() throws InstanceException, NoSuchAlgorithmException {
        assertNotNull(SecurityFactory.builder(KeyStoreAlgorithm.PKCS12).build());
    }

    public void testMacBuilder() throws InstanceException, NoSuchAlgorithmException {
        assertNotNull(SecurityFactory.builder(MacAlgorithm.HMAC_SHA1).build());
    }

    public void testMessageDigestBuilder() throws InstanceException, NoSuchAlgorithmException {
        assertNotNull(SecurityFactory.builder(MessageDigestAlgorithm.SHA256).build());
    }

    public void testSslContextBuilder() throws InstanceException, NoSuchAlgorithmException {
        assertNotNull(SecurityFactory.builder(SslContextAlgorithm.TLS_V12).build());
    }

    public void testSecretKeyFactoryBuilder() throws InstanceException, NoSuchAlgorithmException {
        assertNotNull(SecurityFactory.builder(SecretKeyFactoryAlgorithm.PBKDF2_WITH_HMAC_SHA1).build());
    }

    public void testSecureRandomBuilder() throws InstanceException, NoSuchAlgorithmException {
        assertNotNull(SecurityFactory.builder(SecureRandomAlgorithm.SHA1_PRNG).build());
    }

    public void testSignatureBuilder() throws InstanceException, NoSuchAlgorithmException {
        assertNotNull(SecurityFactory.builder(SignatureAlgorithm.SHA256_WITH_RSA).build());
    }

    public void testTrustManagerFactoryBuilder() throws InstanceException, NoSuchAlgorithmException {
        assertNotNull(SecurityFactory.builder(TrustManagerFactoryAlgorithm.SUN_X509).build());
    }
}
