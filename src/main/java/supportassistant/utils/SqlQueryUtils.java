package supportassistant.utils;

import supportassistant.service.PlatformService;

public class SqlQueryUtils {
    private static final String URL = "jdbc:sqlite:";

    private static final String SEARCH_QUERY = "SELECT * FROM %s WHERE %s LIKE ?";
    private static final String ALL_QUERY = "SELECT * FROM %s";
    private static final String ADD_QUERY = "INSERT INTO %s (%s, %s, %s) VALUES (?, ?, ?)";
    private static final String DELETE_QUERY = "DELETE FROM %s WHERE %s = ?";
    private static final String CREATE_QUERY = """
            CREATE TABLE IF NOT EXISTS %s (
                %s INTEGER PRIMARY KEY AUTOINCREMENT,
                %s TEXT NOT NULL,
                %s TEXT NOT NULL,
                %s TEXT NOT NULL
            );
            """;

    private static final String DATABASE_NAME = "template";

    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_TITLE_LOWER = "title_lower";
    private static final String KEY_TEMPLATE = "template";

    public static String getURL() {
        return URL + PlatformService.getDatabasePath();
    }

    public static String getSearchQuery() {
        return String.format(SEARCH_QUERY, DATABASE_NAME, KEY_TITLE_LOWER);
    }

    public static String getAllQuery() {
        return String.format(ALL_QUERY, DATABASE_NAME);
    }

    public static String getAddQuery() {
        return String.format(ADD_QUERY, DATABASE_NAME, KEY_TITLE, KEY_TITLE_LOWER, KEY_TEMPLATE);
    }

    public static String getDeleteQuery() {
        return String.format(DELETE_QUERY, DATABASE_NAME, KEY_ID);
    }

    public static String getCreateQuery() {
        return String.format(CREATE_QUERY, DATABASE_NAME, KEY_ID, KEY_TITLE, KEY_TITLE_LOWER, KEY_TEMPLATE);
    }

    public static String getKeyId() {
        return KEY_ID;
    }

    public static String getKeyTitle() {
        return KEY_TITLE;
    }

    public static String getKeyTemplate() {
        return KEY_TEMPLATE;
    }
}
