package mars.utils.cryptor;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.util.*;

@Slf4j
public class TestCrypt {

    private static String RSA_PUBLIC_KEY = "";

    private static String RSA_PRIVATE_KEY = "";

    @Test
    public void testKey() {
        KeyPair keyPair = RSAUtil.generateRSAKeyPair(2048);
        RSAUtil.printPublicKeyInfo(keyPair.getPublic());
        RSAUtil.printPrivateKeyInfo(keyPair.getPrivate());
    }

    @Test
    public void testEncrypt() throws Exception {
        String publicKey = RSA_PUBLIC_KEY;
        String privateKey = RSA_PRIVATE_KEY;
        String originDate = "aaaa";
        String encryptKey =  Base64Util.encode(
                Objects.requireNonNull(
                        RSAUtil.encryptData(originDate.getBytes(StandardCharsets.UTF_8), RSAUtil.loadPublicKey(publicKey))
                )
        );

        System.out.println(encryptKey);

        String decryptKey = new String(
                Objects.requireNonNull(
                        RSAUtil.decryptData(Base64Util.decode(encryptKey), RSAUtil.loadPrivateKey(privateKey))
                )
        );

        System.out.println(decryptKey);
    }

    @Test
    public void encryptAndDecrypt() {
        String publicKey = RSA_PUBLIC_KEY;
        String privateKey = RSA_PRIVATE_KEY;

        try {
            //模拟发送消息流程
            String data = "a";
            System.out.println("originData:" + data);

            String aesKey = AESUtil.generateAESKey();//返回base64 string
            System.out.println("aesKey:" + aesKey);
            assert aesKey != null;
            String encryptData = AESUtil.encrypt(data, aesKey);
//            String encryptKey = RSAUtil.encryptByPublicKey(aesKey, publicKey);
            String encryptKey =  Base64Util.encode(
                    Objects.requireNonNull(
                            RSAUtil.encryptData(aesKey.getBytes(StandardCharsets.UTF_8), RSAUtil.loadPublicKey(publicKey))
                    )
            );
            System.out.println("encryptData:" + encryptData);
            System.out.println("encryptKey:"+ encryptKey);

            //模拟接受消息流程
//            String decryptKey = RSAUtil.decryptByPrivateKey(encryptKey, privateKey);
            String decryptKey = new String(
                    Objects.requireNonNull(
                            RSAUtil.decryptData(Base64Util.decode(encryptKey), RSAUtil.loadPrivateKey(privateKey))
                    )
            );
            String decryptData = AESUtil.decrypt(encryptData, decryptKey);
            System.out.println("decryptData:" + decryptData);
            System.out.println("decryptKey:"+ decryptKey);

            System.currentTimeMillis();
        } catch (Exception e) {
            System.currentTimeMillis();
        }
    }

    @Test
    public void signAndUnSign() {
        String publicKey = RSA_PUBLIC_KEY;
        String privateKey = RSA_PRIVATE_KEY;

        Map<String, Object> map = new HashMap<>();
        map.put("a", "b");
        String sign = SignUtil.signByPrivateKey(map, privateKey);

        map.put("sign", sign);
        boolean check = SignUtil.verifySign(map, publicKey);
        System.out.println(check);
    }
}
