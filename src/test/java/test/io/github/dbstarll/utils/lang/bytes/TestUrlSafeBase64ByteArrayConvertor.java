package test.io.github.dbstarll.utils.lang.bytes;

import io.github.dbstarll.utils.lang.bytes.Base64ByteArrayConvertor;
import io.github.dbstarll.utils.lang.bytes.ByteArrayConvertor;
import io.github.dbstarll.utils.lang.test.ByteArrayConvertorTestCase;

public class TestUrlSafeBase64ByteArrayConvertor extends ByteArrayConvertorTestCase {
    @Override
    protected ByteArrayConvertor buildByteArrayConvertor() {
        return new Base64ByteArrayConvertor(true);
    }
}
