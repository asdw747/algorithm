package mars.utils.cryptor;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import org.apache.commons.codec.binary.Base64;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class SignUtil {

    private static final String SIGN_ALGORITHMS = "SHA1WithRSA";
    private static final String ENCODING = "utf-8";

    public static String signByPrivateKey(Map<String, Object> map, String privateKey) {
        try {
            TreeMap<String, Object> treeMap = Maps.newTreeMap();
            treeMap.putAll(map);
            String content = mapToSortedStr(treeMap);

            PrivateKey priKey = RSAUtil.loadPrivateKey(privateKey);
            Signature signature = Signature.getInstance("SHA1WithRSA");
            signature.initSign(priKey);
            signature.update(content.getBytes(ENCODING));
            byte[] signed = signature.sign();
            return new String(Base64.encodeBase64URLSafe(signed), ENCODING);
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean verifySign(Map<String, Object> map, String publicKey) {
        String sign = (String)map.get("sign");
        TreeMap<String, Object> treeMap = Maps.newTreeMap();
        treeMap.putAll(map);
        String content = mapToSortedStr(treeMap);
        try {
            return doCheck(content.getBytes(ENCODING), Base64.decodeBase64(sign), RSAUtil.loadPublicKey(publicKey));
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean doCheck(byte[] encryptByte, byte[] bs, PublicKey publicKey) {
        try {
            Signature signature = Signature.getInstance(SIGN_ALGORITHMS);
            signature.initVerify(publicKey);
            signature.update(encryptByte);
            return signature.verify(bs);
        } catch (Exception e) {
            return false;
        }
    }

    public static String mapToSortedStr(TreeMap<String, Object> paramsMap) {
        Preconditions.checkArgument(!paramsMap.isEmpty(), "paramsMap might be empty");
        paramsMap.remove("sign");
        StringBuilder sb = new StringBuilder();
        Iterator<Map.Entry<String,Object>> var2 = paramsMap.entrySet().iterator();

        while(var2.hasNext()) {
            Map.Entry<String, Object> each = var2.next();
            sb.append(each.getKey() + "=" + each.getValue() + "&");
        }

        return sb.substring(0, sb.length() - 1);
    }

}
