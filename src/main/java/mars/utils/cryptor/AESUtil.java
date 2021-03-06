package mars.utils.cryptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;

public class AESUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(AESUtil.class);

    private static final String KEY_ALGORITHM = "AES";
    private static final String ENCODING = "utf-8";

    public static String generateAESKey() {
        KeyGenerator keyGenerator;

        try {
            keyGenerator = KeyGenerator.getInstance(KEY_ALGORITHM);
        } catch (NoSuchAlgorithmException var3) {
            return null;
        }

        keyGenerator.init(128);
        SecretKey key = keyGenerator.generateKey();
        byte[] keyExternal = key.getEncoded();
        return Base64Util.encode(keyExternal);
    }

    public static String encrypt(String content, String key) {
        try {
            byte[] bytesKey = Base64Util.decode(key);
            SecretKeySpec secretKey = new SecretKeySpec(bytesKey, KEY_ALGORITHM);
            Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
            byte[] byteContent = content.getBytes(ENCODING);
            cipher.init(1, secretKey);
            byte[] result = cipher.doFinal(byteContent);
            return Base64Util.encode(result);
        } catch (Exception var7) {
            LOGGER.error("encrypt error", var7);
            return null;
        }
    }

    public static String decrypt(String content, String key) {
        try {
            byte[] bytesKey = Base64Util.decode(key);
            SecretKeySpec secretKey = new SecretKeySpec(bytesKey, KEY_ALGORITHM);
            Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
            cipher.init(2, secretKey);
            byte[] result = cipher.doFinal(Base64Util.decode(content));
            return new String(result);
        } catch (Exception var6) {
            LOGGER.error("decrypt error", var6);
            return null;
        }
    }

    public static String encryptDirectUseCBC(String content, String key) {
        try {
            byte[] byteContent = content.getBytes(ENCODING);
            byte[] bytesKey = key.getBytes(StandardCharsets.UTF_8);
            SecretKeySpec secretKey = new SecretKeySpec(bytesKey, KEY_ALGORITHM);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(1, secretKey, new IvParameterSpec(initIV("AES/CBC/PKCS5Padding")));
            byte[] result = cipher.doFinal(byteContent);
            return Base64Util.encode(result);
        } catch (Exception var7) {
            LOGGER.error("encrypt error", var7);
            return null;
        }
    }

    public static String decryptDirectUseCBC(String content, String key) {
        try {
            byte[] contentBytes = Base64Util.decode(content);
            byte[] bytesKey = key.getBytes(ENCODING);
            SecretKeySpec secretKey = new SecretKeySpec(bytesKey, KEY_ALGORITHM);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(initIV("AES/CBC/PKCS5Padding")));
            byte[] result = cipher.doFinal(contentBytes);
            return new String(result);
        } catch (Exception var6) {
            LOGGER.error("decrypt error", var6);
            return null;
        }
    }

    private static byte[] initIV(String aesCbcPkcAlg) {
        Cipher cp;
        try {
            cp = Cipher.getInstance(aesCbcPkcAlg);
            int blockSize = cp.getBlockSize();
            byte[] iv = new byte[blockSize];
            for (int i = 0; i < blockSize; ++i) {
                iv[i] = 0;
            }
            return iv;

        } catch (Exception e) {
            int blockSize = 16;
            byte[] iv = new byte[blockSize];
            for (int i = 0; i < blockSize; ++i) {
                iv[i] = 0;
            }
            return iv;
        }
    }

}
