package arkadisahakyan.jsonparser.primitives;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arkadi Sahakyan on 13.12.2023.
 */
public class JsonObject implements JsonPrimitive {
    private List<JsonItem> items;

    public JsonObject() {
        this.items = new ArrayList<>();
    }

    public List<JsonItem> getItems() {
        return items;
    }

    public void setItems(List<JsonItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{ ");
        if (!items.isEmpty()) {
            for (JsonItem item : items)
                sb.append(item.toString() + ", ");
            sb.setLength(sb.length() - 2); // delete last ", "
        }
        sb.append(" }");
        return sb.toString();
    }
}
