package test.io.github.dbstarll.utils.lang.main;

import io.github.dbstarll.utils.lang.main.EncryptFile;
import junit.framework.TestCase;
import org.apache.commons.io.FileUtils;

import java.io.File;

public class TestEncryptFile extends TestCase {
    /**
     * 测试{@link EncryptFile#main(String[])}.
     *
     * @throws Exception Exception
     */
    public void testEncryptFile() throws Exception {
        File srcFile = new File("src/test/resources/lines.txt");
        File srcEncryptFile = new File("src/test/resources/lines-encrypt.txt");

        File destFile = new File("target/lines.txt");
        FileUtils.copyFile(srcFile, destFile);

        File destEncryptFile = new File("target/lines-encrypt.txt");
        EncryptFile.main(new String[]{"encryptedKey", destFile.getAbsolutePath()});
        assertTrue(FileUtils.contentEquals(srcEncryptFile, destEncryptFile));
        EncryptFile.main(new String[]{"encryptedKey", destEncryptFile.getAbsolutePath()});
        assertTrue(FileUtils.contentEquals(srcFile, destFile));

        File destFileNoSuffix = new File("target/lines");
        FileUtils.copyFile(srcFile, destFileNoSuffix);
        EncryptFile.main(new String[]{"encryptedKey", destFileNoSuffix.getAbsolutePath()});
        File destEncryptFileNoSuffix = new File("target/lines-encrypt");
        assertTrue(FileUtils.contentEquals(srcEncryptFile, destEncryptFileNoSuffix));
        EncryptFile.main(new String[]{"encryptedKey", destEncryptFileNoSuffix.getAbsolutePath()});
    }
}
