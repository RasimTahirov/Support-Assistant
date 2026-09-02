package supportassistant.ui.window.alert;

import javafx.scene.control.Alert;
import supportassistant.utils.I18n;
import supportassistant.utils.LoadStyles;

public class ErrorAlert extends Alert {
    public ErrorAlert() {
        super(AlertType.ERROR);

        setTitle(I18n.get("message.error.title"));
        setContentText(null);
        setHeaderText(null);
        setGraphic(null);

        LoadStyles.apply(getDialogPane());
    }
}
