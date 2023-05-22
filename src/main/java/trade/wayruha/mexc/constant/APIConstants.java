package trade.wayruha.mexc.constant;

import okhttp3.MediaType;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

public class APIConstants {
    /**
     * All requests and responses are application/json content type and follow typical HTTP response status codes for success and failure.
     */
    public static final String CONTENT_TYPE = "Content-Type";

    public static final String ACCEPT = "Accept";

    public static final String COOKIE = "Cookie";

    public static final String LOCALE = "locale=";

    public static final String JSON_CONTENT_TYPE = "application/json";
    public static final String UTF_8_JSON_CONTENT_TYPE = "application/json; charset=UTF-8";
    public static final MediaType JSON = MediaType.parse(JSON_CONTENT_TYPE);
    public static final String LOCALE_EN = "en_US";

    public static final Charset UTF_8 = StandardCharsets.UTF_8;

    public static final String QUESTION = "?";

    public static final String EMPTY = "";


    public static final List<String> toStringTypeArray = Arrays.asList(
            "class java.lang.Long",
            "class java.lang.Integer",
            "long",
            "int");
    public static final List<String> toStringTypeDoubleArray = Arrays.asList(
            "class java.lang.Double",
            "double");

    public static final List<Integer> resultStatusArray = Arrays.asList(
            400, 401, 429, 500);

    public static final String BOOLEAN = "boolean";
    public static final String IS = "is";
    public static final String get = "get";
    public static final char a = 'a';
    public static final char z = 'z';
    public static final String ZERO_STRING = "0";
    public static final String DOUBLE_ZERO_STRING = "0.00";

    public static final String DOT1 = ".";
    public static final String DOT2 = "\\.";
    public static final String E = "E";
    public static final String e = "e";
    public static final int DEFAULT_SCALE = 2;
    /**
     * 8900000000.000000000
     */
    public static final String DOUBLE_END1 = "0+?$";
    /**
     * 8900000000.
     */
    public static final String DOUBLE_END2 = "[.]$";

    /**
     * default cursor id
     */
    public static final int ONE = 1;
    /**
     * max limit: 100
     */
    public static final int HUNDRED = 100;

    public static final String HLINE = "-";

    public static final String SLASH = "/";
}
