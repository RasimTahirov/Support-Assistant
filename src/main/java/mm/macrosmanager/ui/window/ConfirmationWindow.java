package mm.macrosmanager.ui.window;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import mm.macrosmanager.entity.Macros;
import mm.macrosmanager.exception.MacrosException;
import mm.macrosmanager.model.MacrosModel;
import java.util.Optional;

import static javafx.scene.control.Alert.AlertType.CONFIRMATION;

public class ConfirmationWindow {
    public static void showDeleteMacrosConfirmation(
            String title,
            String message,
            Macros macros,
            MacrosModel model) {
        Alert alert = new Alert(CONFIRMATION);

        alert.setTitle(title);
        alert.setContentText(message + " " + macros.getTitle() + "?");
        alert.setHeaderText(null);
        alert.setGraphic(null);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                model.delete(macros);
            } catch (MacrosException exception) {
                WarningWindow.showWarning("Не удалось удалить макрос");
            }
        }
    }
}
