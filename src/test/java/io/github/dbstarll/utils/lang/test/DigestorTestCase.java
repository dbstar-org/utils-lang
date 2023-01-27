package io.github.dbstarll.utils.lang.test;

import io.github.dbstarll.utils.lang.digest.Digestor;
import junit.framework.TestCase;
import org.apache.commons.lang3.RandomUtils;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public abstract class DigestorTestCase extends TestCase {
  private Digestor digestor;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    this.digestor = getDigestor();
  }

  @Override
  protected void tearDown() throws Exception {
    this.digestor = null;
    super.tearDown();
  }

  protected abstract Digestor getDigestor() throws NoSuchAlgorithmException;

  /**
   * 对相同的输入，摘要结果要相同.
   */
  public void testSame() {
    byte[] data = RandomUtils.nextBytes(1024);
    byte[] d1 = digestor.digest(data);
    byte[] d2 = digestor.digest(data);
    assertNotSame(d1, d2);
    assertTrue(Arrays.equals(d1, d2));

    assertNotNull(digestor.toString());
  }
}
