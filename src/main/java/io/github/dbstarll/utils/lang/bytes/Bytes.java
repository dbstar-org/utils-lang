package io.github.dbstarll.utils.lang.bytes;

import io.github.dbstarll.utils.lang.StandardCharsets;

import java.util.Arrays;

/**
 * 提供对byte数组的封装.
 *
 * @author dbstar
 */
public class Bytes implements Comparable<Bytes> {
    public static final Bytes ZERO = new Bytes(new byte[0]);
    public static final Bytes NULL = new Bytes((byte[]) null);

    private final byte[] data;
    private final int length;
    private final int hash;

    /**
     * 根据提供的byte数组创建对象.
     *
     * @param data the array to be copied
     */
    public Bytes(byte[] data) {
        this.data = data == null ? null : Arrays.copyOf(data, data.length);
        this.length = this.data == null ? -1 : this.data.length;
        this.hash = Arrays.hashCode(this.data);
    }

    /**
     * 根据提供的byte数组和范围来创建对象.
     *
     * @param data the array from which a range is to be copied
     * @param from the initial index of the range to be copied, inclusive
     * @param to   the final index of the range to be copied, exclusive. (This index may lie outside the
     *             array.)
     * @throws IllegalArgumentException       if <tt>from &gt; to</tt>
     * @throws ArrayIndexOutOfBoundsException if {@code from < 0} or {@code from > data.length}
     */
    public Bytes(byte[] data, int from, int to) throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
        this.data = data == null ? null : Arrays.copyOfRange(data, from, to);
        this.length = this.data == null ? -1 : this.data.length;
        this.hash = Arrays.hashCode(this.data);
    }

    /**
     * 根据提供的字符串的getBytes来创建对象，字符编码为UTF-8.
     *
     * @param data the string bytes to be set
     */
    public Bytes(String data) {
        this.data = data == null ? null : data.getBytes(StandardCharsets.UTF_8);
        this.length = this.data == null ? -1 : this.data.length;
        this.hash = Arrays.hashCode(this.data);
    }

    /**
     * 获得封装的byte数组的副本.
     *
     * @return 封装的byte数组
     */
    public final byte[] get() {
        return data == null ? null : Arrays.copyOf(data, data.length);
    }

    /**
     * 获得封装的byte数据的指定位置的值.
     *
     * @param index 指定位置索引
     * @return 封装的byte数据的指定位置的值
     * @throws NullPointerException           如果封装的byte数组为null
     * @throws ArrayIndexOutOfBoundsException if {@code from < 0} or {@code from > data.length}
     */
    public final byte get(int index) throws NullPointerException, ArrayIndexOutOfBoundsException {
        return data[index];
    }

    /**
     * 获得封装的byte数组的长度.
     *
     * @return 封装的byte数组的长度。如果封装的byte数组为null，则返回-1。
     */
    public final int length() {
        return length;
    }

    @Override
    public final int hashCode() {
        return hash;
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (getClass() != obj.getClass()) {
            return false;
        }
        Bytes other = (Bytes) obj;
        return Arrays.equals(data, other.data);
    }

    @Override
    public int compareTo(Bytes other) {
        if (data == null) {
            return other.data == null ? 0 : -1;
        } else if (other.data == null) {
            return 1;
        } else {
            return BytesUtils.compare(data, 0, data.length, other.data, 0, other.data.length);
        }
    }
}
