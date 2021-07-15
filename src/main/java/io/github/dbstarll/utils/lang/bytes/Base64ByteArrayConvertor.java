package io.github.dbstarll.utils.lang.bytes;

/**
 * 基于Base64编码的转换器实现.
 *
 * @author dbstar
 */
public class Base64ByteArrayConvertor implements ByteArrayConvertor {
  private final boolean urlSafe;

  /**
   * 构建一个基于Base64编码的转换器实现.
   *
   * @param urlSafe if <code>true</code> this encoder will emit - and _ instead of the usual + and /
   *                characters. <b>Note: no padding is added when encoding using the URL-safe
   *                alphabet.</b>
   */
  public Base64ByteArrayConvertor(boolean urlSafe) {
    this.urlSafe = urlSafe;
  }

  @Override
  public byte[] toByteArray(String value) {
    return BytesUtils.decodeBase64String(value);
  }

  @Override
  public String toString(byte[] value) {
    return BytesUtils.encodeBase64String(value, urlSafe);
  }
}
