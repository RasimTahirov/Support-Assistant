package supportassistant.utils;

import java.io.File;

public class PathUtils {
    private static final String OS_NAME =  System.getProperty("os.name").toLowerCase();
    private static final String USER_HOME = System.getProperty("user.home");

    private static final String WINDOWS_PATH = "AppData/Roaming/Support Assistant";
    private static final String MAC_OS_PATH = "Library/Application Support/Support Assistant";
    private static final String DEFAULT_PATH = ".supportassistant";

    private static final String WIN = "win";
    private static final String MAC = "mac";

    public static String getOsName() {
        return OS_NAME;
    }

    public static String getUserHome() {
        return USER_HOME;
    }

    public static String getAppPath() {
        if (OS_NAME.contains(WIN)) {
            return WINDOWS_PATH;
        }

        if (OS_NAME.contains(MAC)) {
            return MAC_OS_PATH;
        }

        return DEFAULT_PATH;
    }

    public static String getFilePath(String fileName) {
        File folder = new File(getUserHome(), getAppPath());

        if (!folder.exists() && !folder.mkdirs()) {
            throw new IllegalStateException("Failed to create root directory");
        }

        return new File(folder, fileName).getAbsolutePath();
    }
}
