package io.github.dbstarll.utils.lang.main;

import io.github.dbstarll.utils.lang.EncryptUtils;
import io.github.dbstarll.utils.lang.bytes.Bytes;
import io.github.dbstarll.utils.lang.io.EncryptOutputStream;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public final class EncryptFile {
    private static final Logger LOGGER = LoggerFactory.getLogger(EncryptFile.class);

    private final Bytes encryptedKey;

    private EncryptFile(final Bytes encryptedKey) {
        this.encryptedKey = encryptedKey;
    }

    /**
     * 加密输入的文件.
     *
     * @param args 命令行参数
     * @throws Exception Exception
     */
    public static void main(final String[] args) throws Exception {
        final String token = "-encrypt";
        final Bytes encryptedKey = new Bytes(EncryptUtils.sha(args[0], 1));

        new EncryptFile(encryptedKey).encrypt(token, new File(args[1]));
    }

    private void encrypt(final String token, final File fileOriginal) throws IOException {
        try (FileInputStream in = new FileInputStream(fileOriginal)) {
            final File fileEncrypt = getEncryptFile(token, fileOriginal);
            try (EncryptOutputStream out = new EncryptOutputStream(new FileOutputStream(fileEncrypt), encryptedKey)) {
                IOUtils.copy(in, out);
                LOGGER.info("encrypt: {} ---> {}", fileOriginal.getName(), fileEncrypt.getName());
            }
        }
    }

    private File getEncryptFile(final String token, final File fileOriginal) {
        final File dRoot = fileOriginal.getParentFile();
        final String originalName = StringUtils.substringBeforeLast(fileOriginal.getName(), ".");
        final String suffix = StringUtils.substringAfterLast(fileOriginal.getName(), ".");
        final String encryptName = originalName.endsWith(token)
                ? originalName.substring(0, originalName.length() - token.length())
                : originalName + token;
        return new File(dRoot, encryptName + (StringUtils.isBlank(suffix) ? "" : "." + suffix));
    }
}
