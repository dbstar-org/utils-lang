package io.github.dbstarll.utils.lang.security;

import io.github.dbstarll.utils.lang.enums.EnumUtils;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;

public final class CipherBuilder extends AbstractSecurityBuilder<Cipher, CipherAlgorithm> {
    /**
     * 构建CipherBuilder.
     *
     * @param algorithm 算法
     * @param mode      算法模式
     * @param padding   padding
     * @throws NoSuchAlgorithmException 算法不存在
     * @throws InstanceException        实例化失败
     */
    public CipherBuilder(final CipherAlgorithm algorithm,
                         final CipherAlgorithmMode mode,
                         final CipherAlgorithmPadding padding)
            throws NoSuchAlgorithmException, InstanceException {
        super(Cipher.class, new TransformationInstancer(algorithm, mode, padding));
    }

    /**
     * 设置加密用的key和随机数.
     *
     * @param key    key
     * @param random 随机数
     * @return CipherBuilder
     * @throws InvalidKeyException 无效的key
     */
    public CipherBuilder encrypt(final Key key, final SecureRandom random) throws InvalidKeyException {
        getType().init(Cipher.ENCRYPT_MODE, key, random);
        return this;
    }

    /**
     * 设置加密用的key，参数和随机数.
     *
     * @param key    key
     * @param spec   参数
     * @param random 随机数
     * @return CipherBuilder
     * @throws InvalidKeyException                无效的key
     * @throws InvalidAlgorithmParameterException 无效的参数
     */
    public CipherBuilder encrypt(final Key key, final AlgorithmParameterSpec spec, final SecureRandom random)
            throws InvalidKeyException, InvalidAlgorithmParameterException {
        getType().init(Cipher.ENCRYPT_MODE, key, spec, random);
        return this;
    }


    /**
     * 设置解密用的key和随机数.
     *
     * @param key    key
     * @param random 随机数
     * @return CipherBuilder
     * @throws InvalidKeyException 无效的key
     */
    public CipherBuilder decrypt(final Key key, final SecureRandom random) throws InvalidKeyException {
        getType().init(Cipher.DECRYPT_MODE, key, random);
        return this;
    }

    /**
     * 设置解密用的key，参数和随机数.
     *
     * @param key        key
     * @param parameters 参数
     * @param random     随机数
     * @return CipherBuilder
     * @throws InvalidKeyException                无效的key
     * @throws InvalidAlgorithmParameterException 无效的参数
     */
    public CipherBuilder decrypt(final Key key, final AlgorithmParameters parameters, final SecureRandom random)
            throws InvalidKeyException, InvalidAlgorithmParameterException {
        getType().init(Cipher.DECRYPT_MODE, key, parameters, random);
        return this;
    }

    /**
     * 设置解密用的key，参数和随机数.
     *
     * @param key    key
     * @param spec   参数
     * @param random 随机数
     * @return CipherBuilder
     * @throws InvalidKeyException                无效的key
     * @throws InvalidAlgorithmParameterException 无效的参数
     */
    public CipherBuilder decrypt(final Key key, final AlgorithmParameterSpec spec, final SecureRandom random)
            throws InvalidKeyException, InvalidAlgorithmParameterException {
        getType().init(Cipher.DECRYPT_MODE, key, spec, random);
        return this;
    }

    private static class TransformationInstancer implements Instancer<Cipher> {
        private final String transformation;

        TransformationInstancer(final CipherAlgorithm algorithm, final CipherAlgorithmMode mode,
                                final CipherAlgorithmPadding padding) {
            final StringBuilder builder = new StringBuilder(EnumUtils.name(algorithm));
            if (mode != null) {
                builder.append('/').append(EnumUtils.name(mode));
            }
            if (padding != null) {
                builder.append('/').append(EnumUtils.name(padding));
            }
            this.transformation = builder.toString();
        }

        @Override
        public Cipher getInstance(final Class<Cipher> typeClass) throws InstanceException {
            try {
                return Cipher.getInstance(transformation);
            } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
                throw new InstanceException("getInstance failed for: " + typeClass.getName(), e);
            }
        }
    }
}
