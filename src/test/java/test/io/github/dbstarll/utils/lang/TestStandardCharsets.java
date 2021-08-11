package test.io.github.dbstarll.utils.lang;

import io.github.dbstarll.utils.lang.StandardCharsets;
import junit.framework.TestCase;

import java.nio.charset.Charset;

public class TestStandardCharsets extends TestCase {
  public void testUsAscii() {
    assertEquals(StandardCharsets.US_ASCII, Charset.forName("US-ASCII"));
  }

  public void testIso8859_1() {
    assertEquals(StandardCharsets.ISO_8859_1, Charset.forName("ISO-8859-1"));
  }

  public void testUtf8() {
    assertEquals(StandardCharsets.UTF_8, Charset.forName("UTF-8"));
  }
}
