package io.github.dbstarll.utils.lang.launcher;

public interface Task {
    /**
     * Execute the command with the given arguments.
     *
     * @param args command specific arguments.
     * @return exit code.
     * @throws Throwable Exception
     */
    int run(String... args) throws Throwable;
}
