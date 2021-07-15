package io.github.dbstarll.utils.lang.line;

import io.github.dbstarll.utils.lang.EncryptUtils;
import io.github.dbstarll.utils.lang.StandardCharsets;
import io.github.dbstarll.utils.lang.bytes.Bytes;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public final class LineUtils {
    /**
     * 操作每一行数据.
     *
     * @param lines        所有的行
     * @param lineOperator 行操作
     * @return 行操作结果的计数器
     */
    public static <E> Map<E, Integer> operate(Iterable<String> lines, LineOperator<E> lineOperator) {
        final Map<E, Integer> counters = new HashMap<E, Integer>();
        for (String line : lines) {
            final E result = lineOperator.operate(line);
            final Integer counter = counters.get(result);
            counters.put(result, 1 + (counter == null ? 0 : counter.intValue()));
        }
        return counters;
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
     * @return 行操作结果的计数器
     */
    public static <E> Map<E, Integer> operate(Logger logger, String name, InputStream in, String encryptedKey,
                                              int strength, LineOperator<E> lineOperator) {
        final Bytes key;
        try {
            key = new Bytes(EncryptUtils.sha(encryptedKey, strength));
        } catch (Throwable ex) {
            if (logger != null) {
                logger.error("无法加载" + name + "列表。", ex);
            }
            IOUtils.closeQuietly(in);
            return null;
        }

        final Iterable<String> lines;
        try {
            lines = Lines.openGZip(in, key, StandardCharsets.UTF_8, LineValidator.NotComment);
        } catch (Throwable ex) {
            if (logger != null) {
                logger.error("无法加载" + name + "列表。", ex);
            }
            IOUtils.closeQuietly(in);
            return null;
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
     * @return 行操作结果的计数器
     */
    public static <E> Map<E, Integer> operate(Logger logger, String name, InputStream in, String encryptedKey,
                                              LineOperator<E> lineOperator) {
        return operate(logger, name, in, encryptedKey, 1, lineOperator);
    }

    /**
     * 操作每一行数据.
     *
     * @param lines        所有的行
     * @param lineOperator 行操作
     * @return 返回处理的总行数
     */
    public static int operate(Iterable<String> lines, VoidLineOperator lineOperator) {
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
    public static int operate(Logger logger, String name, InputStream in, String encryptedKey, int strength,
                              VoidLineOperator lineOperator) {
        final Bytes key;
        try {
            key = new Bytes(EncryptUtils.sha(encryptedKey, strength));
        } catch (Throwable ex) {
            if (logger != null) {
                logger.error("无法加载" + name + "列表。", ex);
            }
            IOUtils.closeQuietly(in);
            return -1;
        }

        final Iterable<String> lines;
        try {
            lines = Lines.openGZip(in, key, StandardCharsets.UTF_8, LineValidator.NotComment);
        } catch (Throwable ex) {
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
    public static int operate(Logger logger, String name, InputStream in, String encryptedKey,
                              VoidLineOperator lineOperator) {
        return operate(logger, name, in, encryptedKey, 1, lineOperator);
    }
}
