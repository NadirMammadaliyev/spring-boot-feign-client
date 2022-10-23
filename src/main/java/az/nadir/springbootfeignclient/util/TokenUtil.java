package az.nadir.springbootfeignclient.util;

import java.util.concurrent.atomic.AtomicReference;

public class TokenUtil {
    private static final AtomicReference<String> TOKEN = new AtomicReference<>();

    private TokenUtil() {
    }

    public static String set(String token) {
        TOKEN.set(token);
        return token;
    }

    public static String getToken() {
        return TOKEN.get();
    }
}
