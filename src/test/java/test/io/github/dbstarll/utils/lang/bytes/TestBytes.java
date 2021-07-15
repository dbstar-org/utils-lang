package test.io.github.dbstarll.utils.lang.bytes;

import io.github.dbstarll.utils.lang.StandardCharsets;
import io.github.dbstarll.utils.lang.bytes.Bytes;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TestBytes extends TestCase {
    /**
     * 测试正常参数的{@link Bytes#Bytes(byte[])}.
     */
    public void testCreateByBypeArrayNormal() {
        byte[] data = new byte[]{1, 2, 3};
        Bytes bytes = new Bytes(data);
        assertTrue(Arrays.equals(data, bytes.get()));
        assertFalse(data == bytes.get());
        assertEquals(3, bytes.length());
        assertEquals(Arrays.hashCode(data), bytes.hashCode());
    }

    /**
     * 测试null的{@link Bytes#Bytes(byte[])}.
     */
    public void testCreateByBypeArrayNull() {
        byte[] data = null;
        Bytes bytes = new Bytes(data);
        assertTrue(Arrays.equals(data, bytes.get()));
        assertNull(bytes.get());
        assertEquals(-1, bytes.length());
        assertEquals(Arrays.hashCode(data), bytes.hashCode());
    }

    /**
     * 测试0长度字节数组的{@link Bytes#Bytes(byte[])}.
     */
    public void testCreateByBypeArrayEmpty() {
        byte[] data = new byte[]{};
        Bytes bytes = new Bytes(data);
        assertTrue(Arrays.equals(data, bytes.get()));
        assertFalse(data == bytes.get());
        assertEquals(0, bytes.length());
        assertEquals(Arrays.hashCode(data), bytes.hashCode());
    }

    /**
     * 测试正常参数的{@link Bytes#Bytes(byte[], int, int)}.
     */
    public void testCreateByBypeArrayRangeNormal() {
        byte[] data = new byte[]{1, 2, 3};
        Bytes bytes = new Bytes(data, 0, data.length);
        assertTrue(Arrays.equals(data, bytes.get()));
        assertFalse(data == bytes.get());
        assertEquals(3, bytes.length());
        assertEquals(Arrays.hashCode(data), bytes.hashCode());
    }

    /**
     * 测试null的{@link Bytes#Bytes(byte[], int, int)}.
     */
    public void testCreateByBypeArrayRangeNull() {
        byte[] data = null;
        Bytes bytes = new Bytes(data, 0, 10);
        assertTrue(Arrays.equals(data, bytes.get()));
        assertNull(bytes.get());
        assertEquals(-1, bytes.length());
        assertEquals(Arrays.hashCode(data), bytes.hashCode());
    }

    /**
     * 测试0长度字节数组的{@link Bytes#Bytes(byte[], int, int)}.
     */
    public void testCreateByBypeArrayRangeEmpty() {
        byte[] data = new byte[]{};
        Bytes bytes = new Bytes(data, 0, 0);
        assertTrue(Arrays.equals(data, bytes.get()));
        assertFalse(data == bytes.get());
        assertEquals(0, bytes.length());
        assertEquals(Arrays.hashCode(data), bytes.hashCode());
    }

    /**
     * 测试{@link Bytes#Bytes(byte[], int, int)}的异常参数.
     */
    public void testCreateByBypeArrayRangeError() {
        byte[] data = new byte[]{1, 2, 3};

        try {
            new Bytes(data, -1, 2);
            fail("throw ArrayIndexOutOfBoundsException");
        } catch (ArrayIndexOutOfBoundsException ex) {
            assertNotNull(ex);
        }

        try {
            new Bytes(data, 4, 5);
            fail("throw ArrayIndexOutOfBoundsException");
        } catch (ArrayIndexOutOfBoundsException ex) {
            assertNotNull(ex);
        }

        try {
            new Bytes(data, 2, 1);
            fail("throw IllegalArgumentException");
        } catch (IllegalArgumentException ex) {
            assertNotNull(ex);
        }
    }

    /**
     * 测试正常参数的{@link Bytes#Bytes(String)}.
     */
    public void testCreateByStringNormal() {
        String data = "123";
        Bytes bytes = new Bytes(data);
        assertTrue(Arrays.equals(data.getBytes(StandardCharsets.UTF_8), bytes.get()));
        assertEquals(3, bytes.length());
        assertEquals(Arrays.hashCode(data.getBytes(StandardCharsets.UTF_8)), bytes.hashCode());
    }

    /**
     * 测试null的{@link Bytes#Bytes(String)}.
     */
    public void testCreateByStringNull() {
        String data = null;
        Bytes bytes = new Bytes(data);
        assertNull(bytes.get());
        assertEquals(-1, bytes.length());
        assertEquals(0, bytes.hashCode());
    }

    /**
     * 测试0长度字符串的{@link Bytes#Bytes(String)}.
     */
    public void testCreateByStringEmpty() {
        String data = "";
        Bytes bytes = new Bytes(data);
        assertTrue(Arrays.equals(data.getBytes(StandardCharsets.UTF_8), bytes.get()));
        assertEquals(0, bytes.length());
        assertEquals(Arrays.hashCode(data.getBytes(StandardCharsets.UTF_8)), bytes.hashCode());
    }

    /**
     * 测试对{@link Bytes#Bytes(byte[])}构建后的{@link Bytes#get(int)}.
     */
    public void testGetNormal() {
        byte[] data = new byte[]{1, 2, 3, 4};
        Bytes bytes = new Bytes(data);
        assertFalse(data == bytes.get());
        assertEquals(4, bytes.length());
        assertEquals(data[0], bytes.get(0));
        assertEquals(data[1], bytes.get(1));
        assertEquals(data[2], bytes.get(2));
        assertEquals(data[3], bytes.get(3));
    }

    /**
     * 测试对{@link Bytes#Bytes(byte[], int, int)}构建后的{@link Bytes#get(int)}.
     */
    public void testGetNormalSub() {
        byte[] data = new byte[]{1, 2, 3, 4};
        Bytes bytes = new Bytes(data, 1, 3);
        assertFalse(data == bytes.get());
        assertEquals(2, bytes.length());
        assertEquals(data[1], bytes.get(0));
        assertEquals(data[2], bytes.get(1));
    }

    /**
     * 测试对封装null的Bytes的{@link Bytes#get(int)}异常.
     */
    public void testGetErrorNull() {
        Bytes bytes = Bytes.NULL;
        try {
            bytes.get(0);
            fail("throw NullPointerException");
        } catch (NullPointerException ex) {
            assertNotNull(ex);
        }
    }

    /**
     * 测试{@link Bytes#get(int)}的异常参数处理.
     */
    public void testGetErrorOut() {
        Bytes bytes = new Bytes(new byte[]{1, 2, 3, 4});

        try {
            bytes.get(-1);
            fail("throw ArrayIndexOutOfBoundsException");
        } catch (ArrayIndexOutOfBoundsException ex) {
            assertNotNull(ex);
        }

        try {
            bytes.get(5);
            fail("throw ArrayIndexOutOfBoundsException");
        } catch (ArrayIndexOutOfBoundsException ex) {
            assertNotNull(ex);
        }
    }

    /**
     * 测试{@link Bytes#equals(Object)}方法.
     */
    public void testEquals() {
        Bytes b1 = new Bytes(new byte[]{1, 2, 3, 4});
        Bytes b2 = new Bytes(new byte[]{1, 2, 3, 4});

        assertTrue(b1.equals(b1));
        assertTrue(b1.equals(b2));
        assertFalse(b1 == b2);

        assertFalse(b1.equals(null));
        assertFalse(b1.equals("b2"));

        Bytes b3 = new Bytes(new byte[]{1, 2, 3, 3});
        assertFalse(b1.equals(b3));
    }

    /**
     * 测试{@link Bytes#compareTo(Bytes)}方法.
     */
    public void testCompare() {
        Bytes b1 = new Bytes(new byte[]{1, 2, 3, 4});
        Bytes b2 = new Bytes(new byte[]{1, 2, 3, 4});

        assertEquals(0, b1.compareTo(b1));
        assertEquals(0, b1.compareTo(b2));
        assertEquals(0, b2.compareTo(b1));

        Bytes bn1 = Bytes.NULL;
        assertEquals(1, b1.compareTo(bn1));
        assertEquals(-1, bn1.compareTo(b1));

        Bytes bn2 = Bytes.NULL;
        assertEquals(0, bn1.compareTo(bn2));
        assertEquals(0, bn2.compareTo(bn1));

        Bytes b3 = new Bytes(new byte[]{1, 2, 3});
        assertEquals(1, b1.compareTo(b3));
        assertEquals(-1, b3.compareTo(b1));

        Bytes b4 = new Bytes(new byte[]{1, 2, 3, 3});
        assertEquals(1, b1.compareTo(b4));
        assertEquals(-1, b4.compareTo(b1));
    }

    /**
     * 通过Collection排序来测试{@link Bytes#compareTo(Bytes)}.
     */
    public void testCompareByCollection() {
        List<Bytes> list = new ArrayList<Bytes>(6);

        Bytes b1 = new Bytes(new byte[]{1, 2, 3, 4});
        Bytes b2 = new Bytes(new byte[]{1, 2, 3, 4});
        list.add(b1);
        list.add(b2);

        Bytes bn1 = Bytes.NULL;
        Bytes bn2 = Bytes.NULL;
        list.add(bn1);
        list.add(bn2);

        Bytes b3 = new Bytes(new byte[]{1, 2, 3});
        Bytes b4 = new Bytes(new byte[]{1, 2, 3, 3});
        list.add(b3);
        list.add(b4);

        assertEquals(6, list.size());
        Collections.sort(list);
        assertEquals(6, list.size());

        assertEquals(bn1, list.get(0));
        assertEquals(bn2, list.get(1));
        assertEquals(b3, list.get(2));
        assertEquals(b4, list.get(3));
        assertEquals(b1, list.get(4));
        assertEquals(b2, list.get(5));
    }
}
