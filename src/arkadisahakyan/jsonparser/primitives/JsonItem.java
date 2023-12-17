package arkadisahakyan.jsonparser.primitives;

/**
 * Created by Arkadi Sahakyan on 13.12.2023.
 */
public class JsonItem implements JsonPrimitive {
    private String key;
    private JsonPrimitive value;

    public JsonItem(String key, JsonPrimitive value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public JsonPrimitive getValue() {
        return value;
    }

    public void setValue(JsonPrimitive value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return key + ": " + value;
    }
}
