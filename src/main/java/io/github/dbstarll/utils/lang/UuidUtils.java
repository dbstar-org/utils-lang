package io.github.dbstarll.utils.lang;

import java.util.UUID;

/**
 * 处理UUID相关的工具类.
 *
 * @author dbstar
 */
public final class UuidUtils {
    /**
     * masks for mostSigBits.
     */
    private static final long MOST_SIG_BITS_MASKS = 0xfffffL;

    /**
     * masks for timestamp.
     */
    private static final long TIMESTAMP_MASKS = 0xfffffffffffL;

    /**
     * shift for timestamp.
     */
    private static final int TIMESTAMP_SHIFT = 20;

    private UuidUtils() {
        //隐藏构造器
    }

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
    public static UUID randomUuidWithTimestamp(final long timestamp) {
        UUID uuid = randomUuid();
        final long timeBits = (timestamp & TIMESTAMP_MASKS) << TIMESTAMP_SHIFT;
        final long mostSigBits = uuid.getMostSignificantBits() & MOST_SIG_BITS_MASKS;
        return new UUID(mostSigBits | timeBits, uuid.getLeastSignificantBits());
    }

    /**
     * 从UUID中提取时间戳.
     *
     * @param uuid A string that specifies a {@link UUID}
     * @return 时间戳
     * @throws IllegalArgumentException If uuid does not conform to the string representation as
     *                                  described in {@link UUID#toString}
     */
    public static long timestampOfUuid(final String uuid) throws IllegalArgumentException {
        return timestampOfUuid(UUID.fromString(uuid));
    }

    /**
     * 从UUID中提取时间戳.
     *
     * @param uuid UUID
     * @return 时间戳
     */
    public static long timestampOfUuid(final UUID uuid) {
        return uuid.getMostSignificantBits() >> TIMESTAMP_SHIFT;
    }
}
