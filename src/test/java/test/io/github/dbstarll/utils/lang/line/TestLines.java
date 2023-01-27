package test.io.github.dbstarll.utils.lang.line;

import io.github.dbstarll.utils.lang.EncryptUtils;
import io.github.dbstarll.utils.lang.StandardCharsets;
import io.github.dbstarll.utils.lang.bytes.Bytes;
import io.github.dbstarll.utils.lang.io.EncryptInputStream;
import io.github.dbstarll.utils.lang.line.LineValidator;
import io.github.dbstarll.utils.lang.line.Lines;
import junit.framework.TestCase;

import java.io.File;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

public class TestLines extends TestCase {
    private Bytes encryptedKey;

    @Override
    protected void setUp() throws Exception {
        this.encryptedKey = new Bytes(EncryptUtils.sha("encryptedKey", 1));
        super.setUp();
    }

    private static int read(Iterable<String> lines) {
        int count = 0;
        for (String line : lines) {
            count++;
        }
        return count;
    }

    /**
     * 测试{@link Lines#open(File, java.nio.charset.Charset, LineValidator)}.
     *
     * @throws IOException IOException
     */
    public void testOpenFile() throws IOException {
        assertEquals(6, read(Lines.open(new File("src/test/resources/lines.txt"),
                StandardCharsets.UTF_8, LineValidator.ALL)));
    }

    /**
     * 测试{@link Lines#openGZip(File, java.nio.charset.Charset, LineValidator)}.
     *
     * @throws IOException IOException
     */
    public void testOpenGzipFile() throws IOException {
        assertEquals(6, read(Lines.openGZip(new File("src/test/resources/lines.txt.gz"),
                StandardCharsets.UTF_8, LineValidator.ALL)));
    }

    /**
     * 测试{@link Lines#open(String, java.nio.charset.Charset, LineValidator)}.
     *
     * @throws IOException IOException
     */
    public void testOpenSystemResource() throws IOException {
        assertEquals(6, read(Lines.open("lines.txt", StandardCharsets.UTF_8, null)));
    }

    /**
     * 测试{@link Lines#openGZip(String, java.nio.charset.Charset, LineValidator)}.
     *
     * @throws IOException IOException
     */
    public void testOpenGzipSystemResource() throws IOException {
        assertEquals(6, read(Lines.openGZip("lines.txt.gz", StandardCharsets.UTF_8, null)));
    }

    /**
     * 测试{@link Lines#open(Class, String, java.nio.charset.Charset, LineValidator)}.
     *
     * @throws IOException IOException
     */
    public void testOpenResource() throws IOException {
        assertEquals(4, read(
                Lines.open(TestLines.class, "/lines.txt", StandardCharsets.UTF_8, LineValidator.NOT_BLANK)));
    }

    /**
     * 测试{@link Lines#openGZip(Class, String, java.nio.charset.Charset, LineValidator)}.
     *
     * @throws IOException IOException
     */
    public void testOpenGzipResource() throws IOException {
        assertEquals(4, read(Lines.openGZip(TestLines.class, "/lines.txt.gz", StandardCharsets.UTF_8,
                LineValidator.NOT_BLANK)));
    }

    /**
     * 测试{@link Lines#open(java.io.InputStream, Bytes, java.nio.charset.Charset, LineValidator)}.
     *
     * @throws IOException IOException
     */
    public void testOpenEncrypted() throws IOException {
        assertEquals(6, read(Lines.open(ClassLoader.getSystemResourceAsStream("lines-encrypt.txt"),
                encryptedKey, StandardCharsets.UTF_8, LineValidator.ALL)));
    }

    /**
     * 测试{@link Lines#openGZip(java.io.InputStream, Bytes, java.nio.charset.Charset, LineValidator)}.
     *
     * @throws IOException IOException
     */
    public void testOpenGzipEncrypted() throws IOException {
        assertEquals(6,
                read(Lines.openGZip(ClassLoader.getSystemResourceAsStream("lines.txt-encrypt.gz"),
                        encryptedKey, StandardCharsets.UTF_8, LineValidator.ALL)));
    }

    /**
     * 测试{@link Lines#open(java.io.InputStream, Bytes, java.nio.charset.Charset, LineValidator)}.
     *
     * @throws IOException IOException
     */
    public void testOpenEncryptedAlready() throws IOException {
        assertEquals(6,
                read(Lines.open(new EncryptInputStream(
                        new GZIPInputStream(ClassLoader.getSystemResourceAsStream("lines-encrypt.txt.gz")),
                        encryptedKey), encryptedKey, StandardCharsets.UTF_8, LineValidator.ALL)));
    }

    /**
     * 测试{@link Lines#openGZip(java.io.InputStream, Bytes, java.nio.charset.Charset, LineValidator)}.
     *
     * @throws IOException IOException
     */
    public void testOpenGzipEncryptedAlready() throws IOException {
        assertEquals(6,
                read(
                        Lines.openGZip(
                                new EncryptInputStream(
                                        ClassLoader.getSystemResourceAsStream("lines.txt-encrypt.gz"), encryptedKey),
                                encryptedKey, StandardCharsets.UTF_8, LineValidator.ALL)));
    }

    /**
     * 测试{@link Lines#openGZip(java.io.InputStream, java.nio.charset.Charset, LineValidator)}.
     *
     * @throws IOException IOException
     */
    public void testOpenGzipAlready() throws IOException {
        assertEquals(6,
                read(Lines.openGZip(
                        new GZIPInputStream(ClassLoader.getSystemResourceAsStream("lines.txt.gz")),
                        StandardCharsets.UTF_8, null)));
    }
}
