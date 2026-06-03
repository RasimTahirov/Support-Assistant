package mm.macrosmanager.service;

import java.io.File;

public class DatabaseService {
    private static final String USER_HOME = System.getProperty("user.home");

    public static String getDatabasePath(String path) {
        File folder = new File(USER_HOME, path);

        if (!folder.exists()) {
            folder.mkdirs();
        }

        return new File(folder, "macros.db").getAbsolutePath();
    }
}
