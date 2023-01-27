package test.io.github.dbstarll.utils.lang.line;

import io.github.dbstarll.utils.lang.line.*;
import io.github.dbstarll.utils.lang.test.LineType;
import io.github.dbstarll.utils.lang.test.LineTypeOperator;
import junit.framework.TestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class TestLineUtils extends TestCase {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestLineUtils.class);

    private static final VoidLineOperator VOID_OPERATOR = new VoidLineOperator() {
        @Override
        protected void operateWithoutReturn(String line) {
        }
    };
    private static final LineOperator<LineType> LINE_TYPE_OPERATOR = new LineTypeOperator();

    /**
     * 测试 {@link LineUtils#operate(Iterable, LineOperator)}.
     *
     * @throws IOException IOException
     */
    public void testOperate() throws IOException {
        Iterable<String> lines = Lines.open(new File("src/test/resources/lines.txt"),
                StandardCharsets.UTF_8, LineValidator.ALL);
        Map<LineType, Integer> rs = LineUtils.operate(lines, LINE_TYPE_OPERATOR);
        assertEquals(2, rs.get(LineType.blank).intValue());
        assertEquals(1, rs.get(LineType.comment).intValue());
        assertEquals(3, rs.get(LineType.line).intValue());
    }

    /**
     * 测试 {@link LineUtils#operate(Logger, String, InputStream, String, LineOperator)}.
     */
    public void testOperateLogger() {
        final InputStream in = ClassLoader.getSystemResourceAsStream("lines.txt-encrypt.gz");
        assertEquals(3, LineUtils.operate(LOGGER, "lines", in, "encryptedKey", LINE_TYPE_OPERATOR)
                .get(LineType.line).intValue());
    }

    /**
     * 测试 {@link LineUtils#operate(Logger, String, InputStream, String, int, LineOperator)}.
     */
    public void testOperateLoggerException() {
        final InputStream in = ClassLoader.getSystemResourceAsStream("lines-encrypt.txt.gz");
        final Map<LineType, Integer> counter = LineUtils.operate(LOGGER, "lines", in, "encryptedKey", 1, LINE_TYPE_OPERATOR);
        assertNotNull(counter);
        assertEquals(0, counter.size());
    }

    /**
     * 测试 {@link LineUtils#operate(Logger, String, InputStream, String, int, LineOperator)}.
     */
    public void testOperateLoggerExceptionNull() {
        final InputStream in = ClassLoader.getSystemResourceAsStream("lines-encrypt.txt.gz");
        final Map<LineType, Integer> counter = LineUtils.operate(null, "lines", in, "encryptedKey", 1, LINE_TYPE_OPERATOR);
        assertNotNull(counter);
        assertEquals(0, counter.size());
    }

    /**
     * 测试 {@link LineUtils#operate(Logger, String, InputStream, String, int, LineOperator)}.
     */
    public void testOperateLoggerExceptionStrength() {
        final InputStream in = ClassLoader.getSystemResourceAsStream("lines-encrypt.txt.gz");
        final Map<LineType, Integer> counter = LineUtils.operate(LOGGER, "lines", in, "encryptedKey", 1024, LINE_TYPE_OPERATOR);
        assertNotNull(counter);
        assertEquals(0, counter.size());
    }

    /**
     * 测试 {@link LineUtils#operate(Logger, String, InputStream, String, int, LineOperator)}.
     */
    public void testOperateLoggerExceptionStrengthNull() {
        final InputStream in = ClassLoader.getSystemResourceAsStream("lines-encrypt.txt.gz");
        final Map<LineType, Integer> counter = LineUtils.operate(null, "lines", in, "encryptedKey", 1024, LINE_TYPE_OPERATOR);
        assertNotNull(counter);
        assertEquals(0, counter.size());
    }

    /**
     * 测试 {@link LineUtils#operate(Iterable, VoidLineOperator)}.
     *
     * @throws IOException IOException
     */
    public void testOperateVoid() throws IOException {
        Iterable<String> lines = Lines.open(new File("src/test/resources/lines.txt"),
                StandardCharsets.UTF_8, LineValidator.ALL);
        int rs = LineUtils.operate(lines, VOID_OPERATOR);
        assertEquals(6, rs);
    }

    /**
     * 测试 {@link LineUtils#operate(Logger, String, InputStream, String, VoidLineOperator)}.
     */
    public void testOperateVoidLogger() {
        final InputStream in = ClassLoader.getSystemResourceAsStream("lines.txt-encrypt.gz");
        assertEquals(3, LineUtils.operate(LOGGER, "lines", in, "encryptedKey", VOID_OPERATOR));
    }

    /**
     * 测试 {@link LineUtils#operate(Logger, String, InputStream, String, int, VoidLineOperator)}.
     */
    public void testOperateVoidLoggerException() {
        final InputStream in = ClassLoader.getSystemResourceAsStream("lines-encrypt.txt.gz");
        assertEquals(-1, LineUtils.operate(LOGGER, "lines", in, "encryptedKey", 1, VOID_OPERATOR));
    }

    /**
     * 测试 {@link LineUtils#operate(Logger, String, InputStream, String, int, VoidLineOperator)}.
     */
    public void testOperateVoidLoggerExceptionNull() {
        final InputStream in = ClassLoader.getSystemResourceAsStream("lines-encrypt.txt.gz");
        assertEquals(-1, LineUtils.operate(null, "lines", in, "encryptedKey", 1, VOID_OPERATOR));
    }

    /**
     * 测试 {@link LineUtils#operate(Logger, String, InputStream, String, int, VoidLineOperator)}.
     */
    public void testOperateVoidLoggerExceptionStrength() {
        final InputStream in = ClassLoader.getSystemResourceAsStream("lines-encrypt.txt.gz");
        assertEquals(-1, LineUtils.operate(LOGGER, "lines", in, "encryptedKey", 1024, VOID_OPERATOR));
    }

    /**
     * 测试 {@link LineUtils#operate(Logger, String, InputStream, String, int, VoidLineOperator)}.
     */
    public void testOperateVoidLoggerExceptionStrengthNull() {
        final InputStream in = ClassLoader.getSystemResourceAsStream("lines-encrypt.txt.gz");
        assertEquals(-1, LineUtils.operate(null, "lines", in, "encryptedKey", 1024, VOID_OPERATOR));
    }
}
