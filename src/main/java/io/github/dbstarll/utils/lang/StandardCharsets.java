package io.github.dbstarll.utils.lang;

import java.nio.charset.Charset;

/**
 * Constant definitions for the standard {@link Charset Charsets}. These
 * charsets are guaranteed to be available on every implementation of the Java
 * platform.
 *
 * @deprecated use java.nio.charset.StandardCharsets
 */
@Deprecated
public final class StandardCharsets {
    private StandardCharsets() {
        //隐藏构造器
    }

    /**
     * Seven-bit ASCII, a.k.a. ISO646-US, a.k.a. the Basic Latin block of the Unicode character set
     */
    public static final Charset US_ASCII = java.nio.charset.StandardCharsets.US_ASCII;

    /**
     * ISO Latin Alphabet No. 1, a.k.a. ISO-LATIN-1
     */
    public static final Charset ISO_8859_1 = java.nio.charset.StandardCharsets.ISO_8859_1;

    /**
     * Eight-bit UCS Transformation Format.
     */
    public static final Charset UTF_8 = java.nio.charset.StandardCharsets.UTF_8;
}
