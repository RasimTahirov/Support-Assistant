package mm.macrosmanager.service;

public class PlatformService {
    public static String getDatabasePath() {
        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("win")) {
            return DatabaseService.getDatabasePath("AppData/Roaming/Macros Manager");
        }

        if (os.contains("mac")) {
            return DatabaseService.getDatabasePath("/Library/Application Support/Macros Manager");
        }

        return DatabaseService.getDatabasePath(".macrosmanager");
    }
}
