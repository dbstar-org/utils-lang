package io.github.dbstarll.utils.lang.launcher;

public class LaunchException extends Exception {
    private static final long serialVersionUID = 4299963443783298869L;

    /**
     * 构造LaunchException.
     *
     * @param cause the cause (which is saved for later retrieval by the
     *              {@link #getCause()} method).  (A <tt>null</tt> value is
     *              permitted, and indicates that the cause is nonexistent or
     *              unknown.)
     */
    public LaunchException(final Throwable cause) {
        super(cause);
    }
}
