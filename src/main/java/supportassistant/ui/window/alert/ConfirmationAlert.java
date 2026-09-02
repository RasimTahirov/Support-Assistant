package supportassistant.ui.window.alert;

import javafx.scene.control.Alert;
import supportassistant.utils.I18n;
import supportassistant.utils.LoadStyles;

public class ConfirmationAlert extends Alert {
    public ConfirmationAlert() {
        super(AlertType.CONFIRMATION);

        setTitle(I18n.get("message.confirmation.title"));
        setContentText(null);
        setHeaderText(null);
        setGraphic(null);

        LoadStyles.apply(getDialogPane());
    }
}
