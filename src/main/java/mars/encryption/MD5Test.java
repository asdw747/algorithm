package mars.encryption;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

public class MD5Test {

    public static void main(String [] args) {

        try {
            String signCheck= DigestUtils.md5Hex("211394775"+
                    "1554901197660" +
                    "190410061145607586" +
                    "220211628420190410" +
                    "2" +
                    "UznNdgtRAUCZfVKyC9D6hw==");

            System.out.println(signCheck);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static String md5(String text, String key) throws Exception {
        //加密后的字符串
        String encodeStr= DigestUtils.md5Hex(text + key);
        System.out.println("MD5加密后的字符串为:encodeStr="+encodeStr);
        return encodeStr;
    }

}
