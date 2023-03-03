package io.github.dbstarll.utils.lang.line;

import io.github.dbstarll.utils.lang.bytes.Bytes;
import io.github.dbstarll.utils.lang.io.EncryptInputStream;
import io.github.dbstarll.utils.lang.wrapper.IterableWrapper;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.zip.GZIPInputStream;

public class Lines extends LineIterator implements Iterable<String> {
    private final LineValidator lineValidator;

    /**
     * 构造Lines.
     *
     * @param reader        reader
     * @param lineValidator 行校验器
     */
    public Lines(final Reader reader, final LineValidator lineValidator) {
        super(reader);
        this.lineValidator = lineValidator;
    }

    /**
     * 从输入流构造Lines.
     *
     * @param in            输入流
     * @param cs            字符集
     * @param lineValidator 行校验器
     * @return 指定的Lines
     */
    public static Lines open(final InputStream in, final Charset cs, final LineValidator lineValidator) {
        return new Lines(new InputStreamReader(in, cs), lineValidator);
    }

    /**
     * 从文件构造Lines.
     *
     * @param file          文件
     * @param cs            字符集
     * @param lineValidator 行校验器
     * @return 指定的Lines
     * @throws IOException IO异常
     */
    public static Lines open(final File file, final Charset cs, final LineValidator lineValidator) throws IOException {
        return open(new FileInputStream(file), cs, lineValidator);
    }

    /**
     * 从指定类的ClassLoader中的资源文件构造Lines.
     *
     * @param cls           资源加载类
     * @param resource      资源名
     * @param cs            字符集
     * @param lineValidator 行校验器
     * @return 指定的Lines
     */
    public static Lines open(final Class<?> cls,
                             final String resource,
                             final Charset cs,
                             final LineValidator lineValidator) {
        return open(cls.getResourceAsStream(resource), cs, lineValidator);
    }

    /**
     * 从当前ClassLoader中的资源文件构造Lines.
     *
     * @param resource      资源名
     * @param cs            字符集
     * @param lineValidator 行校验器
     * @return 指定的Lines
     */
    public static Lines open(final String resource,
                             final Charset cs,
                             final LineValidator lineValidator) {
        return open(ClassLoader.getSystemResourceAsStream(resource), cs, lineValidator);
    }

    /**
     * 从加密的输入流构造Lines.
     *
     * @param in            输入流
     * @param key           加密密钥
     * @param cs            字符集
     * @param lineValidator 行校验器
     * @return 指定的Lines
     */
    public static Lines open(final InputStream in,
                             final Bytes key,
                             final Charset cs,
                             final LineValidator lineValidator) {
        return open(in instanceof EncryptInputStream ? in : new EncryptInputStream(in, key), cs, lineValidator);
    }

    /**
     * 从gzip压缩流构造Lines.
     *
     * @param in            输入流
     * @param cs            字符集
     * @param lineValidator 行校验器
     * @return 指定的Lines
     * @throws IOException 解压缩异常
     */
    public static Lines openGZip(final InputStream in,
                                 final Charset cs,
                                 final LineValidator lineValidator) throws IOException {
        return open(in instanceof GZIPInputStream ? in : new GZIPInputStream(in), cs, lineValidator);
    }

    /**
     * 从gzip压缩文件构造Lines.
     *
     * @param file          压缩文件
     * @param cs            字符集
     * @param lineValidator 行校验器
     * @return 指定的Lines
     * @throws IOException 解压缩异常
     */
    public static Lines openGZip(final File file,
                                 final Charset cs,
                                 final LineValidator lineValidator) throws IOException {
        return openGZip(new FileInputStream(file), cs, lineValidator);
    }


    /**
     * 从指定类的ClassLoader中的gzip资源文件构造Lines.
     *
     * @param cls           资源加载类
     * @param resource      资源名
     * @param cs            字符集
     * @param lineValidator 行校验器
     * @return 指定的Lines
     * @throws IOException 解压缩异常
     */
    public static Lines openGZip(final Class<?> cls,
                                 final String resource,
                                 final Charset cs,
                                 final LineValidator lineValidator)
            throws IOException {
        return openGZip(cls.getResourceAsStream(resource), cs, lineValidator);
    }

    /**
     * 从当前ClassLoader中的gzip资源文件构造Lines.
     *
     * @param resource      资源名
     * @param cs            字符集
     * @param lineValidator 行校验器
     * @return 指定的Lines
     * @throws IOException 解压缩异常
     */
    public static Lines openGZip(final String resource,
                                 final Charset cs,
                                 final LineValidator lineValidator) throws IOException {
        return openGZip(ClassLoader.getSystemResourceAsStream(resource), cs, lineValidator);
    }

    /**
     * 从加密的gzip输入流构造Lines.
     *
     * @param in            输入流
     * @param key           加密密钥
     * @param cs            字符集
     * @param lineValidator 行校验器
     * @return 指定的Lines
     * @throws IOException 解压缩异常
     */
    public static Lines openGZip(final InputStream in,
                                 final Bytes key,
                                 final Charset cs,
                                 final LineValidator lineValidator) throws IOException {
        return openGZip(in instanceof EncryptInputStream ? in : new EncryptInputStream(in, key), cs, lineValidator);
    }

    @Override
    protected final boolean isValidLine(final String line) {
        return lineValidator == null || lineValidator.isValidLine(line);
    }

    @Override
    public final Iterator<String> iterator() {
        return IterableWrapper.wrap(this).iterator();
    }

    @Override
    public final boolean hasNext() {
        if (super.hasNext()) {
            return true;
        } else {
            IOUtils.closeQuietly(this);
            return false;
        }
    }
}
