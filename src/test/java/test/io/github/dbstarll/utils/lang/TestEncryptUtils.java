package test.io.github.dbstarll.utils.lang;

import io.github.dbstarll.utils.lang.EncryptUtils;
import io.github.dbstarll.utils.lang.bytes.Bytes;
import junit.framework.TestCase;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotEquals;

public class TestEncryptUtils extends TestCase {
    /**
     * 测试{@link EncryptUtils#md5(byte[])}.
     *
     * @throws NoSuchAlgorithmException NoSuchAlgorithmException
     */
    public void testMd5() throws NoSuchAlgorithmException {
        byte[] bt = new byte[]{1, 2, 3, 4};
        byte[] e1 = EncryptUtils.md5(bt);
        byte[] e2 = EncryptUtils.md5(bt);
        assertArrayEquals(e1, e2);
        assertNotSame(e1, e2);
    }

    /**
     * 测试{@link EncryptUtils#sha(byte[], int)}.
     *
     * @throws NoSuchAlgorithmException NoSuchAlgorithmException
     */
    public void testSha1() throws NoSuchAlgorithmException {
        byte[] bt = new byte[]{1, 2, 3, 4};
        byte[] e1 = EncryptUtils.sha(bt, 1);
        byte[] e2 = EncryptUtils.sha(bt, 1);
        assertArrayEquals(e1, e2);
        assertNotSame(e1, e2);
    }

    /**
     * 测试{@link EncryptUtils#sha(String, int)}.
     *
     * @throws NoSuchAlgorithmException NoSuchAlgorithmException
     */
    public void testSha1String() throws NoSuchAlgorithmException {
        String str = "testSha1String";
        byte[] e1 = EncryptUtils.sha(str, 1);
        byte[] e2 = EncryptUtils.sha(str, 1);
        assertArrayEquals(e1, e2);
        assertNotSame(e1, e2);

        try {
            EncryptUtils.sha(str, 1024);
            fail("throw NoSuchAlgorithmException");
        } catch (NoSuchAlgorithmException ex) {
            assertNotNull(ex);
        }
    }

    /**
     * 测试
     * {@link EncryptUtils#getEncryptedByte(byte, Bytes, long)}.
     */
    public void testEncryptedByte() {
        Bytes key = new Bytes(new byte[]{64, 84});
        byte b1 = 30;
        byte b2 = EncryptUtils.getEncryptedByte(b1, key, 0);

        // change
        assertNotEquals(b1, b2);

        // same for same key
        assertEquals(b2, EncryptUtils.getEncryptedByte(b1, key, 0));
        assertEquals(b2, EncryptUtils.getEncryptedByte(b1, key, 0));

        // same fot Encrypt twice
        byte b3 = EncryptUtils.getEncryptedByte(b2, key, 0);
        assertEquals(b1, b3);
    }

    /**
     * 测试{@link EncryptUtils#encryptCopy(byte[], Bytes)}.
     *
     * @throws NoSuchAlgorithmException NoSuchAlgorithmException
     */
    public void testEncryptCopy() throws NoSuchAlgorithmException {
        Bytes key = new Bytes(EncryptUtils.sha("testEncryptedBytes", 1));
        byte[] b1 = EncryptUtils.sha("NoSuchAlgorithmException", 256);
        byte[] b2 = EncryptUtils.encryptCopy(b1, key);

        // change
        assertFalse(Arrays.equals(b1, b2));
        assertNotSame(b1, b2);

        // same for same key
        assertArrayEquals(b2, EncryptUtils.encryptCopy(b1, key));
        assertArrayEquals(b2, EncryptUtils.encryptCopy(b1, key));

        // same fot Encrypt twice
        byte[] b3 = EncryptUtils.encryptCopy(b2, key);
        assertArrayEquals(b1, b3);
        assertNotSame(b1, b3);

        // null
        assertNull(EncryptUtils.encryptCopy(null, key));
    }

    /**
     * 测试{@link EncryptUtils#encryptReplace(byte[], Bytes)}.
     *
     * @throws NoSuchAlgorithmException NoSuchAlgorithmException
     */
    public void testEncryptReplace() throws NoSuchAlgorithmException {
        Bytes key = new Bytes(EncryptUtils.sha("testEncryptedBytes", 1));
        byte[] b1 = EncryptUtils.sha("NoSuchAlgorithmException", 256);
        byte[] b2 = EncryptUtils.encryptReplace(b1, key);

        // change
        assertFalse(Arrays.equals(b1, EncryptUtils.sha("NoSuchAlgorithmException", 256)));
        assertSame(b1, b2);

        // same fot Encrypt twice
        byte[] b3 = EncryptUtils.encryptReplace(b2, key);
        assertArrayEquals(b1, EncryptUtils.sha("NoSuchAlgorithmException", 256));
        assertSame(b1, b3);

        // null
        assertNull(EncryptUtils.encryptReplace(null, key));
    }
}
