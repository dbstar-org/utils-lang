package io.github.dbstarll.utils.lang;

import java.util.UUID;

/**
 * 处理UUID相关的工具类.
 *
 * @author dbstar
 */
public final class UuidUtils {
    /**
     * 随机生成UUID.
     *
     * @return 随机生成的UUID
     */
    public static UUID randomUuid() {
        return UUID.randomUUID();
    }

    /**
     * 根据时间戳来创建UUID.
     *
     * @param timestamp 时间戳
     * @return 包含时间戳信息的UUID
     */
    public static UUID randomUuidWithTimestamp(long timestamp) {
        UUID uuid = randomUuid();
        return new UUID((uuid.getMostSignificantBits() & 0xfffffL) | ((timestamp & 0xfffffffffffL) << 20),
                uuid.getLeastSignificantBits());
    }

    /**
     * 从UUID中提取时间戳.
     *
     * @param uuid A string that specifies a {@link UUID}
     * @return 时间戳
     * @throws IllegalArgumentException If uuid does not conform to the string representation as
     *                                  described in {@link UUID#toString}
     */
    public static long timestampOfUuid(String uuid) throws IllegalArgumentException {
        return timestampOfUuid(UUID.fromString(uuid));
    }

    /**
     * 从UUID中提取时间戳.
     *
     * @param uuid UUID
     * @return 时间戳
     */
    public static long timestampOfUuid(UUID uuid) {
        return uuid.getMostSignificantBits() >> 20;
    }
}
