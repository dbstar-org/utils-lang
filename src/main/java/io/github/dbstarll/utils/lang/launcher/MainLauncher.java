package io.github.dbstarll.utils.lang.launcher;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class MainLauncher extends AbstractLauncher<Object> {
    @Override
    protected final int run(final Class<?> taskClass, final String... args) throws LaunchException {
        Method method;
        try {
            method = taskClass.getMethod("main", String[].class);
        } catch (NoSuchMethodException ex) {
            throw new LaunchException(ex);
        }

        try {
            method.invoke(taskClass, (Object) args);
        } catch (InvocationTargetException ex) {
            final Throwable target = ex.getTargetException();
            if (target instanceof RuntimeException) {
                throw (RuntimeException) target;
            } else {
                throw new LaunchException(target);
            }
        } catch (IllegalAccessException ex) {
            throw new LaunchException(ex);
        }
        return 0;
    }
}
