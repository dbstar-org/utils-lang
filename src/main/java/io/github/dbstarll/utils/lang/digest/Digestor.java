package io.github.dbstarll.utils.lang.digest;

public interface Digestor {
    /**
     * 摘要数据.
     *
     * @param data 原始数据
     * @return 摘要后的数据
     */
    byte[] digest(byte[] data);
}
