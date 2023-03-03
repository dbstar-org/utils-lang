package io.github.dbstarll.utils.lang.launcher;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public abstract class MainLauncher extends AbstractLauncher<Object> {
    @Override
    protected final int run(final Class<?> taskClass, final String... args) throws LaunchException {
        final Method method;
        try {
            method = taskClass.getMethod("main", String[].class);
        } catch (NoSuchMethodException ex) {
            throw new LaunchException("main(String[]) not found", ex);
        }

        if (!Modifier.isStatic(method.getModifiers())) {
            throw new LaunchException("main(String[]) not static");
        }

        try {
            method.invoke(taskClass, (Object) args);
        } catch (InvocationTargetException ex) {
            final Throwable target = ex.getTargetException();
            if (target instanceof RuntimeException) {
                throw (RuntimeException) target;
            } else {
                throw new LaunchException("main(String[]) throws exception", target);
            }
        } catch (IllegalAccessException ex) {
            throw new LaunchException("main(String[]) invoke failed", ex);
        }
        return 0;
    }
}
