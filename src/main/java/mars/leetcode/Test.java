package mars.leetcode;

import com.alibaba.fastjson.JSON;
import com.sun.org.apache.xpath.internal.operations.Mod;
import lombok.Data;
import org.json.JSONObject;

import javax.jws.WebParam;
import java.util.*;

public class Test {

    public static void main(String [] args) {
//        Map<Integer, String> map = new HashMap<>();
//        map.put(1, "a");
//        map.put(2, "b");

//        for (Map.Entry<Integer, String> entry : map.entrySet()) {
//            System.out.println(entry.getKey());
//        }

        List<Integer> ruleIds = new ArrayList<>();
        ruleIds.add(3);
        ruleIds.add(2);
        ruleIds.add(15);

        ruleIds.sort(Comparator.comparing(item -> item));
        System.out.println(ruleIds);

    }



}

@Data
class Model {
    Integer a;
    Integer b;
}