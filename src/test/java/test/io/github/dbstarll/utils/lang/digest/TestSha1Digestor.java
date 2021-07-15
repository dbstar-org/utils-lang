package test.io.github.dbstarll.utils.lang.digest;

import io.github.dbstarll.utils.lang.digest.Digestor;
import io.github.dbstarll.utils.lang.digest.Sha1Digestor;
import io.github.dbstarll.utils.lang.test.DigestorTestCase;

import java.security.NoSuchAlgorithmException;

public class TestSha1Digestor extends DigestorTestCase {
    @Override
    protected Digestor getDigestor() throws NoSuchAlgorithmException {
        return new Sha1Digestor();
    }
}
