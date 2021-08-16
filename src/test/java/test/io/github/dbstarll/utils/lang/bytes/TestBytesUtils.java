package test.io.github.dbstarll.utils.lang.bytes;

import io.github.dbstarll.utils.lang.bytes.BytesUtils;
import junit.framework.TestCase;

import java.util.Arrays;

public class TestBytesUtils extends TestCase {
    /**
     * 测试{@link BytesUtils#compare(byte[], int, int, byte[], int, int)}.
     */
    public void testCompare() {
        byte[] bt = new byte[]{1, 2, 3, 4};
        assertEquals(0, BytesUtils.compare(bt, 0, 4, bt, 0, 4));
        assertEquals(0, BytesUtils.compare(bt, 1, 2, bt, 1, 2));
        assertEquals(-1, BytesUtils.compare(bt, 0, 3, bt, 0, 4));
        assertEquals(1, BytesUtils.compare(bt, 0, 4, bt, 0, 3));
        assertEquals(-1, BytesUtils.compare(bt, 0, 3, bt, 1, 3));
        assertEquals(1, BytesUtils.compare(bt, 1, 3, bt, 0, 3));
    }

    /**
     * 测试{@link BytesUtils#byte2int(byte)}.
     */
    public void testByte2int() {
        byte bt;
        assertEquals(0, BytesUtils.byte2int(bt = 0));
        assertEquals(1, BytesUtils.byte2int(bt = 1));
        assertEquals(64, BytesUtils.byte2int(bt = 64));
        assertEquals(127, BytesUtils.byte2int(bt = 127));
        assertEquals(128, BytesUtils.byte2int(bt = -128));
        assertEquals(192, BytesUtils.byte2int(bt = -64));
        assertEquals(254, BytesUtils.byte2int(bt = -2));
        assertEquals(255, BytesUtils.byte2int(bt = -1));
        assertEquals(-1, bt);
    }

    /**
     * 测试字节数组与十六进制字符串之间的转换.
     */
    public void testHexString() {
        byte[] bt = new byte[]{0, 1, 64, 127, -128, -64, -2, -1};
        assertEquals("0001407f80c0feff", BytesUtils.encodeHexString(bt));
        assertTrue(Arrays.equals(bt, BytesUtils.decodeHexString("0001407f80c0feff")));
        assertTrue(Arrays.equals(bt, BytesUtils.decodeHexString("0001407F80C0FEFF")));

        assertNull(BytesUtils.encodeHexString(null));
        assertNull(BytesUtils.decodeHexString(null));

        try {
            BytesUtils.decodeHexString("1407f80c0feff");
            fail("throw IllegalArgumentException");
        } catch (IllegalArgumentException ex) {
            assertNotNull(ex);
        }

        try {
            BytesUtils.decodeHexString("gh");
            fail("throw IllegalArgumentException");
        } catch (IllegalArgumentException ex) {
            assertNotNull(ex);
        }
    }

    /**
     * 测试字节数组与Base64字符串之间的转换.
     */
    public void testBase64String() {
        byte[] bt = new byte[]{0, 1, 64, 127, -128, -64, -2, -1};
        assertEquals("AAFAf4DA/v8=", BytesUtils.encodeBase64String(bt, false));
        assertEquals("AAFAf4DA_v8", BytesUtils.encodeBase64String(bt, true));
        assertTrue(Arrays.equals(bt, BytesUtils.decodeBase64String("AAFAf4DA/v8=")));
        assertTrue(Arrays.equals(bt, BytesUtils.decodeBase64String("AAFAf4DA_v8")));

        assertNull(BytesUtils.encodeBase64String(null, false));
        assertNull(BytesUtils.encodeBase64String(null, true));
        assertNull(BytesUtils.decodeBase64String(null));
    }
}
