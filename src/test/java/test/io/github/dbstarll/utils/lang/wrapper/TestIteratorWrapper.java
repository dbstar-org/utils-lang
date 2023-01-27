package test.io.github.dbstarll.utils.lang.wrapper;

import io.github.dbstarll.utils.lang.wrapper.IteratorWrapper;
import junit.framework.TestCase;

import java.util.*;

public class TestIteratorWrapper extends TestCase {
    /**
     * 常规功能测试.
     */
    public void testNormal() {
        Iterator<Integer> iterator = new IteratorWrapper<String, Integer>(
                Arrays.asList("1", "2").iterator()) {
            @Override
            protected Integer next(String value) {
                return Integer.valueOf(value);
            }
        };

        assertTrue(iterator.hasNext());
        assertEquals(Integer.valueOf(1), iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(Integer.valueOf(2), iterator.next());
        assertFalse(iterator.hasNext());

        try {
            assertNull(iterator.next());
            fail("throw NoSuchElementException");
        } catch (NoSuchElementException ex) {
            assertNotNull(ex);
        }
    }

    /**
     * 测试remove.
     */
    public void testRemove() {
        List<String> list = new LinkedList<>(Arrays.asList("1", "2"));
        Iterator<Integer> iterator = new IteratorWrapper<String, Integer>(list.iterator()) {
            @Override
            protected Integer next(String value) {
                return Integer.valueOf(value);
            }
        };

        assertEquals(2, list.size());
        assertEquals("1", list.get(0));
        assertTrue(iterator.hasNext());
        assertEquals(Integer.valueOf(1), iterator.next());
        iterator.remove();
        assertTrue(iterator.hasNext());
        assertEquals(Integer.valueOf(2), iterator.next());
        assertFalse(iterator.hasNext());
        assertEquals(1, list.size());
        assertEquals("2", list.get(0));

        try {
            assertNull(iterator.next());
            fail("throw NoSuchElementException");
        } catch (NoSuchElementException ex) {
            assertNotNull(ex);
        }
    }
}
