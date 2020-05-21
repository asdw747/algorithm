package mars.utils.cryptor;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import org.apache.commons.codec.binary.Base64;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class CrytorTest {

    private static String RSA_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA28V/TvLUN5CZM5RAejRI\n" +
            "QUvo/J8gM2k+RWolMx+WVctsR+sEwtjPZRzv72sCXJgQH0rsISkZ0MEdTnlFi4N/\n" +
            "ILgq2dxNNknqG7i+n2nA4DgnKqqs7Lm23hvWvH6xbVkS2P//Yv7f2272R6nDo/OR\n" +
            "qqpZ12XSjpNHJ4g6xDNjPNn03cBg62VIIYpjQdmmepzCg+YLaka1BUAlrjZ6Jsi8\n" +
            "tM02FY/2+UiUlf/ZF1FdTN9hTYOQTRqjQ6FZv4G4XL6dKGLDCzy4RV75EykRxDbZ\n" +
            "4xv9pCozwzB1pQdgkAaAo7tOg3Qr5ATgImGVkfGmlOUxaiN/SDHP/RoBfeIgXZ0i\n" +
            "TwIDAQAB";

    private static String RSA_PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDbxX9O8tQ3kJkz\n" +
            "lEB6NEhBS+j8nyAzaT5FaiUzH5ZVy2xH6wTC2M9lHO/vawJcmBAfSuwhKRnQwR1O\n" +
            "eUWLg38guCrZ3E02SeobuL6facDgOCcqqqzsubbeG9a8frFtWRLY//9i/t/bbvZH\n" +
            "qcOj85GqqlnXZdKOk0cniDrEM2M82fTdwGDrZUghimNB2aZ6nMKD5gtqRrUFQCWu\n" +
            "NnomyLy0zTYVj/b5SJSV/9kXUV1M32FNg5BNGqNDoVm/gbhcvp0oYsMLPLhFXvkT\n" +
            "KRHENtnjG/2kKjPDMHWlB2CQBoCju06DdCvkBOAiYZWR8aaU5TFqI39IMc/9GgF9\n" +
            "4iBdnSJPAgMBAAECggEADOCFlRqlVJwkXeFR5dcqZKkN/b9VI64G+ApAI762EFDb\n" +
            "B1u966zIeiQ36s9aVBKU30CElWzTFmg6T17LtGEAEeq4mAtQet3Z2cX5ZV3M71Za\n" +
            "9SUK/NPk4x6+dkz2GHIfqJdXX3WPqEvYIlrQG8spvdY9bWGJ9NCsWqSgzN418lq3\n" +
            "MmuizERzbc+7Q6+mYJeHQr+48MABTDpBJPtxZavc3qQgUi5LYMQaccCdCTga7gAk\n" +
            "62Z0IKZcktH16+togZ0CPjg9TsKl/2mKgdD2UvZKsee8I3+po9+kez9jqcHEf8lv\n" +
            "pvK8N5ZjKR7e8hTeniUQP/YgsALnkQWEqaQvZDGAAQKBgQDtfFOsM9TQCurqszWs\n" +
            "hnPsHN7dUeVDfd43VKwdQFQTihb+Yqahp2EJaRdK/WpICfNVQOeQNC2ClHvNQr6D\n" +
            "KYOXzWd0pssR5MT/cyAhQWOLxHEMuiXSWSobWIzY6WpndnaqBD7s67pwizzYlgkj\n" +
            "RIqNY0yjrOE2TYXojoe4vKmXQQKBgQDs56LCDow5ARQy/vrFq8QMHgd3qeroLhQ9\n" +
            "VYBWbQnTIhyvz83DP85DUYErT/W9Y0AeesjU9QbprAtdsgHYmUNMEtDwDx0Q6BlQ\n" +
            "VauYyGJlCj00bpBN+nj+5/Qz11B7NN9YvOpeSB38bFdmhUD/6bVqF3sUzCRcn8Xa\n" +
            "DsNnEfVljwKBgQCAuHyZv9Y/lUtTPyN+ibnXZqadi0Aa3uRNVUWc7TslBBoGc+4h\n" +
            "fJmqAh4v9T9gIJSVxwI8NSvdtSGNVfnxGdVLuHe00JsM7dShKQNnQwvzu9wSq+rI\n" +
            "IrAh9lMRVyLnRVx4NqjsvbBmQ/IM6vW0+fenPwVRSekgB9DnBiomRYwegQKBgEx7\n" +
            "g9oupCqLJKBKHV/P0l0vHK4PV11Z8wLSJbrBPRjCvIwuBHnykX6+Q3teNzHLZxnv\n" +
            "/gJykKbD+m9nVp087bMTqAy4dGPO6s06u0l59/oy0gupXkEuif2RmQ9bhgogybJ2\n" +
            "WxvRII0r1ROuFYwJQZ5csknGlj6o/lElzwPpklYVAoGBAILqvNcVnD3naBRy0hMy\n" +
            "P0hlrvvrgJJT3uldJ2o/PkphE81dzxXVfrBb7OyTvIMV3YBqVM8+UahE+laTVOLJ\n" +
            "CyhKrmti3rrFX7Fs+lTvLxzyUxDzJMc8+0R4dZNKl4ReNFJa8iSxj8C6zVA4aIvu\n" +
            "sXRG6ljN6IsnXB9c5WuVeklm";


    public static void main(String [] args) {
//        encryptAndDecrypt();
        signAndUnSign();
    }

    private static void encryptAndDecrypt() {
        String publicKey = RSA_PUBLIC_KEY;
        String privateKey = RSA_PRIVATE_KEY;

        try {
            //模拟发送消息流程
            String data = "a";
            String aesKey = AESUtil.generateAESKey();//返回base64 string
            String encryptData = AESUtil.encrypt(data, aesKey);
//            String encryptKey = RSAUtils.encryptByPublicKey(aesKey, miPublicKey);
            String encryptKey =  Base64.encodeBase64String(RSAUtil.encryptData(aesKey.getBytes("utf-8"), RSAUtil.loadPublicKey(publicKey)));

            //模拟接受消息流程
//            String decryptKey = RSAUtils.decryptByPrivateKey(encryptKey, miPrivateKey);
            String decryptKey = new String(RSAUtil.decryptData(Base64.decodeBase64(encryptKey), RSAUtil.loadPrivateKey(privateKey)));
            String decryptData = AESUtil.decrypt(encryptData, decryptKey);

            System.currentTimeMillis();
        } catch (Exception e) {
            System.currentTimeMillis();
        }
    }

    private static void signAndUnSign() {
        String miPublicKey = RSA_PUBLIC_KEY;
        String miPrivateKey = RSA_PRIVATE_KEY;

        Map<String, Object> map = new HashMap<>();
        map.put("a", "b");
        String sign = SignUtil.signByPrivateKey(map, miPrivateKey);

        map.put("sign", sign);
        boolean check = SignUtil.verifySign(map, miPublicKey);
        System.out.println(check);
    }
}
