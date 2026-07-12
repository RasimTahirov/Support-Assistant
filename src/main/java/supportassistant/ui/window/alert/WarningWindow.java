package supportassistant.ui.window.alert;

import javafx.scene.control.Alert;
import supportassistant.utils.I18n;
import supportassistant.utils.LoadStyles;

import static javafx.scene.control.Alert.AlertType.WARNING;

public class WarningWindow {
    private final static String warningMessage = I18n.get("title.warning");

    public static void showWarning(String message) {
        Alert warning = new Alert(WARNING);

        warning.setTitle(warningMessage);
        warning.setHeaderText(null);
        warning.setContentText(message);

        LoadStyles.apply(warning.getDialogPane());

        warning.showAndWait();
    }
}
