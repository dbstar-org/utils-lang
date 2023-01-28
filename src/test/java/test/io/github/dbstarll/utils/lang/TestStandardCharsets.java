package test.io.github.dbstarll.utils.lang;

import io.github.dbstarll.utils.lang.StandardCharsets;
import junit.framework.TestCase;

public class TestStandardCharsets extends TestCase {
    public void testUsAscii() {
        assertEquals(StandardCharsets.US_ASCII, java.nio.charset.StandardCharsets.US_ASCII);
    }

    public void testIso8859_1() {
        assertEquals(StandardCharsets.ISO_8859_1, java.nio.charset.StandardCharsets.ISO_8859_1);
    }

    public void testUtf8() {
        assertEquals(StandardCharsets.UTF_8, java.nio.charset.StandardCharsets.UTF_8);
    }
}
