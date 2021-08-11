package test.io.github.dbstarll.utils.lang;

import io.github.dbstarll.utils.lang.UuidUtils;
import junit.framework.TestCase;

import java.util.UUID;

public class TestUuidUtils extends TestCase {
  /**
   * 测试{@link UuidUtils#randomUuid()}.
   */
  public void testRandomUuid() {
    UUID u1 = UuidUtils.randomUuid();
    UUID u2 = UuidUtils.randomUuid();
    assertFalse(u1.equals(u2));
    assertFalse(u1 == u2);
  }

  /**
   * 测试{@link UuidUtils#randomUuidWithTimestamp(long)}.
   */
  public void testRandomUuidWithTimestamp() {
    long timestamp = System.currentTimeMillis();
    UUID u1 = UuidUtils.randomUuidWithTimestamp(timestamp);
    UUID u2 = UuidUtils.randomUuidWithTimestamp(timestamp);
    assertFalse(u1.equals(u2));
    assertFalse(u1 == u2);
  }

  /**
   * 测试{@link UuidUtils#timestampOfUuid(String)}.
   */
  public void testTimestampOfUuidString() {
    long timestamp = System.currentTimeMillis();
    UUID u1 = UuidUtils.randomUuidWithTimestamp(timestamp);
    UUID u2 = UuidUtils.randomUuidWithTimestamp(timestamp);
    assertEquals(timestamp, UuidUtils.timestampOfUuid(u1.toString()));
    assertEquals(timestamp, UuidUtils.timestampOfUuid(u2.toString()));
  }

  /**
   * 测试{@link UuidUtils#timestampOfUuid(UUID)}.
   */
  public void testTimestampOfUuid() {
    long timestamp = System.currentTimeMillis();
    UUID u1 = UuidUtils.randomUuidWithTimestamp(timestamp);
    UUID u2 = UuidUtils.randomUuidWithTimestamp(timestamp);
    assertEquals(timestamp, UuidUtils.timestampOfUuid(u1));
    assertEquals(timestamp, UuidUtils.timestampOfUuid(u2));
  }
}
