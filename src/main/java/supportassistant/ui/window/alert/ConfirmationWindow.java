package supportassistant.ui.window.alert;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import supportassistant.entity.Template;
import supportassistant.exception.TemplateException;
import supportassistant.model.TemplateModel;
import supportassistant.utils.I18n;
import supportassistant.utils.LoadStyles;

import java.util.Optional;

import static javafx.scene.control.Alert.AlertType.CONFIRMATION;

public class ConfirmationWindow {
    public static void showDeleteTemplateConfirmation(
            String title,
            String message,
            Template template,
            TemplateModel model) {
        Alert alert = new Alert(CONFIRMATION);

        alert.setTitle(title);
        alert.setContentText(message + " " + template.getTitle() + "?");
        alert.setHeaderText(null);
        alert.setGraphic(null);

        LoadStyles.apply(alert.getDialogPane());

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                model.delete(template);
            } catch (TemplateException exception) {
                WarningWindow.showWarning(I18n.get("message.warning.delete"));
            }
        }
    }
}
