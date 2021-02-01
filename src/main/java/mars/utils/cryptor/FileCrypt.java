package mars.utils.cryptor;

import org.junit.Test;

public class FileCrypt {

    private static final String KEY = "65Qp6Ahy1wcX8Wgt";

    @Test
    public void testAESDecrypt() {
        String encryptContent = "";
        System.out.println("originData:" + encryptContent);

        String decryptData = AESUtil.decryptDirectUseCBC(encryptContent, KEY);
        System.out.println("decryptData:" + decryptData);
    }

    @Test
    public void enAndDe() {
        String originData = "abc";
        String encryptData = AESUtil.encryptDirectUseCBC(originData, KEY);
        System.out.println(encryptData);

        String decryptData = AESUtil.decryptDirectUseCBC(encryptData, KEY);
        System.out.println(decryptData);
    }

}
