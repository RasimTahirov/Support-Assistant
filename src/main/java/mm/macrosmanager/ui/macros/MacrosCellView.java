package mm.macrosmanager.ui.macros;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import mm.macrosmanager.entity.Macros;
import mm.macrosmanager.model.MacrosModel;
import mm.macrosmanager.ui.button.Button;
import mm.macrosmanager.ui.window.ConfirmationWindow;

public class MacrosCellView extends ListCell<Macros> {
    private final MacrosModel model;

    public MacrosCellView(MacrosModel model) {
        this.model = model;
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
                macros,
                model
        ));

        HBox hBox = new HBox(50, macrosTitle, deleteMacros);
        setGraphic(hBox);
    }
}
