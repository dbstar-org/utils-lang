package test.io.github.dbstarll.utils.lang.io;

import io.github.dbstarll.utils.lang.EncryptUtils;
import io.github.dbstarll.utils.lang.bytes.Bytes;
import io.github.dbstarll.utils.lang.io.EncryptInputStream;
import junit.framework.TestCase;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class TestEncryptInputStream extends TestCase {
    private Bytes encryptedKey;

    @Override
    protected void setUp() throws Exception {
        this.encryptedKey = new Bytes(EncryptUtils.sha("encryptedKey", 256));
        super.setUp();
    }

    /**
     * 测试{@link EncryptInputStream}.
     *
     * @throws IOException IOException
     */
    public void testEncrypt() throws IOException {
        byte[] data = RandomUtils.nextBytes(65536);

        ByteArrayOutputStream out = new ByteArrayOutputStream(data.length);
        assertEquals(data.length,
                IOUtils.copy(new EncryptInputStream(new ByteArrayInputStream(data), encryptedKey), out));
        byte[] encryptData = out.toByteArray();

        assertNotSame(data, encryptData);
        assertFalse(Arrays.equals(data, encryptData));
        assertEquals(data.length, encryptData.length);

        ByteArrayOutputStream out2 = new ByteArrayOutputStream(encryptData.length);
        InputStream in = new EncryptInputStream(new ByteArrayInputStream(encryptData), encryptedKey);
        in.reset();
        for (int i = 0; i < 100; i++) {
            out2.write(in.read());
        }
        in.mark(2096);
        byte[] buffer = new byte[1024];
        int len = in.read(buffer);
        out2.write(Arrays.copyOf(buffer, len));
        in.reset();
        in.skip(len);
        assertEquals(encryptData.length - len - 100, IOUtils.copy(in, out2));
        byte[] encryptData2 = out2.toByteArray();

        assertNotSame(encryptData, encryptData2);
        assertFalse(Arrays.equals(encryptData, encryptData2));
        assertEquals(encryptData.length, encryptData2.length);

        assertTrue(Arrays.equals(data, encryptData2));
    }
}
