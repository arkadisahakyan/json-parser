package arkadisahakyan.jsonparser.tokenizer;

import arkadisahakyan.jsonparser.parser.AssignedToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arkadi Sahakyan on 13.12.2023.
 */
public class Tokenizer {

    public static AssignedToken[] tokenize(String text) {
        List<AssignedToken> result = new ArrayList<>();
        char[] chars = text.toCharArray();
        int ptr = 0;
        StringBuilder sb = new StringBuilder();
        while (ptr < chars.length) {
            if ((chars[ptr] >= 'a' && chars[ptr] <= 'z') || (chars[ptr] >= 'A' && chars[ptr] <= 'Z')) {
                do {
                    sb.append(chars[ptr++]);
                } while ((chars[ptr] >= 'a' && chars[ptr] <= 'z') || (chars[ptr] >= 'A' && chars[ptr] <= 'Z'));
                result.add(new AssignedToken(Token.WORD, sb.toString()));
                sb.setLength(0);
            } else if (chars[ptr] >= '0' && chars[ptr] <= '9') {
                do {
                    sb.append(chars[ptr++]);
                } while (chars[ptr] >= '0' && chars[ptr] <= '9');
                result.add(new AssignedToken(Token.NUMBER, sb.toString()));
                sb.setLength(0);
            } else if (chars[ptr] == '{') {
                result.add(new AssignedToken(Token.OPEN_BRACE, String.valueOf(chars[ptr++])));
            } else if (chars[ptr] == '}') {
                result.add(new AssignedToken(Token.CLOSED_BRACE, String.valueOf(chars[ptr++])));
            } else if (chars[ptr] == '[') {
                result.add(new AssignedToken(Token.OPEN_BRACKET, String.valueOf(chars[ptr++])));
            } else if (chars[ptr] == ']') {
                result.add(new AssignedToken(Token.CLOSED_BRACKET, String.valueOf(chars[ptr++])));
            } else if (chars[ptr] == ':') {
                result.add(new AssignedToken(Token.COLON, String.valueOf(chars[ptr++])));
            } else if (chars[ptr] == ',') {
                result.add(new AssignedToken(Token.COMMA, String.valueOf(chars[ptr++])));
            } else if (chars[ptr] == '"') {
                result.add(new AssignedToken(Token.QUOTE, String.valueOf(chars[ptr++])));
            } else if (chars[ptr] == '-') {
                result.add(new AssignedToken(Token.MINUS, String.valueOf(chars[ptr++])));
            } else if (chars[ptr] == '.') {
                result.add(new AssignedToken(Token.POINT, String.valueOf(chars[ptr++])));
            } else if (chars[ptr] == '\n') {
                result.add(new AssignedToken(Token.NEW_LINE, String.valueOf(chars[ptr++])));
            } else if (chars[ptr] == '\\') {
                result.add(new AssignedToken(Token.BACKSLASH, String.valueOf(chars[ptr++])));
            } else if (chars[ptr] == ' ') {
                result.add(new AssignedToken(Token.WHITE_SPACE, String.valueOf(chars[ptr++])));
            } else {
                result.add(new AssignedToken(Token.UNDEFINED, String.valueOf(chars[ptr++])));
            }
        }
        AssignedToken[] tokens = new AssignedToken[result.size()];
        result.toArray(tokens);
        return tokens;
    }
}
