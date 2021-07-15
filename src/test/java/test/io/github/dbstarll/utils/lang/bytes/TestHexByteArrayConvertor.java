package test.io.github.dbstarll.utils.lang.bytes;

import io.github.dbstarll.utils.lang.bytes.ByteArrayConvertor;
import io.github.dbstarll.utils.lang.bytes.HexByteArrayConvertor;
import io.github.dbstarll.utils.lang.test.ByteArrayConvertorTestCase;

public class TestHexByteArrayConvertor extends ByteArrayConvertorTestCase {
    @Override
    protected ByteArrayConvertor buildByteArrayConvertor() {
        return new HexByteArrayConvertor();
    }
}
