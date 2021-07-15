package io.github.dbstarll.utils.lang.bytes;

/**
 * 处理字节数组与字符串之间的转换.
 *
 * @author dbstar
 */
public interface ByteArrayConvertor {
    /**
     * 字符串转换为字节数组.
     *
     * @param value 字符串
     * @return 字节数组
     */
    byte[] toByteArray(String value);

    /**
     * 字节数组转换为字符串.
     *
     * @param value 字节数组
     * @return 字符串
     */
    String toString(byte[] value);
}
