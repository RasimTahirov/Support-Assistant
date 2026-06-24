package mm.macrosmanager.service;

import java.io.File;

public class DatabaseService {
    public static String getDatabasePath(String path) {
        File folder = new File(PlatformService.getUserHome(), path);

        if (!folder.exists()) {
            folder.mkdirs();
        }

        return new File(folder, "macros.db").getAbsolutePath();
    }
}
