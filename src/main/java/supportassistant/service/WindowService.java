package supportassistant.service;

import javafx.application.Platform;
import javafx.stage.Stage;
import supportassistant.utils.WindowFocusUtils;

public class WindowService {
    public static void open(Stage stage) {
        Platform.runLater(() -> {
            if (stage.isIconified()) {
                stage.setIconified(false);
            }

            if (!stage.isShowing()) {
                stage.show();
            }

            stage.toFront();
            stage.requestFocus();

            WindowFocusUtils.requestForeground();
        });
    }
}
