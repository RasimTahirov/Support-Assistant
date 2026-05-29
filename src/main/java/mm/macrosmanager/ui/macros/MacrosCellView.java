package mm.macrosmanager.ui.macros;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import mm.macrosmanager.model.Macros;
import mm.macrosmanager.storage.MacrosStorage;

public class MacrosCellView extends ListCell<Macros> {
    public MacrosCellView(MacrosStorage storage) {}

    @Override
    protected void updateItem(Macros macros, boolean empty) {
        super.updateItem(macros, empty);

        if (macros == null || empty) {
            setGraphic(null);
            return;
        }

        Label macrosTitle = new Label(macros.getTitle());

        HBox hBox = new HBox(50, macrosTitle);
        setGraphic(hBox);
    }
}
