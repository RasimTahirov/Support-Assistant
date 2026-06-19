package mm.macrosmanager.ui.macros;

import javafx.scene.control.ListView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import mm.macrosmanager.entity.Macros;
import mm.macrosmanager.model.MacrosModel;

public class MacrosList extends VBox {
    ListView<Macros> macrosList = new ListView<>();

    public MacrosList(MacrosModel model) {
        macrosList.setItems(model.getMacrosList());
        macrosList.setCellFactory(macros -> new MacrosCellView(model));

        VBox.setVgrow(macrosList, Priority.ALWAYS);
        getChildren().add(macrosList);
    }

    public ListView<Macros> getMacrosList() {
        return macrosList;
    }
}
