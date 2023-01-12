package test.io.github.dbstarll.utils.lang.launcher;

import io.github.dbstarll.utils.lang.launcher.LaunchException;
import io.github.dbstarll.utils.lang.launcher.Task;
import io.github.dbstarll.utils.lang.launcher.TaskLauncher;
import junit.framework.TestCase;

import java.util.concurrent.atomic.AtomicInteger;

public class TestTaskLauncher extends TestCase {
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
     * 测试任务执行.
     *
     * @throws Throwable Exception
     */
    public void testRun() throws Throwable {
        Launcher launcher = new Launcher();
        launcher.addMainTask("token", MockOkTask.class);

        assertEquals(0, launcher.run("token", "0"));
        assertEquals(sign.get(), 0);
        assertEquals(1, launcher.run("token", "1"));
        assertEquals(sign.get(), 1);
        assertEquals(-1, launcher.run("token", "-1"));
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
        launcher.addMainTask("token", MockErrorTask.class);

        try {
            assertEquals(0, launcher.run("token", "0"));
            fail("catch IllegalArgumentException");
        } catch (IllegalArgumentException ex) {
            assertEquals(LaunchException.class, ex.getCause().getClass());
            assertEquals(IllegalAccessException.class, ex.getCause().getCause().getClass());
        }
    }

    private static class Launcher extends TaskLauncher {
        public final void addMainTask(String token, Class<? extends Task> taskClass) {
            addTask(token, taskClass);
        }
    }

    public static class MockOkTask implements Task {
        @Override
        public int run(String... args) {
            sign.set(Integer.parseInt(args[0]));
            return sign.get();
        }
    }

    public static class MockErrorTask implements Task {
        private MockErrorTask() {
            // 禁止实例化
        }

        @Override
        public int run(String... args) {
            sign.set(Integer.parseInt(args[0]));
            return sign.get();
        }
    }
}
