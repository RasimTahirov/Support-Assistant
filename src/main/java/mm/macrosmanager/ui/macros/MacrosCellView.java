package mm.macrosmanager.ui.macros;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import mm.macrosmanager.model.Macros;
import mm.macrosmanager.storage.MacrosStorage;
import mm.macrosmanager.ui.button.Button;
import mm.macrosmanager.ui.window.ConfirmationWindow;

public class MacrosCellView extends ListCell<Macros> {
    private final MacrosStorage storage;

    public MacrosCellView(MacrosStorage storage) {
        this.storage = storage;
    }

    @Override
    protected void updateItem(Macros macros, boolean empty) {
        super.updateItem(macros, empty);

        if (macros == null || empty) {
            setGraphic(null);
            return;
        }

        Label macrosTitle = new Label(macros.getTitle());
        Button deleteMacros = new Button("Удалить");

        deleteMacros.setOnAction(e -> ConfirmationWindow.showDeleteMacrosConfirmation(
                "Удаление",
                "Удалить макрос",
                storage,
                macros
        ));

        HBox hBox = new HBox(50, macrosTitle, deleteMacros);
        setGraphic(hBox);
    }
}
