package supportassistant.utils;

import java.util.Locale;
import java.util.ResourceBundle;

public class I18n {
    private final static String BASE_NAME = "language";

    private static ResourceBundle resourceBundle =
            ResourceBundle.getBundle(BASE_NAME, Locale.getDefault());

    public static String get(String key) {
        return resourceBundle.getString(key);
    }

    public static void setLocale(Locale locale) {
        resourceBundle = ResourceBundle.getBundle(BASE_NAME, locale);
    }
}
