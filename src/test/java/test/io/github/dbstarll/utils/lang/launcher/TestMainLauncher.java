package test.io.github.dbstarll.utils.lang.launcher;

import io.github.dbstarll.utils.lang.launcher.AbstractLauncher;
import io.github.dbstarll.utils.lang.launcher.LaunchException;
import io.github.dbstarll.utils.lang.launcher.MainLauncher;
import junit.framework.TestCase;

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
     *
     * @throws Throwable Exception
     */
    public void testNullParams() throws Throwable {
        Launcher launcher = new Launcher();

        try {
            launcher.run((String[]) null);
            fail("catch 'Need task token.'");
        } catch (IllegalArgumentException ex) {
            assertEquals("Need task token.", ex.getMessage());
        }

        try {
            launcher.run(new String[]{});
            fail("catch 'Need task token.'");
        } catch (IllegalArgumentException ex) {
            assertEquals("Need task token.", ex.getMessage());
        }
    }

    /**
     * 测试{@link AbstractLauncher#run(String[])}在无对应任务时抛出异常.
     *
     * @throws Throwable Exception
     */
    public void testNoMatchToken() throws Throwable {
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
     *
     * @throws Throwable Exception
     */
    public void testRun() throws Throwable {
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
     * 测试任务执行异常.
     *
     * @throws Throwable Exception
     */
    public void testRunFailed() throws Throwable {
        Launcher launcher = new Launcher();
        launcher.addMainTask("main", MockErrorNoMainTask.class);
        launcher.addMainTask("access", MockErrorAccessTask.class);

        try {
            assertEquals(0, launcher.run("main", "0"));
            fail("catch IllegalArgumentException");
        } catch (IllegalArgumentException ex) {
            assertEquals(LaunchException.class, ex.getCause().getClass());
            assertEquals(NoSuchMethodException.class, ex.getCause().getCause().getClass());
        }

        try {
            assertEquals(0, launcher.run("access", "0"));
            fail("catch IllegalArgumentException");
        } catch (IllegalArgumentException ex) {
            assertEquals(LaunchException.class, ex.getCause().getClass());
            assertEquals(IllegalAccessException.class, ex.getCause().getCause().getClass());
        }
    }

    private static class Launcher extends MainLauncher {
        public final void addMainTask(String token, Class<? extends Object> taskClass) {
            addTask(token, taskClass);
        }
    }

    public static class MockOkTask {
        public static final void main(String[] args) {
            sign.set(Integer.parseInt(args[0]));
        }
    }

    private static class MockErrorAccessTask {
        @SuppressWarnings("unused")
        public final void main(String[] args) {
            sign.set(Integer.parseInt(args[0]));
        }
    }

    public static class MockErrorNoMainTask {
        public static final void main2(String[] args) {
            sign.set(Integer.parseInt(args[0]));
        }
    }
}
