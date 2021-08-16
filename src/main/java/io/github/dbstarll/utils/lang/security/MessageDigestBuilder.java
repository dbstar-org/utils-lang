package io.github.dbstarll.utils.lang.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class MessageDigestBuilder extends AbstractSecurityBuilder<MessageDigest, MessageDigestAlgorithm> {
    /**
     * 构造MessageDigestBuilder.
     *
     * @param algorithm 算法
     * @throws NoSuchAlgorithmException 未知算法
     * @throws InstanceException        不能实例化
     */
    public MessageDigestBuilder(final MessageDigestAlgorithm algorithm)
            throws NoSuchAlgorithmException, InstanceException {
        super(MessageDigest.class, algorithm);
    }
}
