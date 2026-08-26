package supportassistant.service;

import javafx.application.Platform;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import supportassistant.utils.SystemInfoUtils;

import java.awt.*;
import java.awt.event.KeyEvent;

public class WindowService {
    private static final Logger logger = LoggerFactory.getLogger(WindowService.class);
    private static final Robot robot = createRobot();

    private static Robot createRobot() {
        try {
            return new Robot();
        } catch (AWTException e) {
            logger.warn("Unable to create Robot", e);
            return null;
        }
    }

    public static void open(Stage stage) {
        Platform.runLater(() -> {
            if (SystemInfoUtils.getOsName().contains(SystemInfoUtils.getWin()) && robot != null) {
                robot.keyPress(KeyEvent.VK_ALT);
                robot.keyRelease(KeyEvent.VK_ALT);
                robot.delay(50);
            }

            if (stage.isIconified()) {
                stage.setIconified(false);
            }

            if (!stage.isShowing()) {
                stage.show();
            }

            stage.setAlwaysOnTop(true);
            stage.toFront();
            stage.requestFocus();

            Platform.runLater(() -> stage.setAlwaysOnTop(false));
        });
    }
}
