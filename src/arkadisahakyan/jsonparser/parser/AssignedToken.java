package arkadisahakyan.jsonparser.parser;

import arkadisahakyan.jsonparser.tokenizer.Token;

/**
 * Created by Arkadi Sahakyan on 13.12.2023.
 */
public class AssignedToken {
    private final Token token;
    private final String value;

    public AssignedToken(Token token, String value) {
        this.token = token;
        this.value = value;
    }

    public Token getToken() {
        return token;
    }

    public String getValue() {
        return value;
    }
}
