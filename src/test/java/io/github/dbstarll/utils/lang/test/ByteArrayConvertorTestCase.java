package io.github.dbstarll.utils.lang.test;

import io.github.dbstarll.utils.lang.bytes.ByteArrayConvertor;
import junit.framework.TestCase;
import org.apache.commons.lang3.RandomUtils;

import java.util.Arrays;

public abstract class ByteArrayConvertorTestCase extends TestCase {
  protected ByteArrayConvertor convertor;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    this.convertor = buildByteArrayConvertor();
  }

  protected abstract ByteArrayConvertor buildByteArrayConvertor();

  @Override
  protected void tearDown() throws Exception {
    this.convertor = null;
    super.tearDown();
  }

  /**
   * 测试{@link ByteArrayConvertor#toString(byte[])}对null参数的处理.
   */
  public void testToStringNull() {
    assertNull(convertor.toString(null));
  }

  /**
   * 测试{@link ByteArrayConvertor#toString(byte[])}对空字节数组的处理.
   */
  public void testToStringEmpty() {
    assertEquals("", convertor.toString(new byte[0]));
  }

  /**
   * 测试{@link ByteArrayConvertor#toByteArray(String)}对null参数的处理.
   */
  public void testToByteArrayNull() {
    assertNull(convertor.toByteArray(null));
  }

  /**
   * 测试{@link ByteArrayConvertor#toByteArray(String)}对空字符串的处理.
   */
  public void testToByteArrayEmpty() {
    assertTrue(Arrays.equals(new byte[0], convertor.toByteArray("")));
  }

  /**
   * 测试数据的转换与还原.
   */
  public void testConvert() {
    byte[] bts = RandomUtils.nextBytes(1024);

    String str = convertor.toString(bts);
    assertNotNull(str);

    byte[] bts2 = convertor.toByteArray(str);
    assertTrue(Arrays.equals(bts, bts2));
    assertNotSame(bts, bts2);

    String str2 = convertor.toString(bts2);
    assertEquals(str, str2);
    assertNotSame(str, str2);
  }
}
