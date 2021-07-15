package test.io.github.dbstarll.utils.lang.line;

import io.github.dbstarll.utils.lang.line.LineValidator;
import junit.framework.TestCase;

public class TestLineValidator extends TestCase {

    /**
     * 测试{@link LineValidator#All}.
     */
    public void testAll() {
        LineValidator validator = LineValidator.All;

        assertTrue(validator.isValidLine("line"));
        assertTrue(validator.isValidLine("   line"));
        assertTrue(validator.isValidLine("   "));
        assertTrue(validator.isValidLine("\tline"));
        assertTrue(validator.isValidLine("\t"));
        assertTrue(validator.isValidLine("# line"));
    }

    /**
     * 测试{@link LineValidator#NotBlank}.
     */
    public void testNotBlank() {
        LineValidator validator = LineValidator.NotBlank;

        assertTrue(validator.isValidLine("line"));
        assertTrue(validator.isValidLine("   line"));
        assertFalse(validator.isValidLine("   "));
        assertTrue(validator.isValidLine("\tline"));
        assertFalse(validator.isValidLine("\t"));
        assertTrue(validator.isValidLine("# line"));
    }

    /**
     * 测试{@link LineValidator#NotComment}.
     */
    public void testNotComment() {
        LineValidator validator = LineValidator.NotComment;

        assertTrue(validator.isValidLine("line"));
        assertTrue(validator.isValidLine("   line"));
        assertFalse(validator.isValidLine("   "));
        assertTrue(validator.isValidLine("\tline"));
        assertFalse(validator.isValidLine("\t"));
        assertFalse(validator.isValidLine("# line"));
    }
}
