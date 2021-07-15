package test.io.github.dbstarll.utils.lang.digest;

import io.github.dbstarll.utils.lang.digest.Digestor;
import io.github.dbstarll.utils.lang.digest.Md5Digestor;
import io.github.dbstarll.utils.lang.test.DigestorTestCase;

import java.security.NoSuchAlgorithmException;

public class TestMd5Digestor extends DigestorTestCase {
    @Override
    protected Digestor getDigestor() throws NoSuchAlgorithmException {
        return new Md5Digestor();
    }
}
