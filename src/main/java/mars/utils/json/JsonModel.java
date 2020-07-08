package mars.utils.json;

import com.google.gson.JsonArray;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.json.JSONArray;

import java.util.List;

@Data
@AllArgsConstructor
public class JsonModel {
    private int a;
    private int b;
    private List<Temp> c;
    private String d;

    public JsonModel() {

    }
}
