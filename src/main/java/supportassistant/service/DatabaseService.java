package supportassistant.service;

import supportassistant.utils.PathUtils;
import supportassistant.utils.SystemInfoUtils;

import java.io.File;

public class DatabaseService {
    public static String getDatabasePath() {
        return PathUtils.getFilePath("templates.db");
    }

    public static void saveLogPathProperty() {
        System.setProperty("LOG_PATH", new File(SystemInfoUtils.getUserHome(), PathUtils.getAppPath()).getAbsolutePath());
    }
}
