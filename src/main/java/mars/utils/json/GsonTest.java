package mars.utils.json;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GsonTest {

    public static void main(String [] args) {
//        GsonBuilder builder = new GsonBuilder();
//        builder.registerTypeAdapter(IntListType, jsonModelSerializer);
//        builder.registerTypeAdapter(new TypeToken<List<JsonModel>>() {}.getType(), jsonModelSerializer);

        GsonBuilder builder = JsonUtil.getBuilder(new TypeToken<JsonModel>() {}.getType(), new JsonModelSerializer());
        Gson gson = builder.create();

        JsonModel jsonModel = new JsonModel();
        jsonModel.setA(1);
        jsonModel.setB(3);
        String dataContent = gson.toJson(jsonModel);
        System.out.println(dataContent);

        List<JsonModel> models = new ArrayList<>();
        JsonModel jsonModelItem1 = new JsonModel();
        jsonModelItem1.setA(2);
        models.add(jsonModel);
        String dataContent1 = gson.toJson(models);
        System.out.println(dataContent1);
    }

}


