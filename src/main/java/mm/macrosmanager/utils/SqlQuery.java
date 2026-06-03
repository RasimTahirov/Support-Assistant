package mm.macrosmanager.utils;

import mm.macrosmanager.service.PlatformService;

public class SqlQuery {
    private static String URL = "jdbc:sqlite:";

    private static final String ALL_QUERY = "SELECT * FROM %s";
    private static final String ADD_QUERY = "INSERT INTO %s (%s, %s) VALUES (?, ?)";
    private static final String DELETE_QUERY = "DELETE FROM %s WHERE %s = ?";
    private static final String CREATE_QUERY = """
            CREATE TABLE IF NOT EXISTS %s (
                %s INTEGER PRIMARY KEY AUTOINCREMENT,
                %s TEXT NOT NULL,
                %s TEXT NOT NULL
            );
            """;

    private static final String DATABASE_NAME = "macros";

    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_MACROS = "macros";

    public static String getURL() {
        return URL + PlatformService.os();
    }

    public static String getAllQuery() {
        return String.format(ALL_QUERY, DATABASE_NAME);
    }

    public static String getAddQuery() {
        return String.format(ADD_QUERY, DATABASE_NAME, KEY_TITLE, KEY_MACROS);
    }

    public static String getDeleteQuery() {
        return String.format(DELETE_QUERY, DATABASE_NAME, KEY_ID);
    }

    public static String getCreateQuery() {
        return String.format(CREATE_QUERY, DATABASE_NAME, KEY_ID, KEY_TITLE, KEY_MACROS);
    }

    public static String getKeyId() {
        return KEY_ID;
    }

    public static String getKeyTitle() {
        return KEY_TITLE;
    }

    public static String getKeyMacros() {
        return KEY_MACROS;
    }

}
