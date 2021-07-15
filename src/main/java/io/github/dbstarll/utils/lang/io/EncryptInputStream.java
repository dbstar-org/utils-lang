package io.github.dbstarll.utils.lang.io;

import io.github.dbstarll.utils.lang.EncryptUtils;
import io.github.dbstarll.utils.lang.bytes.Bytes;
import io.github.dbstarll.utils.lang.bytes.BytesUtils;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class EncryptInputStream extends FilterInputStream {
    private final Bytes encryptedKey;
    private long position;
    private long mark;

    /**
     * Encrypt输入流，对流中的数据进行Encrypt编码.
     *
     * @param in           输入流
     * @param encryptedKey 编码的key
     */
    public EncryptInputStream(InputStream in, Bytes encryptedKey) {
        super(in);
        this.encryptedKey = encryptedKey;
        this.position = 0;
        this.mark = -1;
    }

    @Override
    public int read() throws IOException {
        return BytesUtils.byte2int(EncryptUtils.getEncryptedByte((byte) in.read(), encryptedKey, position++));
    }

    @Override
    public int read(byte[] bt) throws IOException {
        return read(bt, 0, bt.length);
    }

    @Override
    public int read(byte[] bt, int off, int len) throws IOException {
        int read = in.read(bt, off, len);
        if (read > 0) {
            position += EncryptUtils.encrypt(bt, off, read, encryptedKey, position);
        }
        return read;
    }

    @Override
    public long skip(long nb) throws IOException {
        long lb = super.skip(nb);
        this.position += lb;
        return lb;
    }

    @Override
    public synchronized void mark(int readlimit) {
        super.mark(readlimit);
        this.mark = this.position;
    }

    @Override
    public synchronized void reset() throws IOException {
        super.reset();
        if (this.mark >= 0) {
            this.position = this.mark;
        }
    }
}
