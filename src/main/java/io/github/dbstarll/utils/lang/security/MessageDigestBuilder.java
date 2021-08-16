package io.github.dbstarll.utils.lang.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class MessageDigestBuilder extends AbstractSecurityBuilder<MessageDigest, MessageDigestAlgorithm> {
    public MessageDigestBuilder(final MessageDigestAlgorithm algorithm) throws NoSuchAlgorithmException, InstanceException {
        super(MessageDigest.class, algorithm);
    }
}
