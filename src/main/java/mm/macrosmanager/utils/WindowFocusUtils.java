package mm.macrosmanager.utils;

import java.awt.*;

public class WindowFocusUtils {
    public static void requestForeground() {
        Desktop desktop = Desktop.getDesktop();

        if (Desktop.isDesktopSupported()) {
            if (desktop.isSupported(Desktop.Action.APP_REQUEST_FOREGROUND)) {
                desktop.requestForeground(true);
            }
        }
    }
}
