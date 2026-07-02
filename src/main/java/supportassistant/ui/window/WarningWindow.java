package supportassistant.ui.window;

import javafx.scene.control.Alert;
import supportassistant.utils.LoadStyles;

import static javafx.scene.control.Alert.AlertType.WARNING;

public class WarningWindow {
    private final static String warningMessage = "Предупреждение";

    public static void showWarning(String message) {
        Alert warning = new Alert(WARNING);

        warning.setTitle(warningMessage);
        warning.setHeaderText(null);
        warning.setContentText(message);

        LoadStyles.apply(warning.getDialogPane());

        warning.showAndWait();
    }
}
