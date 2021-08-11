package io.github.dbstarll.utils.lang.line;

import io.github.dbstarll.utils.lang.bytes.Bytes;
import io.github.dbstarll.utils.lang.io.EncryptInputStream;
import org.apache.commons.io.LineIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.zip.GZIPInputStream;

public class Lines extends LineIterator implements Iterable<String> {
    private static final Logger LOGGER = LoggerFactory.getLogger(LineIterator.class);

    private final LineValidator lineValidator;

    public Lines(Reader reader, LineValidator lineValidator) {
        super(reader);
        this.lineValidator = lineValidator;
    }

    public static Lines open(InputStream in, Charset cs, LineValidator lineValidator) {
        return new Lines(new InputStreamReader(in, cs), lineValidator);
    }

    public static Lines open(File file, Charset cs, LineValidator lineValidator) throws IOException {
        return open(new FileInputStream(file), cs, lineValidator);
    }

    public static Lines open(Class<?> cls, String resource, Charset cs, LineValidator lineValidator) throws IOException {
        return open(cls.getResourceAsStream(resource), cs, lineValidator);
    }

    public static Lines open(String resource, Charset cs, LineValidator lineValidator) throws IOException {
        return open(ClassLoader.getSystemResourceAsStream(resource), cs, lineValidator);
    }

    public static Lines open(InputStream in, Bytes key, Charset cs, LineValidator lineValidator) throws IOException {
        return open(EncryptInputStream.class.isInstance(in) ? in : new EncryptInputStream(in, key), cs, lineValidator);
    }

    public static Lines openGZip(InputStream in, Charset cs, LineValidator lineValidator) throws IOException {
        return open(GZIPInputStream.class.isInstance(in) ? in : new GZIPInputStream(in), cs, lineValidator);
    }

    public static Lines openGZip(File file, Charset cs, LineValidator lineValidator) throws IOException {
        return openGZip(new FileInputStream(file), cs, lineValidator);
    }

    public static Lines openGZip(Class<?> cls, String resource, Charset cs, LineValidator lineValidator)
            throws IOException {
        return openGZip(cls.getResourceAsStream(resource), cs, lineValidator);
    }

    public static Lines openGZip(String resource, Charset cs, LineValidator lineValidator) throws IOException {
        return openGZip(ClassLoader.getSystemResourceAsStream(resource), cs, lineValidator);
    }

    public static Lines openGZip(InputStream in, Bytes key, Charset cs, LineValidator lineValidator) throws IOException {
        return openGZip(EncryptInputStream.class.isInstance(in) ? in : new EncryptInputStream(in, key), cs, lineValidator);
    }

    @Override
    protected final boolean isValidLine(String line) {
        return lineValidator == null || lineValidator.isValidLine(line);
    }

    @Override
    public final Iterator<String> iterator() {
        return this;
    }

    @Override
    public final boolean hasNext() {
        if (super.hasNext()) {
            return true;
        } else {
            try {
                close();
            } catch (IOException e) {
                LOGGER.warn("close failed", e);
            }
            return false;
        }
    }
}
