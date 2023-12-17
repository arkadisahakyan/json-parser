package arkadisahakyan.jsonparser.tokenizer;

/**
 * Created by Arkadi Sahakyan on 13.12.2023.
 */
public enum Token {
    OPEN_BRACE,         // {
    CLOSED_BRACE,       // }
    OPEN_BRACKET,       // [
    CLOSED_BRACKET,     // ]
    COLON,              // :
    COMMA,              // ,
    QUOTE,              // "
    MINUS,              // -
    POINT,              // .
    NEW_LINE,           // \n
    BACKSLASH,          // \
    WORD,
    NUMBER,
    WHITE_SPACE,
    UNDEFINED

    /*private final String value;

    Token(String value) {
        this.value = value;
    }*/
}
