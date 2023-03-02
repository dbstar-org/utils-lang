package test.io.github.dbstarll.utils.lang.enums;

import io.github.dbstarll.utils.lang.enums.EnumValue;
import io.github.dbstarll.utils.lang.enums.EnumValueHelper;
import io.github.dbstarll.utils.lang.test.enums.Custom;
import io.github.dbstarll.utils.lang.test.enums.Default;
import io.github.dbstarll.utils.lang.test.enums.Normal;
import io.github.dbstarll.utils.lang.test.enums.ToString;
import junit.framework.TestCase;

import java.util.Arrays;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

public class TestEnumValueHelper extends TestCase {
    enum NoPublicClass {
        ABC, DEF
    }

    @EnumValue(method = "abc")
    public enum NoPublicMethod {
        ABC, DEF;

        String abc() {
            return name();
        }
    }

    @EnumValue(method = "abc")
    public enum Static {
        ABC, DEF;

        public static String abc() {
            return "abc";
        }
    }

    @EnumValue(method = "")
    public enum Empty {
        ABC, DEF
    }

    @EnumValue(method = "abc")
    public enum ReturnNotString {
        ABC, DEF;

        public boolean abc() {
            return true;
        }
    }

    @EnumValue(method = "abc")
    public enum Duplicate {
        ABC, DEF;

        public String abc() {
            return "abc";
        }
    }

    @EnumValue(method = "abc")
    public enum Failed {
        ABC, DEF;

        public String abc() {
            throw new IllegalArgumentException("abc");
        }
    }

    private <T extends Enum<T>> void testEnum(final Class<T> enumClass, final Function<T, String> value) {
        final EnumValueHelper<T> helper = new EnumValueHelper<>(enumClass);
        Arrays.stream(enumClass.getEnumConstants()).forEach(e -> {
            assertEquals("name() failed on " + enumClass.getName(), value.apply(e), helper.name(e));
            assertSame("valueOf() failed on " + enumClass.getName(), e, helper.valueOf(value.apply(e)));
        });
    }

    public void testHelper() {
        testEnum(Normal.class, Enum::name);
        testEnum(Default.class, Enum::name);
        testEnum(Custom.class, Custom::getTitle);
        testEnum(ToString.class, Enum::toString);
    }

    public void testNoPublicClass() {
        final Exception e = assertThrowsExactly(IllegalArgumentException.class, () -> new EnumValueHelper<>(NoPublicClass.class));
        assertEquals("enumType must be public: " + NoPublicClass.class.getName(), e.getMessage());
        assertNull(e.getCause());
    }

    public void testNoPublicMethod() {
        final Exception e = assertThrowsExactly(IllegalArgumentException.class, () -> new EnumValueHelper<>(NoPublicMethod.class));
        assertEquals("get method[abc] failed: " + NoPublicMethod.class.getName(), e.getMessage());
        assertNotNull(e.getCause());
        assertSame(NoSuchMethodException.class, e.getCause().getClass());
        assertEquals(NoPublicMethod.class.getName() + ".abc()", e.getCause().getMessage());
    }

    public void testStatic() {
        final Exception e = assertThrowsExactly(IllegalArgumentException.class, () -> new EnumValueHelper<>(Static.class));
        assertEquals("method[abc] must not be static: " + Static.class.getName(), e.getMessage());
        assertNull(e.getCause());
    }

    public void testEmpty() {
        final Exception e = assertThrowsExactly(IllegalArgumentException.class, () -> new EnumValueHelper<>(Empty.class));
        assertEquals("@EnumValue.method() not set: " + Empty.class.getName(), e.getMessage());
        assertNull(e.getCause());
    }

    public void testReturnNotString() {
        final Exception e = assertThrowsExactly(IllegalArgumentException.class, () -> new EnumValueHelper<>(ReturnNotString.class));
        assertEquals("method[abc]'s returnType must be String: " + ReturnNotString.class.getName(), e.getMessage());
        assertNull(e.getCause());
    }

    public void testDuplicate() {
        final Exception e = assertThrowsExactly(IllegalArgumentException.class, () -> new EnumValueHelper<>(Duplicate.class));
        assertEquals("duplicate name [abc] for [ABC] and [DEF]: " + Duplicate.class.getName(), e.getMessage());
        assertNull(e.getCause());
    }

    public void testFailed() {
        final Exception e = assertThrowsExactly(IllegalStateException.class, () -> new EnumValueHelper<>(Failed.class));
        assertEquals("get enum[ABC]'s name failed: " + Failed.class.getName(), e.getMessage());
        assertNotNull(e.getCause());
        assertSame(IllegalArgumentException.class, e.getCause().getClass());
        assertEquals("abc", e.getCause().getMessage());
    }

    public void testNameNull() {
        final EnumValueHelper<Normal> helper = new EnumValueHelper<>(Normal.class);
        final Exception e = assertThrowsExactly(IllegalStateException.class, () -> helper.name(null));
        assertEquals("get enum[null]'s name failed: " + Normal.class.getName(), e.getMessage());
        assertNotNull(e.getCause());
        assertSame(NullPointerException.class, e.getCause().getClass());
    }

    public void testValueOfNull() {
        final EnumValueHelper<Normal> helper = new EnumValueHelper<>(Normal.class);
        final Exception e = assertThrowsExactly(NullPointerException.class, () -> helper.valueOf(null));
        assertEquals("name is blank", e.getMessage());
    }

    public void testValueOfUnknown() {
        final EnumValueHelper<Normal> helper = new EnumValueHelper<>(Normal.class);
        final Exception e = assertThrowsExactly(IllegalArgumentException.class, () -> helper.valueOf("unknown"));
        assertEquals("No enum constant[unknown]: " + Normal.class.getName(), e.getMessage());
    }
}
