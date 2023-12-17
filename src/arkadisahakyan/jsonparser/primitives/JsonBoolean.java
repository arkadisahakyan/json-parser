package arkadisahakyan.jsonparser.primitives;

/**
 * Created by Arkadi Sahakyan on 13.12.2023.
 */
public class JsonBoolean implements JsonPrimitive {
    private boolean bool;

    public JsonBoolean(boolean bool) {
        this.bool = bool;
    }

    public boolean getBool() {
        return bool;
    }

    public void setBool(boolean bool) {
        this.bool = bool;
    }

    @Override
    public String toString() {
        return String.valueOf(bool);
    }
}
