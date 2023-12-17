package arkadisahakyan.jsonparser.primitives;

/**
 * Created by Arkadi Sahakyan on 13.12.2023.
 */
public class JsonNumber implements JsonPrimitive {
    private double number;

    public JsonNumber(double number) {
        this.number = number;
    }

    public double getNumber() {
        return number;
    }

    public void setNumber(double number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }
}
