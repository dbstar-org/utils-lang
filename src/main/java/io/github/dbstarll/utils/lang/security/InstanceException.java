package io.github.dbstarll.utils.lang.security;

public class InstanceException extends Exception {
    private static final long serialVersionUID = 2117859069317935093L;

    /**
     * 构造InstanceException.
     *
     * @param message the detail message (which is saved for later retrieval
     *                by the {@link #getMessage()} method).
     * @param cause   the cause (which is saved for later retrieval by the
     *                {@link #getCause()} method).  (A <tt>null</tt> value is
     *                permitted, and indicates that the cause is nonexistent or
     *                unknown.)
     */
    public InstanceException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
