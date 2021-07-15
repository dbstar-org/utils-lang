package io.github.dbstarll.utils.lang.launcher;

public abstract class TaskLauncher extends AbstractLauncher<Task> {
    @Override
    protected final int run(Class<? extends Task> taskClass, String... args) throws LaunchException, Throwable {
        Task task;
        try {
            task = taskClass.newInstance();
        } catch (Throwable ex) {
            throw new LaunchException(ex);
        }
        return task.run(args);
    }
}
