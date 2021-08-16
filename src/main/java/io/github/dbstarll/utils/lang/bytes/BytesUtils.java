package io.github.dbstarll.utils.lang.bytes;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

/**
 * 处理字节和字节数组相关的工具类.
 *
 * @author dbstar
 */
public final class BytesUtils {
    private static final int BYTE_MASK = 0xff;
    private static final int BYTE_MAX = 0x07f;
    private static final int BYTE_MIN = 0x80;

    private BytesUtils() {
        //隐藏构造器.
    }

    /**
     * 字节数组比较.
     *
     * @param bs1  待比较的第一个字节数组
     * @param off1 第一个字节数组的开始比较偏移位置
     * @param len1 第一个字节数组从偏移位置开始的待比较字节长度
     * @param bs2  待比较的第二个字节数组
     * @param off2 第二个字节数组的开始比较偏移位置
     * @param len2 第二个字节数组从偏移位置开始的待比较字节长度
     * @return a negative integer, zero, or a positive integer as buffer1 is less than, equal to, or
     * greater than buffer2.
     */
    public static int compare(final byte[] bs1, final int off1, final int len1,
                              final byte[] bs2, final int off2, final int len2) {
        // Short circuit equal case
        if (bs1 == bs2 && off1 == off2 && len1 == len2) {
            return 0;
        }
        // compare by Byte
        int end1 = off1 + len1;
        int end2 = off2 + len2;
        for (int i = off1, j = off2; i < end1 && j < end2; i++, j++) {
            int bytea = bs1[i] & BYTE_MASK;
            int byteb = bs2[j] & BYTE_MASK;
            if (bytea != byteb) {
                return bytea - byteb;
            }
        }
        return len1 - len2;
    }

    /**
     * 将byte转换为无符号整数.
     *
     * @param bt 待转换的byte
     * @return 转换后的无符号整数 
     */
    public static int byte2int(final byte bt) {
        int lb = bt & BYTE_MAX;
        if (bt < 0) {
            lb |= BYTE_MIN;
        }
        return lb;
    }

    /**
     * 将字节数组转换为十六进制字符串.
     *
     * @param data 待转换的字节数组
     * @return 对应的十六进制字符串
     */
    public static String encodeHexString(final byte[] data) {
        return data == null ? null : Hex.encodeHexString(data);
    }

    /**
     * 将十六进制字符串转换为字节数组.
     *
     * @param hexString 十六进制字符串
     * @return 对应的字节数组
     * @throws IllegalArgumentException Thrown if an odd number or illegal of characters is supplied
     */
    public static byte[] decodeHexString(final String hexString) throws IllegalArgumentException {
        try {
            return hexString == null ? null : Hex.decodeHex(hexString.toCharArray());
        } catch (DecoderException ex) {
            throw new IllegalArgumentException(ex);
        }
    }

    /**
     * 将字节数组转换为Base64字符串.
     *
     * @param data    待转换的字节数组
     * @param urlSafe if <code>true</code> this encoder will emit - and _ instead of the usual + and /
     *                characters. <b>Note: no padding is added when encoding using the URL-safe
     *                alphabet.</b>
     * @return Base64-encoded data.
     */
    public static String encodeBase64String(final byte[] data, final boolean urlSafe) {
        return urlSafe ? Base64.encodeBase64URLSafeString(data) : Base64.encodeBase64String(data);
    }

    /**
     * 将Base64字符串转换为字节数组.
     *
     * @param base64String Base64字符串
     * @return 对应的字节数组
     */
    public static byte[] decodeBase64String(final String base64String) {
        return Base64.decodeBase64(base64String);
    }
}
