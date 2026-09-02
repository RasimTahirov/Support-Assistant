package supportassistant.ui.window.alert;

import javafx.scene.control.Alert;
import supportassistant.utils.I18n;
import supportassistant.utils.LoadStyles;

public class WarningAlert extends Alert {
    public WarningAlert() {
        super(AlertType.WARNING);

        setTitle(I18n.get("message.warning.title"));
        setContentText(null);
        setHeaderText(null);
        setGraphic(null);

        LoadStyles.apply(getDialogPane());
    }
}
