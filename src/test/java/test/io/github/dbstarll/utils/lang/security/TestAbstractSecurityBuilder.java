package test.io.github.dbstarll.utils.lang.security;

import io.github.dbstarll.utils.lang.security.AbstractSecurityBuilder;
import io.github.dbstarll.utils.lang.security.InstanceException;
import junit.framework.TestCase;

import java.security.NoSuchAlgorithmException;

public class TestAbstractSecurityBuilder extends TestCase {
    private class NoSuchMethodClass {
    }

    public static class MyClass {
        private final String algorithm;

        private MyClass(String algorithm) {
            this.algorithm = algorithm;
        }

        public static MyClass getInstance(String algorithm) throws NoSuchAlgorithmException {
            if (MyEnum.NO_SUCH_ALGORITHM.toString().equals(algorithm)) {
                throw new NoSuchAlgorithmException(algorithm);
            } else {
                return new MyClass(algorithm);
            }
        }
    }

    private enum MyEnum {
        ABC, NO_SUCH_ALGORITHM
    }

    private class MyBuilder<T, A extends Enum<?>> extends AbstractSecurityBuilder<T, A> {
        public MyBuilder(final Class<T> typeClass, final A algorithm) throws NoSuchAlgorithmException, InstanceException {
            super(typeClass, algorithm);
        }
    }

    public void testNoSuchMethod() {
        try {
            new MyBuilder(NoSuchMethodClass.class, MyEnum.ABC);
            fail("catch InstanceException");
        } catch (Exception ex) {
            assertEquals(InstanceException.class, ex.getClass());
            assertNotNull(ex.getCause());
            assertEquals(NoSuchMethodException.class, ex.getCause().getClass());
        }
    }

    public void testNoSuchAlgorithm() {
        try {
            new MyBuilder(MyClass.class, MyEnum.NO_SUCH_ALGORITHM);
            fail("catch NoSuchAlgorithmException");
        } catch (Exception ex) {
            assertEquals(NoSuchAlgorithmException.class, ex.getClass());
            assertEquals(MyEnum.NO_SUCH_ALGORITHM.toString(), ex.getMessage());
        }
    }

    public void testSuccess() throws InstanceException, NoSuchAlgorithmException {
        final MyClass myClass = new MyBuilder<>(MyClass.class, MyEnum.ABC).build();
        assertNotNull(myClass);
        assertEquals(MyEnum.ABC.toString(), myClass.algorithm);
    }
}
