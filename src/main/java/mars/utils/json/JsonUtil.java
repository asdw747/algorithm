package mars.utils.json;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class JsonUtil {

    public static GsonBuilder getBuilder(Type type, Object typeAdapter) {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(type, typeAdapter);

        return builder;
    }

}
