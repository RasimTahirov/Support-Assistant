package mm.macrosmanager.service;

import java.io.File;

public class PlatformService {
    private static final String GET_PROPERTY =  System.getProperty("os.name").toLowerCase();
    private static final String USER_HOME = System.getProperty("user.home");

    private static final String WINDOWS_PATH = "AppData/Roaming/Macros Manager";
    private static final String MAC_OS_PATH = "/Library/Application Support/Macros Manager";
    private static final String DEFAULT_PATH = ".macrosmanager";

    private static final String WIN = "win";
    private static final String MAC = "mac";

    private static String getAppPath() {
        if (GET_PROPERTY.contains(WIN)) {
            return WINDOWS_PATH;
        }

        if (GET_PROPERTY.contains(MAC)) {
            return MAC_OS_PATH;
        }

        return DEFAULT_PATH;
    }

    public static void saveLogPathProperty() {
        System.setProperty("LOG_PATH", new File(USER_HOME, getAppPath()).getAbsolutePath());
        System.out.println(new File(USER_HOME, getAppPath()).getAbsolutePath());
    }

    public static String getDatabasePath() {
        return DatabaseService.getDatabasePath(getAppPath());
    }

    public static String getOsName() {
        return GET_PROPERTY;
    }

    public static String getUserHome() {
        return USER_HOME;
    }
}
