package arkadisahakyan.jsonparser.primitives;

/**
 * Created by Arkadi Sahakyan on 13.12.2023.
 */
public class JsonString implements JsonPrimitive {
    private String string;

    public JsonString(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    @Override
    public String toString() {
        return string;
    }
}
