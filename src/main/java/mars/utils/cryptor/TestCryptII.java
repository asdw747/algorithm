package mars.utils.cryptor;

import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class TestCryptII {

    private static final String PAR_RSA_PUBLIC_KEY = "";

    private static final String PAR_RSA_PRIVATE_KEY = "";

    private static final String RSA_PUBLIC_KEY = "";

    private static final String RSA_PRIVATE_KEY = "";


    @Test
    public void test() {
        try {
            //模拟PAR发送消息流程
            String data = "";
            String encryptData =  Base64Util.encode(
                    Objects.requireNonNull(RSAUtil.encryptData(data.getBytes(StandardCharsets.UTF_8), RSAUtil.loadPublicKey(RSA_PUBLIC_KEY)))
            );
            System.out.println("originData:" + data);
            System.out.println("encryptData:" + encryptData);

            String sign = SignUtil.signByPrivateKey(encryptData, PAR_RSA_PRIVATE_KEY);
            System.out.println("sign:" + sign);


            System.out.println("____________________________________________");
            //模拟接受消息流程
            boolean verifySign = SignUtil.verifySign(encryptData, sign, PAR_RSA_PUBLIC_KEY);
            System.out.println("verifySign:" + verifySign);
            String decryptData = new String(
                    Objects.requireNonNull(
                            RSAUtil.decryptData(Base64Util.decode(encryptData), RSAUtil.loadPrivateKey(RSA_PRIVATE_KEY))
                    )
            );
            System.out.println("decryptData:" + decryptData);

            System.currentTimeMillis();
        } catch (Exception e) {
            System.currentTimeMillis();
        }

    }

    @Test
    public void testReturn() throws Exception {
        //模拟PAR接受消息流程
        String encryptData = "";
        String sign = "";

        boolean verifySign = SignUtil.verifySign(encryptData, sign, RSA_PUBLIC_KEY);
        System.out.println("verifySign:" + verifySign);

        String decryptData = new String(
                Objects.requireNonNull(
                        RSAUtil.decryptData(Base64Util.decode(encryptData), RSAUtil.loadPrivateKey(PAR_RSA_PRIVATE_KEY))
                )
        );
        System.out.println("decryptData:" + decryptData);
    }

}
