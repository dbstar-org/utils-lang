package io.github.dbstarll.utils.lang.launcher;

public class LaunchException extends RuntimeException {
    private static final long serialVersionUID = 4299963443783298869L;

    /**
     * 构造LaunchException.
     *
     * @param message the detail message (which is saved for later retrieval
     *                by the {@link #getMessage()} method).
     * @param cause   the cause (which is saved for later retrieval by the
     *                {@link #getCause()} method).  (A <tt>null</tt> value is
     *                permitted, and indicates that the cause is nonexistent or
     *                unknown.)
     */
    public LaunchException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * 构造LaunchException.
     *
     * @param message the detail message (which is saved for later retrieval
     *                by the {@link #getMessage()} method).
     */
    public LaunchException(final String message) {
        super(message);
    }
}
