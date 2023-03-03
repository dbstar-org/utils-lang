package io.github.dbstarll.utils.lang.launcher;

import org.apache.commons.lang3.ArrayUtils;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractLauncher<E> implements Task {
    /**
     * map of tasks with token.
     */
    private final Map<String, Class<? extends E>> tasks = new HashMap<>();

    protected final void addTask(final String token, final Class<? extends E> taskClass) {
        tasks.put(token, taskClass);
    }

    @Override
    public final int run(final String... args) {
        if (ArrayUtils.isEmpty(args)) {
            throw new IllegalArgumentException("Need task token.");
        }

        final String token = args[0];
        final Class<? extends E> taskClass = tasks.get(token);
        if (taskClass == null) {
            throw new IllegalArgumentException("Unknown task token: " + token);
        }

        return run(taskClass, ArrayUtils.subarray(args, 1, args.length));
    }

    protected abstract int run(Class<? extends E> taskClass, String... args) throws LaunchException;
}
