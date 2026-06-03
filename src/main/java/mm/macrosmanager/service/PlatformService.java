package mm.macrosmanager.service;

public class PlatformService {
    public static String os() {
        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("win")) {
            System.out.println(os);
            System.out.println("Здесь пока пусто, так как у меня нет винды");
        }

        if (os.contains("mac")) {
            return DatabaseService.getDatabasePath("/Library/Application Support/Macros Manager");
        }

        return DatabaseService.getDatabasePath(".macrosmanager");
    }
}
