package mars.utils.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class TestJson {
    public static ObjectMapper objectMapper = new ObjectMapper();
    public static Gson gson = new Gson();

    public static void main(String [] args) {

        String a = "{" +
                "\"a\":1," +
                "\"c\":[" +
                "{\n" +
                "\"organizationId\":\"45\"," +
                "\"percent\":100," +
                "\"priority\":0," +
                "\"toOrgId\":0" +
                "}" +
                "]}";
//        String a = "{" +
//                "\"a\":1," +
//                "\"c\":[1,2,3]}";




        try {
//            JsonModel b = JSON.parseObject(a, JsonModel.class);
//            JsonModel b = JSON.parseObject(a, JsonModel.class);
//            JsonModel c = objectMapper.readValue(a, JsonModel.class);
//            JsonModel c = gson.fromJson(a, JsonModel.class);
//            c.setD("aaa");
//
//            String str = gson.toJson(c);
//            JsonObject jsonObject = gson.fromJson(str, JsonObject.class);
//            JSONObject fastJson = JSON.parseObject(str);
//            System.out.println(jsonObject.get("c").getAsString());
//            System.out.println(fastJson.get("d"));

            String a1 = null;
            System.out.println("aaaa" + a1);

            System.currentTimeMillis();
//            if (CollectionUtils.isNotEmpty(c.getC())) {
//                String d = gson.toJson(c.getC());
//                JsonArray jsonArray = new JsonParser().parse(d).getAsJsonArray();
//                System.currentTimeMillis();
//            }

        } catch (Exception e) {
            e.printStackTrace();
        }





    }

}


