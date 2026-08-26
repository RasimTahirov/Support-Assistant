package supportassistant.utils;

public class SystemInfoUtils {
    private static final String OS_NAME =  System.getProperty("os.name").toLowerCase();
    private static final String USER_HOME = System.getProperty("user.home");

    private static final String WIN = "win";
    private static final String MAC = "mac";

    public static String getOsName() {
        return OS_NAME;
    }

    public static String getUserHome() {
        return USER_HOME;
    }

    public static String getWin() {
        return WIN;
    }

    public static String getMac() {
        return MAC;
    }
}
