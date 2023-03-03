package test.io.github.dbstarll.utils.lang.security;

import io.github.dbstarll.utils.lang.security.AbstractSecurityBuilder;
import io.github.dbstarll.utils.lang.security.AlgorithmInstancer;
import io.github.dbstarll.utils.lang.security.InstanceException;
import junit.framework.TestCase;

import java.security.NoSuchAlgorithmException;

public class TestAbstractSecurityBuilder extends TestCase {
    private static class NoSuchMethodClass {
    }

    private static class IllegalAccessClass {
        public static IllegalAccessClass getInstance(final String algorithm) {
            return new IllegalAccessClass();
        }
    }

    public static class NoStaticClass {
        public NoStaticClass getInstance(final String algorithm) {
            return new NoStaticClass();
        }
    }

    public static class MyClass {
        private final String algorithm;
        private final String provider;

        private MyClass(final String algorithm, final String provider) {
            this.algorithm = algorithm;
            this.provider = provider;
        }

        public static MyClass getInstance(final String algorithm) throws NoSuchAlgorithmException {
            if (MyEnum.NO_SUCH_ALGORITHM.toString().equals(algorithm)) {
                throw new NoSuchAlgorithmException(algorithm);
            } else {
                return new MyClass(algorithm, null);
            }
        }

        public static MyClass getInstance(final String algorithm, final String provider) {
            return new MyClass(algorithm, provider);
        }
    }

    public enum MyEnum {
        ABC, NO_SUCH_ALGORITHM
    }

    private static class MyBuilder<T, A extends Enum<A>> extends AbstractSecurityBuilder<T, A> {
        public MyBuilder(final Class<T> typeClass, final A algorithm) throws NoSuchAlgorithmException, InstanceException {
            super(typeClass, algorithm);
        }

        public MyBuilder(final Class<T> typeClass, final A algorithm, final String provider) throws NoSuchAlgorithmException, InstanceException {
            super(typeClass, new AlgorithmInstancer<>(algorithm, provider));
        }
    }

    public void testNoSuchMethod() {
        try {
            new MyBuilder<>(NoSuchMethodClass.class, MyEnum.ABC);
            fail("catch InstanceException");
        } catch (Exception ex) {
            assertEquals(InstanceException.class, ex.getClass());
            assertNotNull(ex.getCause());
            assertEquals(NoSuchMethodException.class, ex.getCause().getClass());
        }
    }

    public void testNoSuchAlgorithm() {
        try {
            new MyBuilder<>(MyClass.class, MyEnum.NO_SUCH_ALGORITHM);
            fail("catch NoSuchAlgorithmException");
        } catch (Exception ex) {
            assertEquals(NoSuchAlgorithmException.class, ex.getClass());
            assertEquals(MyEnum.NO_SUCH_ALGORITHM.toString(), ex.getMessage());
        }
    }

    public void testIllegalAccess() {
        try {
            new MyBuilder<>(IllegalAccessClass.class, MyEnum.ABC);
            fail("catch InstanceException");
        } catch (Exception ex) {
            assertEquals(InstanceException.class, ex.getClass());
            assertNotNull(ex.getCause());
            assertEquals(IllegalAccessException.class, ex.getCause().getClass());
        }
    }

    public void testNoStatic() {
        try {
            new MyBuilder<>(NoStaticClass.class, MyEnum.ABC);
            fail("catch InstanceException");
        } catch (Exception ex) {
            assertEquals(InstanceException.class, ex.getClass());
            assertNotNull(ex.getCause());
            assertEquals(IllegalAccessException.class, ex.getCause().getClass());
        }
    }

    public void testSuccess() throws InstanceException, NoSuchAlgorithmException {
        final MyClass myClass = new MyBuilder<>(MyClass.class, MyEnum.ABC).build();
        assertNotNull(myClass);
        assertEquals(MyEnum.ABC.toString(), myClass.algorithm);
        assertNull(myClass.provider);
    }

    public void testProvider() throws InstanceException, NoSuchAlgorithmException {
        final MyClass myClass = new MyBuilder<>(MyClass.class, MyEnum.ABC, "test").build();
        assertNotNull(myClass);
        assertEquals(MyEnum.ABC.toString(), myClass.algorithm);
        assertEquals("test", myClass.provider);
    }
}
