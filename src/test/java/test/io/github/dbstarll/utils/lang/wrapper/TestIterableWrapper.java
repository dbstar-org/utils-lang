package test.io.github.dbstarll.utils.lang.wrapper;

import io.github.dbstarll.utils.lang.wrapper.IterableWrapper;
import junit.framework.TestCase;

import java.util.Arrays;
import java.util.Iterator;

public class TestIterableWrapper extends TestCase {
    /**
     * 常规功能测试.
     */
    public void testNormal() {
        Iterator<String> iterator = Arrays.asList("1", "2", "3").iterator();
        Iterable<String> iterable = IterableWrapper.wrap(iterator);
        assertTrue(iterator == iterable.iterator());
    }

    /**
     * NULL测试.
     */
    public void testNull() {
        Iterable<String> iterable = IterableWrapper.wrap(null);
        assertNull(iterable.iterator());
    }
}
