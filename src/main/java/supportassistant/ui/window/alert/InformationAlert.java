package supportassistant.ui.window.alert;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import supportassistant.utils.I18n;
import supportassistant.utils.LoadStyles;

public class InformationAlert extends Alert {
    public InformationAlert() {
        super(AlertType.INFORMATION, null, ButtonType.OK);

        setTitle(I18n.get("message.info.title"));
        setContentText(null);
        setHeaderText(null);
        setGraphic(null);

        LoadStyles.apply(getDialogPane());
    }
}
