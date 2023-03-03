package test.io.github.dbstarll.utils.lang.enums;

import io.github.dbstarll.utils.lang.enums.EnumUtils;
import io.github.dbstarll.utils.lang.test.enums.Custom;
import junit.framework.TestCase;

public class TestEnumUtils extends TestCase {
    public void testName() {
        assertEquals("abd", EnumUtils.name(Custom.ABD));
    }

    public void testValueOf() {
        assertSame(Custom.ABD, EnumUtils.valueOf(Custom.class, "abd"));
    }
}
