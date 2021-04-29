package team16.sidedish.utils;

import javax.servlet.http.HttpSession;

public class HttpSessionUtils {
    private static final String ACCESS_TOKEN = "access-token";

    public static String getAccessToken(HttpSession session) {
        return (String) session.getAttribute(ACCESS_TOKEN);
    }

    public static void setAccessToken(HttpSession session, String value) {
        session.setAttribute(ACCESS_TOKEN, value);
    }

    public static void removeAccessToken(HttpSession session) {
        session.removeAttribute(ACCESS_TOKEN);
    }
}
