package io.github.dbstarll.utils.lang.bytes;

/**
 * 基于Hex编码的转换器实现.
 *
 * @author dbstar
 */
public class HexByteArrayConvertor implements ByteArrayConvertor {
    @Override
    public byte[] toByteArray(final String value) {
        return BytesUtils.decodeHexString(value);
    }

    @Override
    public String toString(final byte[] value) {
        return BytesUtils.encodeHexString(value);
    }
}
