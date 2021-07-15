package io.github.dbstarll.utils.lang.io;

import io.github.dbstarll.utils.lang.EncryptUtils;
import io.github.dbstarll.utils.lang.bytes.Bytes;
import io.github.dbstarll.utils.lang.bytes.BytesUtils;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

public class EncryptOutputStream extends FilterOutputStream {
    private final Bytes encryptedKey;
    private long position;

    /**
     * Encrypt输出流，对流中的数据进行Encrypt编码.
     *
     * @param out          输出流
     * @param encryptedKey 编码的key
     */
    public EncryptOutputStream(OutputStream out, Bytes encryptedKey) {
        super(out);
        this.encryptedKey = encryptedKey;
        this.position = 0;
    }

    @Override
    public void write(int bt) throws IOException {
        out.write(BytesUtils.byte2int(EncryptUtils.getEncryptedByte((byte) bt, encryptedKey, position++)));
    }

    @Override
    public void write(byte[] bt) throws IOException {
        write(bt, 0, bt.length);
    }

    @Override
    public void write(byte[] bt, int off, int len) throws IOException {
        byte[] dst = Arrays.copyOfRange(bt, off, off + len);
        position += EncryptUtils.encrypt(dst, 0, dst.length, encryptedKey, position);
        out.write(dst, 0, dst.length);
    }
}
