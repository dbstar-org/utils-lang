package test.io.github.dbstarll.utils.lang.launcher;

import io.github.dbstarll.utils.lang.launcher.AbstractLauncher;
import io.github.dbstarll.utils.lang.launcher.LaunchException;
import io.github.dbstarll.utils.lang.launcher.MainLauncher;
import junit.framework.TestCase;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class TestMainLauncher extends TestCase {
    private static AtomicInteger sign;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        sign = new AtomicInteger(0);
    }

    @Override
    protected void tearDown() throws Exception {
        sign = null;
        super.tearDown();
    }

    /**
     * 测试{@link AbstractLauncher#run(String[])}在无参数时抛出异常.
     */
    public void testNullParams() {
        Launcher launcher = new Launcher();

        try {
            launcher.run((String[]) null);
            fail("catch 'Need task token.'");
        } catch (IllegalArgumentException ex) {
            assertEquals("Need task token.", ex.getMessage());
        }

        try {
            launcher.run();
            fail("catch 'Need task token.'");
        } catch (IllegalArgumentException ex) {
            assertEquals("Need task token.", ex.getMessage());
        }
    }

    /**
     * 测试{@link AbstractLauncher#run(String[])}在无对应任务时抛出异常.
     */
    public void testNoMatchToken() {
        Launcher launcher = new Launcher();

        try {
            launcher.run("token");
            fail("catch 'Unknown task token: token");
        } catch (IllegalArgumentException ex) {
            assertEquals("Unknown task token: token", ex.getMessage());
        }
    }

    /**
     * 测试任务执行.
     */
    public void testRun() {
        Launcher launcher = new Launcher();
        launcher.addMainTask("token", MockOkTask.class);

        assertEquals(0, launcher.run("token", "0"));
        assertEquals(sign.get(), 0);
        assertEquals(0, launcher.run("token", "1"));
        assertEquals(sign.get(), 1);
        assertEquals(0, launcher.run("token", "-1"));
        assertEquals(sign.get(), -1);

        try {
            launcher.run("token", "a");
            fail("catch NumberFormatException");
        } catch (NumberFormatException ex) {
            assertEquals("For input string: \"a\"", ex.getMessage());
        }
    }

    /**
     * 测试任务抛出显式异常.
     */
    public void testRunThrow() {
        Launcher launcher = new Launcher();
        launcher.addMainTask("token", MockThrowTask.class);

        try {
            launcher.run("token", "a");
            fail("catch LaunchException");
        } catch (LaunchException ex) {
            assertEquals("main(String[]) throws exception", ex.getMessage());
            assertNotNull(ex.getCause());
            assertSame(IOException.class, ex.getCause().getClass());
            assertEquals("stop", ex.getCause().getMessage());
        }
    }

    /**
     * 测试任务执行异常.
     */
    public void testRunFailed() {
        Launcher launcher = new Launcher();
        launcher.addMainTask("main", MockErrorNoMainTask.class);
        launcher.addMainTask("static", MockNotStaticTask.class);
        launcher.addMainTask("access", MockErrorAccessTask.class);

        try {
            assertEquals(0, launcher.run("main", "0"));
            fail("catch LaunchException");
        } catch (LaunchException ex) {
            assertEquals("main(String[]) not found", ex.getMessage());
            assertNotNull(ex.getCause());
            assertSame(NoSuchMethodException.class, ex.getCause().getClass());
            assertEquals(MockErrorNoMainTask.class.getName() + ".main([Ljava.lang.String;)", ex.getCause().getMessage());
        }

        try {
            assertEquals(0, launcher.run("static", "0"));
            fail("catch LaunchException");
        } catch (LaunchException ex) {
            assertEquals("main(String[]) not static", ex.getMessage());
            assertNull(ex.getCause());
        }

        try {
            assertEquals(0, launcher.run("access", "0"));
            fail("catch LaunchException");
        } catch (LaunchException ex) {
            assertEquals("main(String[]) invoke failed", ex.getMessage());
            assertNotNull(ex.getCause());
            assertSame(IllegalAccessException.class, ex.getCause().getClass());
        }
    }

    private static class Launcher extends MainLauncher {
        public final void addMainTask(String token, Class<?> taskClass) {
            addTask(token, taskClass);
        }
    }

    public static class MockOkTask {
        public static void main(String[] args) {
            sign.set(Integer.parseInt(args[0]));
        }
    }

    public static class MockThrowTask {
        public static void main(String[] args) throws Exception {
            throw new IOException("stop");
        }
    }

    public static class MockNotStaticTask {
        public final void main(String[] args) {
            sign.set(Integer.parseInt(args[0]));
        }
    }

    private static class MockErrorAccessTask {
        public static void main(String[] args) {
            sign.set(Integer.parseInt(args[0]));
        }
    }

    public static class MockErrorNoMainTask {
        public static void main2(String[] args) {
            sign.set(Integer.parseInt(args[0]));
        }
    }
}
