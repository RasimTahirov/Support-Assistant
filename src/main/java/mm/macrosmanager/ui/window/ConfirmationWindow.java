package mm.macrosmanager.ui.window;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import mm.macrosmanager.model.Macros;
import mm.macrosmanager.storage.MacrosStorage;

import java.sql.SQLException;
import java.util.Optional;

import static javafx.scene.control.Alert.AlertType.CONFIRMATION;

public class ConfirmationWindow {
    public static void showDeleteMacrosConfirmation(
            String title,
            String message,
            MacrosStorage storage,
            Macros macros) {
        Alert alert = new Alert(CONFIRMATION);

        alert.setTitle(title);
        alert.setContentText(message + " " + macros.getTitle() + "?");
        alert.setHeaderText(null);
        alert.setGraphic(null);

        Optional<ButtonType> result = alert.showAndWait();
        System.out.println(result);

        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                storage.deleteMacros(macros);
            } catch (SQLException exception) {
                System.out.println("SQL ошибка " + exception.getMessage());
                WarningWindow.showWarning("Не удалось удалить макрос");
            }
        }
    }
}
