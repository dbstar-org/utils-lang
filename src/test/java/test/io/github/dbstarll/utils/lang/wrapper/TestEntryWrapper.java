package test.io.github.dbstarll.utils.lang.wrapper;

import io.github.dbstarll.utils.lang.wrapper.EntryWrapper;
import junit.framework.TestCase;

import java.util.Map.Entry;

import static org.junit.Assert.assertNotEquals;

public class TestEntryWrapper extends TestCase {
    /**
     * 测试{@link EntryWrapper#wrap(Object, Object)}.
     */
    public void testWarp() {
        final String key = "key";
        final String value = "value";
        EntryWrapper<String, String> entry = EntryWrapper.wrap(key, value);

        assertSame(key, entry.getKey());
        assertSame(value, entry.getValue());
    }

    /**
     * 测试{@link EntryWrapper#wrap(Object, Object)}.
     */
    public void testWarpNull() {
        EntryWrapper<String, String> entry = EntryWrapper.wrap(null, null);

        assertNull(entry.getKey());
        assertNull(entry.getValue());
    }

    /**
     * 测试{@link EntryWrapper#setValue(Object)}.
     */
    public void testSetValue() {
        final String key = "key";
        final String value = "value";
        EntryWrapper<String, String> entry = EntryWrapper.wrap(key, value);

        try {
            entry.setValue("value2");
            fail("throw UnsupportedOperationException");
        } catch (UnsupportedOperationException ex) {
            assertNotNull(ex);
        }
    }

    /**
     * 测试{@link EntryWrapper#hashCode()}.
     */
    public void testHash() {
        assertNotEquals(EntryWrapper.wrap("key", "value").hashCode(), 0);
        assertEquals(EntryWrapper.wrap("key", "value").hashCode(),
                EntryWrapper.wrap("key", "value").hashCode());
        assertEquals(EntryWrapper.wrap("key", null).hashCode(),
                EntryWrapper.wrap("key", null).hashCode());
        assertEquals(EntryWrapper.wrap(null, "value").hashCode(),
                EntryWrapper.wrap(null, "value").hashCode());
        assertEquals(EntryWrapper.wrap(null, null).hashCode(),
                EntryWrapper.wrap(null, null).hashCode());
    }

    /**
     * 测试{@link EntryWrapper#equals(Object)}.
     */
    public void testEquals() {
        assertEquals(EntryWrapper.wrap("key", "value"), EntryWrapper.wrap("key", "value"));
        assertEquals(EntryWrapper.wrap("key", null), EntryWrapper.wrap("key", null));
        assertEquals(EntryWrapper.wrap(null, "value"), EntryWrapper.wrap(null, "value"));
        assertEquals(EntryWrapper.wrap(null, null), EntryWrapper.wrap(null, null));

        Entry<String, String> entry = EntryWrapper.wrap("key", "value");
        assertEquals(entry, entry);
        assertNotEquals(entry, null);
        assertNotEquals(entry, "entry");

        assertNotEquals(EntryWrapper.wrap("key", "value"), EntryWrapper.wrap(null, "value"));
        assertNotEquals(EntryWrapper.wrap(null, "value"), EntryWrapper.wrap("key", "value"));
        assertNotEquals(EntryWrapper.wrap("key", "value"), EntryWrapper.wrap("key1", "value"));
        assertNotEquals(EntryWrapper.wrap("key", "value"), EntryWrapper.wrap("key", null));
        assertNotEquals(EntryWrapper.wrap("key", null), EntryWrapper.wrap("key", "value"));
        assertNotEquals(EntryWrapper.wrap("key", "value"), EntryWrapper.wrap("key", "value1"));
    }

    /**
     * 测试{@link EntryWrapper#toString()}.
     */
    public void testToString() {
        assertEquals("key=value", EntryWrapper.wrap("key", "value").toString());
    }
}
