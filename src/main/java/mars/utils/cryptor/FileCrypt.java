package mars.utils.cryptor;

import org.junit.Test;

public class FileCrypt {

    private static final String KEY = "65Qp6Ahy1wcX8Wgt";

    @Test
    public void testAESDecrypt() {
        String aesKey = KEY;
        String encryptContent = "";
        System.out.println("originData:" + encryptContent);

        String decryptData = AESUtil.decrypt(encryptContent, aesKey);
        System.out.println("decryptData:" + decryptData);
    }

}
