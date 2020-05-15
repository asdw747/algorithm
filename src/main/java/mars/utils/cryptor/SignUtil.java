package mars.utils.cryptor;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import org.apache.commons.codec.binary.Base64;

import java.security.PrivateKey;
import java.security.Signature;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class SignUtil {

    public static String signByPrivateKey(Map<String, Object> map, String privateKey) {
        try {
            TreeMap<String, Object> treeMap = Maps.newTreeMap();
            treeMap.putAll(map);
            String content = mapToSortedStr(treeMap);

            PrivateKey priKey = RSAUtil.loadPrivateKey(privateKey);
            Signature signature = Signature.getInstance("SHA1WithRSA");
            signature.initSign(priKey);
            signature.update(content.getBytes("utf-8"));
            byte[] signed = signature.sign();
            return new String(Base64.encodeBase64URLSafe(signed), "utf-8");
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean verifySign(Map<String, Object> map, String publicKey) {
        String sign = (String)map.get("sign");
        TreeMap<String, Object> treeMap = Maps.newTreeMap();
        treeMap.putAll(map);
        String content = mapToSortedStr(treeMap);
        return RSAUtil.verifySignByPublicKey(content, sign, publicKey);
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
