package mm.macrosmanager.service;

import javafx.application.Platform;
import javafx.stage.Stage;

public class WindowService {
    public static void open(Stage stage) {
        Platform.runLater(() -> {
            stage.show();
            stage.toFront();
        });
    }
}
