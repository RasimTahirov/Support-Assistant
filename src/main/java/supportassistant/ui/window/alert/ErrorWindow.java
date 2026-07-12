package supportassistant.ui.window.alert;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import supportassistant.utils.I18n;
import supportassistant.utils.LoadStyles;

import static javafx.scene.control.Alert.AlertType.ERROR;

public class ErrorWindow {
    public static void showInitDatabaseError() {
        Alert alert = new Alert(ERROR);

        alert.setHeaderText(I18n.get("message.error.database.header"));
        alert.setContentText(I18n.get("message.error.database.content"));

        LoadStyles.apply(alert.getDialogPane());

        alert.showAndWait();
        Platform.exit();
        System.exit(0);
    }

    public static void showUnregisterError() {
        Alert alert = new Alert(ERROR);

        alert.setHeaderText(I18n.get("message.error.unregister.header"));
        alert.setContentText(I18n.get("message.error.unregister.content"));

        LoadStyles.apply(alert.getDialogPane());

        alert.showAndWait();
    }
}
