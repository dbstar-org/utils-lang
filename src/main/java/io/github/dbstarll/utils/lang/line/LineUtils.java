package io.github.dbstarll.utils.lang.line;

import io.github.dbstarll.utils.lang.EncryptUtils;
import io.github.dbstarll.utils.lang.bytes.Bytes;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class LineUtils {
    private LineUtils() {
        //隐藏构造器
    }

    /**
     * 操作每一行数据.
     *
     * @param <E>          class of lineOperator's result
     * @param lines        所有的行
     * @param lineOperator 行操作
     * @return 行操作结果的计数器
     */
    public static <E> Map<E, Integer> operate(final Iterable<String> lines, final LineOperator<E> lineOperator) {
        final Map<E, Integer> counters = new HashMap<>();
        for (String line : lines) {
            final E result = lineOperator.operate(line);
            final Integer counter = counters.get(result);
            counters.put(result, 1 + (counter == null ? 0 : counter));
        }
        return counters;
    }

    /**
     * 操作每一行数据.
     *
     * @param <E>          class of lineOperator's result
     * @param logger       日志记录
     * @param name         加载的资源名称
     * @param in           加载的资源流
     * @param encryptedKey 秘钥
     * @param strength     SHA摘要力度
     * @param lineOperator 行操作
     * @return 行操作结果的计数器
     */
    public static <E> Map<E, Integer> operate(final Logger logger,
                                              final String name,
                                              final InputStream in,
                                              final String encryptedKey,
                                              final int strength,
                                              final LineOperator<E> lineOperator) {
        final Bytes key;
        try {
            key = new Bytes(EncryptUtils.sha(encryptedKey, strength));
        } catch (Exception ex) {
            if (logger != null) {
                logger.error("无法加载" + name + "列表。", ex);
            }
            IOUtils.closeQuietly(in);
            return Collections.emptyMap();
        }

        final Iterable<String> lines;
        try {
            lines = Lines.openGZip(in, key, StandardCharsets.UTF_8, LineValidator.NOT_COMMENT);
        } catch (Exception ex) {
            if (logger != null) {
                logger.error("无法加载" + name + "列表。", ex);
            }
            IOUtils.closeQuietly(in);
            return Collections.emptyMap();
        }

        return operate(lines, lineOperator);
    }

    /**
     * 操作每一行数据.
     *
     * @param <E>          class of lineOperator's result
     * @param logger       日志记录
     * @param name         加载的资源名称
     * @param in           加载的资源流
     * @param encryptedKey 秘钥
     * @param lineOperator 行操作
     * @return 行操作结果的计数器
     */
    public static <E> Map<E, Integer> operate(final Logger logger,
                                              final String name,
                                              final InputStream in,
                                              final String encryptedKey,
                                              final LineOperator<E> lineOperator) {
        return operate(logger, name, in, encryptedKey, 1, lineOperator);
    }

    /**
     * 操作每一行数据.
     *
     * @param lines        所有的行
     * @param lineOperator 行操作
     * @return 返回处理的总行数
     */
    public static int operate(final Iterable<String> lines, final VoidLineOperator lineOperator) {
        int counter = 0;
        for (String line : lines) {
            lineOperator.operate(line);
            counter++;
        }
        return counter;
    }

    /**
     * 操作每一行数据.
     *
     * @param logger       日志记录
     * @param name         加载的资源名称
     * @param in           加载的资源流
     * @param encryptedKey 秘钥
     * @param strength     SHA摘要力度
     * @param lineOperator 行操作
     * @return 返回处理的总行数
     */
    public static int operate(final Logger logger,
                              final String name,
                              final InputStream in,
                              final String encryptedKey,
                              final int strength,
                              final VoidLineOperator lineOperator) {
        final Bytes key;
        try {
            key = new Bytes(EncryptUtils.sha(encryptedKey, strength));
        } catch (Exception ex) {
            if (logger != null) {
                logger.error("无法加载" + name + "列表。", ex);
            }
            IOUtils.closeQuietly(in);
            return -1;
        }

        final Iterable<String> lines;
        try {
            lines = Lines.openGZip(in, key, StandardCharsets.UTF_8, LineValidator.NOT_COMMENT);
        } catch (Exception ex) {
            if (logger != null) {
                logger.error("无法加载" + name + "列表。", ex);
            }
            IOUtils.closeQuietly(in);
            return -1;
        }

        return operate(lines, lineOperator);
    }

    /**
     * 操作每一行数据.
     *
     * @param logger       日志记录
     * @param name         加载的资源名称
     * @param in           加载的资源流
     * @param encryptedKey 秘钥
     * @param lineOperator 行操作
     * @return 返回处理的总行数
     */
    public static int operate(final Logger logger,
                              final String name,
                              final InputStream in,
                              final String encryptedKey,
                              final VoidLineOperator lineOperator) {
        return operate(logger, name, in, encryptedKey, 1, lineOperator);
    }
}
