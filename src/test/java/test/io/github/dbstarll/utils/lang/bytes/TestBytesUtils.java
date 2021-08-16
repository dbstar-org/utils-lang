package test.io.github.dbstarll.utils.lang.bytes;

import io.github.dbstarll.utils.lang.bytes.BytesUtils;
import junit.framework.TestCase;

import java.util.Arrays;
import java.util.BitSet;

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

    /**
     * 测试{@link BytesUtils#double2Bits(int, double, double, double)}.
     */
    public void testDouble2Bits() {
        final double value = 130;
        final double floor = 70;
        final double ceiling = 140;

        assertEquals(0, BytesUtils.double2Bits(0, value, floor, ceiling).length());
        assertEquals(0, BytesUtils.double2Bits(24, floor, floor, ceiling).length());
        assertEquals(24, BytesUtils.double2Bits(24, ceiling, floor, ceiling).length());
        assertEquals(24, BytesUtils.double2Bits(24, ceiling, floor, ceiling).cardinality());

        assertEquals(16, BytesUtils.double2Bits(24, value, floor, ceiling).cardinality());
        assertEquals(19, BytesUtils.double2Bits(28, value, floor, ceiling).cardinality());
        assertEquals(22, BytesUtils.double2Bits(32, value, floor, ceiling).cardinality());

        assertEquals(BytesUtils.double2Bits(24, value, floor, ceiling),
                BytesUtils.double2Bits(24, value, ceiling, floor));
    }

    /**
     * 测试{@link BytesUtils#double2Bits(int, double, double, double)}的参数异常.
     */
    public void testDouble2BitsException() {
        final double value = 129;
        final double floor = 70;
        final double ceiling = 140;

        try {
            BytesUtils.double2Bits(-1, value, floor, ceiling);
            fail("nbits < 0: -1");
        } catch (IllegalArgumentException ex) {
            assertEquals("nbits < 0: -1", ex.getMessage());
        }

        try {
            BytesUtils.double2Bits(24, floor - 1, floor, ceiling);
            fail("value out of range [70.0, 140.0]: 69.0");
        } catch (IllegalArgumentException ex) {
            assertEquals("value out of range [70.0, 140.0]: 69.0", ex.getMessage());
        }

        try {
            BytesUtils.double2Bits(24, ceiling + 1, floor, ceiling);
            fail("value out of range [70.0, 140.0]: 141.0");
        } catch (IllegalArgumentException ex) {
            assertEquals("value out of range [70.0, 140.0]: 141.0", ex.getMessage());
        }
    }

    /**
     * 测试{@link BytesUtils#bits2Double(int, java.util.BitSet, double, double)}.
     */
    public void testBits2Double() {
        final double value = 130;
        final double floor = 70;
        final double ceiling = 140;

        assertTrue(100000 * Math.abs(value - BytesUtils.bits2Double(0,
                BytesUtils.double2Bits(0, value, floor, ceiling), floor, ceiling)) <= 2500000.0);
        assertTrue(100000 * Math.abs(value - BytesUtils.bits2Double(24,
                BytesUtils.double2Bits(24, value, floor, ceiling), floor, ceiling)) < 1);
        assertTrue(100000 * Math.abs(value - BytesUtils.bits2Double(28,
                BytesUtils.double2Bits(28, value, floor, ceiling), floor, ceiling)) < 0.01);
        assertTrue(100000 * Math.abs(value - BytesUtils.bits2Double(32,
                BytesUtils.double2Bits(32, value, floor, ceiling), floor, ceiling)) < 0.0002);

        assertTrue(100000 * Math.abs(floor - BytesUtils.bits2Double(24,
                BytesUtils.double2Bits(24, floor, floor, ceiling), floor, ceiling)) < 1);
        assertTrue(100000 * Math.abs(ceiling - BytesUtils.bits2Double(24,
                BytesUtils.double2Bits(24, ceiling, floor, ceiling), floor, ceiling)) < 1);

        BitSet bs = BytesUtils.double2Bits(24, value, floor, ceiling);
        assertEquals(BytesUtils.bits2Double(24, bs, floor, ceiling),
                BytesUtils.bits2Double(24, bs, ceiling, floor));
    }

    /**
     * 测试{@link BytesUtils#bits2Double(int, java.util.BitSet, double, double)}的参数异常.
     */
    public void testBits2DoubleException() {
        final double value = 129;
        final double floor = 70;
        final double ceiling = 140;
        final BitSet bs = BytesUtils.double2Bits(24, value, floor, ceiling);

        try {
            BytesUtils.bits2Double(-1, bs, floor, ceiling);
            fail("nbits < 0: -1");
        } catch (IllegalArgumentException ex) {
            assertEquals("nbits < 0: -1", ex.getMessage());
        }

        try {
            BytesUtils.bits2Double(24, null, floor, ceiling);
            fail("bs is null");
        } catch (IllegalArgumentException ex) {
            assertEquals("bs is null", ex.getMessage());
        }
    }

    /**
     * 测试{@link BytesUtils#decodeBitSet(int, BitSet)}.
     */
    public void testDecodeBitSet() {
        final double value = 129;
        final double floor = 70;
        final double ceiling = 140;
        final BitSet bs = BytesUtils.double2Bits(24, value, floor, ceiling);

        assertEquals(0, BytesUtils.decodeBitSet(0, bs).length);
        assertEquals(1, BytesUtils.decodeBitSet(8, bs).length);
        assertEquals(2, BytesUtils.decodeBitSet(16, bs).length);
        assertEquals(3, BytesUtils.decodeBitSet(20, bs).length);
        assertEquals(3, BytesUtils.decodeBitSet(24, bs).length);
        assertEquals(4, BytesUtils.decodeBitSet(28, bs).length);
        assertEquals(4, BytesUtils.decodeBitSet(32, bs).length);

        assertEquals(3, BytesUtils.decodeBitSet(24, null).length);

        try {
            BytesUtils.decodeBitSet(-1, bs);
            fail("nbits < 0: -1");
        } catch (IllegalArgumentException ex) {
            assertEquals("nbits < 0: -1", ex.getMessage());
        }
    }

    /**
     * 测试{@link BytesUtils#encodeBitSet(byte[])}.
     */
    public void testEncodeBitSet() {
        final double value = 129;
        final double floor = 70;
        final double ceiling = 140;
        final BitSet bs = BytesUtils.double2Bits(24, value, floor, ceiling);

        assertEquals(bs.get(0, 0), BytesUtils.encodeBitSet(BytesUtils.decodeBitSet(0, bs)));
        assertEquals(bs.get(0, 8), BytesUtils.encodeBitSet(BytesUtils.decodeBitSet(8, bs)));
        assertEquals(bs.get(0, 16), BytesUtils.encodeBitSet(BytesUtils.decodeBitSet(16, bs)));
        assertEquals(bs.get(0, 20), BytesUtils.encodeBitSet(BytesUtils.decodeBitSet(20, bs)));
        assertEquals(bs, BytesUtils.encodeBitSet(BytesUtils.decodeBitSet(24, bs)));
        assertEquals(bs, BytesUtils.encodeBitSet(BytesUtils.decodeBitSet(28, bs)));
        assertEquals(bs, BytesUtils.encodeBitSet(BytesUtils.decodeBitSet(32, bs)));

        assertEquals(bs.get(0, 0), BytesUtils.encodeBitSet(null));
    }
}
