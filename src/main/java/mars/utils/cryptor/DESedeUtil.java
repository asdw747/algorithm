package mars.utils.cryptor;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.InputStream;
import java.io.OutputStream;

public class DESedeUtil {
    private static final Logger log = LoggerFactory.getLogger(DESedeUtil.class);
    private static final String ALGO = "DESede";

    public DESedeUtil() {
    }

    public static void encrypt(InputStream in, OutputStream out, String keyStr) {
        encrypt(in, out, keyStr.getBytes());
    }

    public static void encrypt(InputStream in, OutputStream out, byte[] key) {
        try {
            SecretKey secretKey = new SecretKeySpec(key, "DESede");
            Cipher cipher = Cipher.getInstance("DESede");
            cipher.init(1, secretKey);
            OutputStream cos = new CipherOutputStream(out, cipher);
            byte[] buffer = new byte[8192];

            int len;
            while(-1 != (len = in.read(buffer))) {
                cos.write(buffer, 0, len);
            }

            cos.close();
        } catch (Exception var11) {
            log.error("EncryptUtils.encrypt error, ", var11);
            throw new RuntimeException("EncryptUtils.encrypt error, " + var11.toString());
        } finally {
            IOUtils.closeQuietly(in);
            IOUtils.closeQuietly(out);
        }
    }

    public static void decrypt(InputStream in, OutputStream out, String keyStr) {
        decrypt(in, out, keyStr.getBytes());
    }

    public static void decrypt(InputStream in, OutputStream out, byte[] key) {
        try {
            SecretKey secretKey = new SecretKeySpec(key, "DESede");
            Cipher cipher = Cipher.getInstance("DESede");
            cipher.init(2, secretKey);
            OutputStream cos = new CipherOutputStream(out, cipher);
            byte[] buffer = new byte[8192];

            int len;
            while(-1 != (len = in.read(buffer))) {
                cos.write(buffer, 0, len);
            }

            cos.close();
        } catch (Exception var11) {
            log.error("EncryptUtils.decrypt error, ", var11);
            throw new RuntimeException("EncryptUtils.decrypt error, " + var11.toString());
        } finally {
            IOUtils.closeQuietly(in);
            IOUtils.closeQuietly(out);
        }
    }
}
