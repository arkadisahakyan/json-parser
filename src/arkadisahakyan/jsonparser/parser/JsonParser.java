package arkadisahakyan.jsonparser.parser;

import arkadisahakyan.jsonparser.primitives.*;
import arkadisahakyan.jsonparser.tokenizer.Token;
import arkadisahakyan.jsonparser.tokenizer.Tokenizer;
import arkadisahakyan.jsonparser.exceptions.JsonSyntaxException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arkadi Sahakyan on 13.12.2023.
 */
public class JsonParser {
    private final AssignedToken[] tokens;
    private int ptr;

    protected JsonParser(AssignedToken[] tokens) {
        this.tokens = ignoreWhiteSpaces(tokens);
        ptr = 0;
    }

    protected JsonPrimitive parse() throws JsonSyntaxException {
        JsonPrimitive result;
        Token currentToken = getTokenOnPtr().getToken();
        switch (currentToken) {
            case OPEN_BRACE:
                ptr++;
                result = parseJsonObject();
                break;
            case OPEN_BRACKET:
                ptr++;
                result = parseJsonObjectArray();
                break;
            default:
                throw new JsonSyntaxException("Unexpected Token: " + getTokenOnPtr().getToken());
        }
        return result;
    }

    private JsonObject parseJsonObject() throws JsonSyntaxException {
        JsonObject result = new JsonObject();
        while (ptr < tokens.length && !getTokenOnPtr().getToken().equals(Token.CLOSED_BRACE)) {
            expect(Token.QUOTE);
            expect(Token.WORD);
            String itemName = getTokenOnPtr(ptr - 1).getValue();
            expect(Token.QUOTE);
            expect(Token.COLON);
            JsonItem item = new JsonItem(itemName, null);
            switch (getTokenOnPtr().getToken()) {
                case OPEN_BRACKET:
                    ptr++;
                    item.setValue(parseJsonObjectArray());
                    break;
                case OPEN_BRACE:
                    ptr++;
                    item.setValue(parseJsonObject());
                    break;
                case QUOTE:
                    ptr++;
                    item.setValue(parseJsonString());
                    break;
                case WORD:
                    item.setValue(parseJsonBoolean());
                    break;
                case MINUS:
                case NUMBER:
                    item.setValue(parseJsonNumber());
                    break;
                default:
                    throw new JsonSyntaxException("Assigned Illegal Value");
            }
            result.getItems().add(item);
            if (getTokenOnPtr(ptr + 1).getToken().equals(Token.QUOTE))
                expect(Token.COMMA);
        }
        expect(Token.CLOSED_BRACE);
        return result;
    }

    private JsonObjectArray parseJsonObjectArray() throws JsonSyntaxException {
        JsonObjectArray result = new JsonObjectArray();
        while (ptr < tokens.length && !getTokenOnPtr().getToken().equals(Token.CLOSED_BRACKET)) {
            expect(Token.OPEN_BRACE);
            result.getObjects().add(parseJsonObject());
            if (getTokenOnPtr(ptr + 1).getToken().equals(Token.OPEN_BRACE))
                expect(Token.COMMA);
        }
        expect(Token.CLOSED_BRACKET);
        return result;
    }

    private JsonPrimitive parseJsonString() throws JsonSyntaxException {
        StringBuilder sb = new StringBuilder();
        while (!getTokenOnPtr().getToken().equals(Token.QUOTE)) {
            if (getTokenOnPtr().getToken().equals(Token.BACKSLASH)) {
                ptr++;
                if (getTokenOnPtr().getToken().equals(Token.QUOTE))
                    sb.append(getTokenOnPtr().getValue());
                else if (getTokenOnPtr().getToken().equals(Token.BACKSLASH))
                    sb.append(getTokenOnPtr().getValue());
                else if (getTokenOnPtr().getToken().equals(Token.WORD)) {
                    String word = getTokenOnPtr().getValue();
                    if (word.charAt(0) == 'n')
                        sb.append('\n');
                    sb.append(word.substring(1)); // ignore character after BACKSLASH
                }
            } else
                sb.append(getTokenOnPtr().getValue());
            ptr++;
        }
        expect(Token.QUOTE);
        return new JsonString(sb.toString());
    }

    private JsonPrimitive parseJsonBoolean() throws JsonSyntaxException {
        if (getTokenOnPtr().getValue().equals("true")) {
            ptr++;
            return new JsonBoolean(true);
        } else if (getTokenOnPtr().getValue().equals("false")) {
            ptr++;
            return new JsonBoolean(false);
        } else
            throw new JsonSyntaxException("Assigned Illegal Value");
    }

    private JsonPrimitive parseJsonNumber() throws JsonSyntaxException {
        boolean isNegative = false;
        if (getTokenOnPtr().getToken().equals(Token.MINUS)) {
            ptr++;
            isNegative = true;
        }
        expect(Token.NUMBER);
        double number = Double.valueOf(tokens[ptr - 1].getValue());
        if (getTokenOnPtr().getToken().equals(Token.POINT)) {
            ptr++;
            expect(Token.NUMBER);
            number = Double.valueOf(getTokenOnPtr(ptr - 3).getValue() + getTokenOnPtr(ptr - 2).getValue() + getTokenOnPtr(ptr - 1).getValue());
        }
        if (isNegative)
            number *= -1;
        return new JsonNumber(number);
    }

    private void expect(Token token) throws JsonSyntaxException {
        if (ptr >= tokens.length)
            throw new JsonSyntaxException("Missing Token: " + token);
        if (!getTokenOnPtr().getToken().equals(token))
            throw new JsonSyntaxException("Unexpected Token: " + getTokenOnPtr().getToken());
        ptr++;
    }

    private AssignedToken getTokenOnPtr() {
        if (ptr < tokens.length)
            return tokens[ptr];
        else
            return new AssignedToken(Token.UNDEFINED, null);
    }

    private AssignedToken getTokenOnPtr(int ptr) {
        if (ptr < tokens.length)
            return tokens[ptr];
        else
            return new AssignedToken(Token.UNDEFINED, null);
    }

    private static AssignedToken[] ignoreWhiteSpaces(AssignedToken[] tokens) {
        List<AssignedToken> result = new ArrayList<>();
        boolean openQuote = false;
        for (int i = 0; i < tokens.length; i++) {
            Token current = tokens[i].getToken();
            if (current.equals(Token.BACKSLASH) && (tokens[i + 1].getToken().equals(Token.QUOTE) || tokens[i + 1].getToken().equals(Token.BACKSLASH))) {
                result.add(tokens[i]);
                i++;
                result.add(tokens[i]);
                continue;
            }
            if (current.equals(Token.QUOTE)) {
                openQuote = !openQuote;
                result.add(tokens[i]);
                continue;
            }
            if ((current.equals(Token.WHITE_SPACE) || current.equals(Token.NEW_LINE)) && !openQuote) {
                continue;
            }
            result.add(tokens[i]);
        }
        AssignedToken[] newTokens = new AssignedToken[result.size()];
        result.toArray(newTokens);
        //for (AssignedToken t : newTokens)
        //    System.out.print(t.getValue());
        //System.out.println();
        return newTokens;
    }

    public static JsonPrimitive parseJson(String textJson) throws JsonSyntaxException {
        return new JsonParser(Tokenizer.tokenize(textJson)).parse();
    }
}
