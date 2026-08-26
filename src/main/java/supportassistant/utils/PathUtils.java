package supportassistant.utils;

import java.io.File;

import static supportassistant.utils.SystemInfoUtils.*;

public class PathUtils {
    private static final String WINDOWS_PATH = "AppData/Roaming/Support Assistant";
    private static final String MAC_OS_PATH = "Library/Application Support/Support Assistant";
    private static final String DEFAULT_PATH = ".supportassistant";

    public static String getAppPath() {
        if (getOsName().contains(getWin())) {
            return WINDOWS_PATH;
        }

        if (getOsName().contains(getMac())) {
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
