package arkadisahakyan.jsonparser;

import arkadisahakyan.jsonparser.exceptions.JsonSyntaxException;
import arkadisahakyan.jsonparser.parser.JsonParser;
import arkadisahakyan.jsonparser.primitives.JsonPrimitive;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws JsonSyntaxException, IOException {
        String s = String.join("\n", Files.readAllLines(Paths.get("sample.json")));
        JsonPrimitive json = JsonParser.parseJson(s);
        System.out.println(json);
    }
}
