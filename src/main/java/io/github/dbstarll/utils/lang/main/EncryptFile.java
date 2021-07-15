package io.github.dbstarll.utils.lang.main;

import io.github.dbstarll.utils.lang.EncryptUtils;
import io.github.dbstarll.utils.lang.bytes.Bytes;
import io.github.dbstarll.utils.lang.io.EncryptOutputStream;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class EncryptFile {
  private static final Logger LOGGER = LoggerFactory.getLogger(EncryptFile.class);

  /**
   * 加密输入的文件.
   *
   * @param args 命令行参数
   * @throws Exception Exception
   */
  public static void main(String[] args) throws Exception {
    final String token = "-encrypt";
    final Bytes encryptedKey = new Bytes(EncryptUtils.sha(args[0], 1));

    encrypt(token, encryptedKey, new File(args[1]));
  }

  private static void encrypt(final String token, Bytes encryptedKey, File fileOriginal) throws IOException {
    File fileEncrypt = getEncryptFile(token, fileOriginal);
    InputStream in = null;
    OutputStream out = null;
    try {
      in = new FileInputStream(fileOriginal);
      out = new EncryptOutputStream(new FileOutputStream(fileEncrypt), encryptedKey);
      IOUtils.copy(in, out);
    } finally {
      IOUtils.closeQuietly(in);
      IOUtils.closeQuietly(out);
    }
    LOGGER.info("encrypt: {} ---> {}", fileOriginal.getName(), fileEncrypt.getName());
  }

  private static File getEncryptFile(final String token, final File fileOriginal) {
    final File dRoot = fileOriginal.getParentFile();
    final String originalName = StringUtils.substringBeforeLast(fileOriginal.getName(), ".");
    final String suffix = StringUtils.substringAfterLast(fileOriginal.getName(), ".");
    final String encryptName = originalName.endsWith(token)
            ? originalName.substring(0, originalName.length() - token.length())
            : originalName + token;
    return new File(dRoot, encryptName + (StringUtils.isBlank(suffix) ? "" : "." + suffix));
  }
}
