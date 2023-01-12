package io.github.dbstarll.utils.lang.launcher;

public interface Task {
    /**
     * Execute the command with the given arguments.
     *
     * @param args command specific arguments.
     * @return exit code.
     */
    int run(String... args);
}
