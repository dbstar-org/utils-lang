package io.github.dbstarll.utils.lang.launcher;

import org.apache.commons.lang3.ArrayUtils;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractLauncher<E> implements Task {
    /**
     * map of tasks with token.
     */
    private final Map<String, Class<? extends E>> tasks = new HashMap<String, Class<? extends E>>();

    protected final void addTask(final String token, final Class<? extends E> taskClass) {
        tasks.put(token, taskClass);
    }

    @Override
    public final int run(final String... args) throws Throwable {
        if (ArrayUtils.isEmpty(args)) {
            throw new IllegalArgumentException("Need task token.");
        }

        String token = args[0];
        Class<? extends E> taskClass = tasks.get(token);
        if (taskClass == null) {
            throw new IllegalArgumentException("Unknown task token: " + token);
        }

        try {
            return run(taskClass, ArrayUtils.subarray(args, 1, args.length));
        } catch (LaunchException ex) {
            throw new IllegalArgumentException(ex);
        }
    }

    protected abstract int run(Class<? extends E> taskClass, String... args) throws LaunchException, Throwable;
}
