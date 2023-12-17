package arkadisahakyan.jsonparser.primitives;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arkadi Sahakyan on 13.12.2023.
 */
public class JsonObjectArray implements JsonPrimitive {
    private List<JsonObject> objects;

    public JsonObjectArray() {
        this.objects = new ArrayList<>();
    }

    public List<JsonObject> getObjects() {
        return objects;
    }

    public void setObjects(List<JsonObject> objects) {
        this.objects = objects;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        if (!objects.isEmpty()) {
            for (JsonObject object : objects)
                sb.append(object.toString() + ", ");
            sb.setLength(sb.length() - 2); // delete last ", "
        }
        sb.append(" ]");
        return sb.toString();
    }
}
