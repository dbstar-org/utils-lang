package io.github.dbstarll.utils.lang.launcher;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class MainLauncher extends AbstractLauncher<Object> {
    @Override
    protected final int run(final Class<? extends Object> taskClass, final String... args) throws Throwable {
        Method method;
        try {
            method = taskClass.getMethod("main", new Class[]{String[].class});
        } catch (Throwable ex) {
            throw new LaunchException(ex);
        }

        try {
            method.invoke(taskClass, new Object[]{args});
        } catch (InvocationTargetException ex) {
            throw ex.getTargetException();
        } catch (Throwable ex) {
            throw new LaunchException(ex);
        }
        return 0;
    }
}
