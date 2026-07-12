package supportassistant.service;

import supportassistant.utils.PathUtils;

import java.io.File;

public class DatabaseService {
    public static String getDatabasePath() {
        return PathUtils.getFilePath("templates.db");
    }

    public static void saveLogPathProperty() {
        System.setProperty("LOG_PATH", new File(PathUtils.getUserHome(), PathUtils.getAppPath()).getAbsolutePath());
    }
}
