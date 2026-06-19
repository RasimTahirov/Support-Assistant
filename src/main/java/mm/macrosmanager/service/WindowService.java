package mm.macrosmanager.service;

import javafx.application.Platform;
import javafx.stage.Stage;
import mm.macrosmanager.utils.WindowFocusUtils;

public class WindowService {
    public static void open(Stage stage) {
        Platform.runLater(() -> {
            stage.show();
            stage.toFront();

            WindowFocusUtils.requestForeground();
        });
    }
}
