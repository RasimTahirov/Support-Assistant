package supportassistant.utils;

import java.awt.*;

public class WindowFocusUtils {
    public static void requestForeground() {
        if (!Desktop.isDesktopSupported()) {
            return;
        }

        Desktop desktop = Desktop.getDesktop();

        if (Desktop.isDesktopSupported()) {
            if (desktop.isSupported(Desktop.Action.APP_REQUEST_FOREGROUND)) {
                desktop.requestForeground(true);
            }
        }
    }
}
