# JSON Parser
This program does not fully support the JSON specification.

## Specification & Features
- Key-value pair names must include only the characters **a-z**, **A-Z**
- Key-value pair values ​​can include strings (e.g. "Hello World"), numbers (e.g. -123.045), booleans (e.g. false), JSON objects {} or arrays []
- Arrays [] can only contain JSON objects {}
- JSON objects {} can only contain key-value pairs
- If arrays [] or JSON objects {} contain multiple elements, they must be separated by commas; the last element should not be separated by a comma
- Any white spaces and newlines are ignored unless they are within quotes
- To use quotes in strings you can use \\", newline is \\n, backslash is \\\\

## Usage
```java
JsonPrimitive json = JsonParser.parseJson("{\"text\": \"Hello\"}");
System.out.println(json);
```
`JsonPrimitive` is an interface to the basic JSON data types, such as `JsonString`, `JsonNumber`. The `JsonParser.parseJson(...)` function returns either a JsonObject or a JsonObjectArray. To determine which class was returned, you can use the following:
```java
if (json instanceof JsonObject) {
  JsonObject obj = (JsonObject) json;
}
```

## Compile & Run
First, create a folder called *out* in the project root, then run the following two commands in CMD:
```
javac -d out -sourcepath src src\arkadisahakyan\jsonparser\Main.java
java -cp out arkadisahakyan.jsonparser.Main
```
Try changing the sample.json file and rebuilding the project.
