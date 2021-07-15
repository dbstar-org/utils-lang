package io.github.dbstarll.utils.lang;

import io.github.dbstarll.utils.lang.bytes.Bytes;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * 处理加解密相关的工具类.
 *
 * @author dbstar
 */
public final class EncryptUtils {
    public static final String ALGORITHM_MD5 = "MD5";
    public static final String ALGORITHM_SHA = "SHA";

    /**
     * 使用秘钥进行字节数组加密，保留原始字节数组不变，加密后结果填充到新创建的字节数组.
     *
     * @param data         待加密的字节数组
     * @param encryptedKey 秘钥
     * @return 加密后的字节数组
     */
    public static byte[] encryptCopy(byte[] data, Bytes encryptedKey) {
        return data == null ? null : encryptReplace(Arrays.copyOf(data, data.length), encryptedKey);
    }

    /**
     * 使用秘钥进行字节数组加密，加密后数据直接替换原始字节数组.
     *
     * @param data         待加密的字节数组
     * @param encryptedKey 秘钥
     * @return 加密后的字节数组
     */
    public static byte[] encryptReplace(byte[] data, Bytes encryptedKey) {
        if (data != null) {
            encrypt(data, 0, data.length, encryptedKey, 0);
        }
        return data;
    }

    /**
     * 使用秘钥和位置进行字节数组加密.
     *
     * @param data         待加密的字节数组
     * @param offset       待加密的字节数组的开始偏移位置
     * @param length       待加密的字节数组从开始偏移位置开始的字节长度
     * @param encryptedKey 秘钥
     * @param position     位置
     * @return 加密处理过的字节数
     */
    public static int encrypt(byte[] data, int offset, int length, Bytes encryptedKey, long position) {
        final byte[] bs = encryptedKey.get();
        final int keyLen = bs.length;
        final int end = offset + length;

        int pos = (int) (position % keyLen);
        for (int i = offset; i < end; i++) {
            data[i] = (byte) (data[i] ^ bs[pos++ % keyLen]);
        }

        // other impl
        // final int pos = (keyLen - (int) (position % keyLen)) % keyLen;
        // for (int i = 0; i < keyLen; i++) {
        // byte encryptByte = bs[i];
        // for (int j = offset + (i + pos) % keyLen; j < end; j += keyLen) {
        // data[j] = (byte) (data[j] ^ encryptByte);
        // }
        // }
        return length;
    }

    /**
     * 使用秘钥和位置进行字节加密.
     *
     * @param data         原始字节
     * @param encryptedKey 秘钥
     * @param position     位置
     * @return 加密后字节
     */
    public static byte getEncryptedByte(byte data, Bytes encryptedKey, long position) {
        return getEncryptedByte(data, getEncryptByte(encryptedKey, position));
    }

    /**
     * 使用秘钥字节进行字节加密.
     *
     * @param data        原始字节
     * @param encryptByte 秘钥字节
     * @return 加密后字节
     */
    private static byte getEncryptedByte(byte data, byte encryptByte) {
        return (byte) (data ^ encryptByte);
    }

    /**
     * 获得指定位置的秘钥字节.
     *
     * @param encryptedKey 秘钥
     * @param position     位置
     * @return 指定位置的秘钥字节
     */
    private static byte getEncryptByte(Bytes encryptedKey, long position) {
        return encryptedKey.get((int) (position % encryptedKey.length()));
    }

    /**
     * 对字节数组进行摘要.
     *
     * @param data      the array of bytes.
     * @param algorithm the name of the algorithm requested.
     * @return the array of bytes for the resulting hash value.
     * @throws NoSuchAlgorithmException if no Provider supports a MessageDigestSpi implementation for
     *                                  the specified algorithm.
     */
    public static byte[] digest(byte[] data, String algorithm) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(algorithm);
        md.update(data);
        return md.digest();
    }

    /**
     * 使用MD5对字节数组进行摘要.
     *
     * @param data the array of bytes.
     * @return the array of bytes for the resulting hash value.
     * @throws NoSuchAlgorithmException if no Provider supports a MessageDigestSpi implementation for
     *                                  the specified algorithm.
     */
    public static byte[] md5(byte[] data) throws NoSuchAlgorithmException {
        return digest(data, ALGORITHM_MD5);
    }

    /**
     * 使用SHA对字节数组进行摘要.
     *
     * @param data     the array of bytes.
     * @param strength SHA摘要力度
     * @return the array of bytes for the resulting hash value.
     * @throws NoSuchAlgorithmException if no Provider supports a MessageDigestSpi implementation for
     *                                  the specified algorithm.
     */
    public static byte[] sha(byte[] data, int strength) throws NoSuchAlgorithmException {
        return digest(data, ALGORITHM_SHA + "-" + strength);
    }

    /**
     * 使用SHA对字符串进行摘要.
     *
     * @param data     string
     * @param strength SHA摘要力度
     * @return the array of bytes for the resulting hash value.
     * @throws NoSuchAlgorithmException if no Provider supports a MessageDigestSpi implementation for
     *                                  the specified algorithm.
     */
    public static byte[] sha(String data, int strength) throws NoSuchAlgorithmException {
        return sha(data.getBytes(StandardCharsets.UTF_8), strength);
    }
}
