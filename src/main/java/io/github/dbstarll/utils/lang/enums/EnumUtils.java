package io.github.dbstarll.utils.lang.enums;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static org.apache.commons.lang3.Validate.notNull;

public final class EnumUtils {
    private static final ConcurrentMap<Class<?>, EnumValueHelper<?>> HELPERS = new ConcurrentHashMap<>();

    private EnumUtils() {
        // 工具类禁止实例化
    }

    /**
     * Returns the name of this enum constant, exactly as declared in its enum declaration.
     *
     * @param enumValue enum constant
     * @param <T>       The enum type whose constant is to be returned
     * @return the name of this enum constant
     */
    public static <T extends Enum<T>> String name(final T enumValue) {
        return getEnumValueHelper(getClass(enumValue)).name(enumValue);
    }

    @SuppressWarnings("unchecked")
    private static <T extends Enum<T>> Class<T> getClass(final T enumValue) {
        return (Class<T>) notNull(enumValue, "enumValue is null").getClass();
    }

    /**
     * Returns the enum constant of the specified enum type with the specified name.
     *
     * @param enumType the {@code Class} object of the enum type from which to return a constant
     * @param name     the name of the constant to return
     * @param <T>      The enum type whose constant is to be returned
     * @return the enum constant of the specified enum type with the specified name
     * @throws IllegalArgumentException if the specified enum type has no constant with the specified name
     * @throws NullPointerException     if {@code enumType} is null
     * @since 1.0.8
     */
    public static <T extends Enum<T>> T valueOf(final Class<T> enumType, final String name) {
        return getEnumValueHelper(notNull(enumType, "enumType is null")).valueOf(name);
    }

    @SuppressWarnings("unchecked")
    private static <T extends Enum<T>> EnumValueHelper<T> getEnumValueHelper(final Class<T> enumType) {
        return (EnumValueHelper<T>) HELPERS.computeIfAbsent(enumType, t -> new EnumValueHelper<>(enumType));
    }
}
