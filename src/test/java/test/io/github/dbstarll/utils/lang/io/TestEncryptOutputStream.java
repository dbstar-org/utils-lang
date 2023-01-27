package test.io.github.dbstarll.utils.lang.io;

import io.github.dbstarll.utils.lang.EncryptUtils;
import io.github.dbstarll.utils.lang.bytes.Bytes;
import io.github.dbstarll.utils.lang.io.EncryptOutputStream;
import junit.framework.TestCase;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomUtils;

import java.io.*;
import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;

public class TestEncryptOutputStream extends TestCase {
    private Bytes encryptedKey;

    @Override
    protected void setUp() throws Exception {
        this.encryptedKey = new Bytes(EncryptUtils.sha("encryptedKey", 256));
        super.setUp();
    }

    /**
     * 测试{@link EncryptOutputStream}.
     *
     * @throws IOException IOException
     */
    public void testEncrypt() throws IOException {
        byte[] data = RandomUtils.nextBytes(65536);

        ByteArrayOutputStream out = new ByteArrayOutputStream(data.length);
        assertEquals(data.length,
                IOUtils.copy(new ByteArrayInputStream(data), new EncryptOutputStream(out, encryptedKey)));
        byte[] encryptData = out.toByteArray();

        assertNotSame(data, encryptData);
        assertFalse(Arrays.equals(data, encryptData));
        assertEquals(data.length, encryptData.length);

        ByteArrayOutputStream out2 = new ByteArrayOutputStream(encryptData.length);
        InputStream in = new ByteArrayInputStream(encryptData);
        OutputStream eout = new EncryptOutputStream(out2, encryptedKey);
        for (int i = 0; i < 100; i++) {
            eout.write(in.read());
        }
        byte[] buffer = new byte[1024];
        int len = in.read(buffer);
        eout.write(Arrays.copyOf(buffer, len));
        assertEquals(encryptData.length - len - 100, IOUtils.copy(in, eout));
        byte[] encryptData2 = out2.toByteArray();

        assertNotSame(encryptData, encryptData2);
        assertFalse(Arrays.equals(encryptData, encryptData2));
        assertEquals(encryptData.length, encryptData2.length);

        assertArrayEquals(data, encryptData2);
    }
}
