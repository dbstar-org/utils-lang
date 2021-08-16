package test.io.github.dbstarll.utils.lang.line;

import io.github.dbstarll.utils.lang.line.LineValidator;
import junit.framework.TestCase;

public class TestLineValidator extends TestCase {

    /**
     * 测试{@link LineValidator#ALL}.
     */
    public void testAll() {
        LineValidator validator = LineValidator.ALL;

        assertTrue(validator.isValidLine("line"));
        assertTrue(validator.isValidLine("   line"));
        assertTrue(validator.isValidLine("   "));
        assertTrue(validator.isValidLine("\tline"));
        assertTrue(validator.isValidLine("\t"));
        assertTrue(validator.isValidLine("# line"));
    }

    /**
     * 测试{@link LineValidator#NOT_BLANK}.
     */
    public void testNotBlank() {
        LineValidator validator = LineValidator.NOT_BLANK;

        assertTrue(validator.isValidLine("line"));
        assertTrue(validator.isValidLine("   line"));
        assertFalse(validator.isValidLine("   "));
        assertTrue(validator.isValidLine("\tline"));
        assertFalse(validator.isValidLine("\t"));
        assertTrue(validator.isValidLine("# line"));
    }

    /**
     * 测试{@link LineValidator#NOT_COMMENT}.
     */
    public void testNotComment() {
        LineValidator validator = LineValidator.NOT_COMMENT;

        assertTrue(validator.isValidLine("line"));
        assertTrue(validator.isValidLine("   line"));
        assertFalse(validator.isValidLine("   "));
        assertTrue(validator.isValidLine("\tline"));
        assertFalse(validator.isValidLine("\t"));
        assertFalse(validator.isValidLine("# line"));
    }
}
