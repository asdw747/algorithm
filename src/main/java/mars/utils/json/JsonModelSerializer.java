package mars.utils.json;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class JsonModelSerializer implements JsonSerializer<JsonModel> {

    @Override
    public JsonElement serialize(JsonModel jsonModel, Type type, JsonSerializationContext jsonSerializationContext) {
//        return new Gson().toJsonTree(jsonModel);
        jsonModel.setB(222);
        return new Gson().toJsonTree(jsonModel);
    }
}
