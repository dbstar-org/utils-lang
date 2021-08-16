package io.github.dbstarll.utils.lang.security;

import javax.crypto.Mac;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

public final class MacBuilder extends AbstractSecurityBuilder<Mac, MacAlgorithm> {
    /**
     * 构造MacBuilder.
     *
     * @param algorithm MacAlgorithm
     * @throws NoSuchAlgorithmException 未知算法
     * @throws InstanceException        不能实例化
     */
    public MacBuilder(final MacAlgorithm algorithm) throws NoSuchAlgorithmException, InstanceException {
        super(Mac.class, algorithm);
    }

    /**
     * 设置key.
     *
     * @param key key
     * @return MacBuilder
     * @throws InvalidKeyException key异常
     */
    public MacBuilder key(final Key key) throws InvalidKeyException {
        getType().init(key);
        return this;
    }
}
