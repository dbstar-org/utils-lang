package test.io.github.dbstarll.utils.lang.security;

import io.github.dbstarll.utils.lang.digest.Md5Digestor;
import io.github.dbstarll.utils.lang.security.*;
import junit.framework.TestCase;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

public class TestCipherBuilder extends TestCase {
    public void testEncrypt() throws Exception {
        final byte[] slatBytes = new Md5Digestor().digest("slatKey".getBytes(StandardCharsets.UTF_8));
        final SecretKey secretKey = new SecretKeySpec(slatBytes, "AES");
        final byte[] ivBytes = new Md5Digestor().digest("vectorKey".getBytes(StandardCharsets.UTF_8));
        final IvParameterSpec iv = new IvParameterSpec(ivBytes);

        final SecureRandom random = SecurityFactory.builder(SecureRandomAlgorithm.SHA1PRNG).build();
        final Cipher encryptCipher = SecurityFactory
                .builder(CipherAlgorithm.AES, CipherAlgorithmMode.CBC, CipherAlgorithmPadding.PKCS5Padding)
                .build();
        encryptCipher.init(Cipher.ENCRYPT_MODE, secretKey, iv, random);

        final String content = "test it";
        final byte[] encrypted = encryptCipher.doFinal(content.getBytes(StandardCharsets.UTF_8));

        final Cipher decryptCipher = SecurityFactory
                .builder(CipherAlgorithm.AES, CipherAlgorithmMode.CBC, CipherAlgorithmPadding.PKCS5Padding)
                .build();
        decryptCipher.init(Cipher.DECRYPT_MODE, secretKey, iv, random);
        final byte[] decrypted = decryptCipher.doFinal(encrypted);

        assertEquals(content, new String(decrypted, StandardCharsets.UTF_8));
    }
}
