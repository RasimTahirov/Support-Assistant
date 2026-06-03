package mm.macrosmanager.ui.macros;

import javafx.scene.control.ListView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import mm.macrosmanager.model.Macros;
import mm.macrosmanager.storage.MacrosStorage;
import mm.macrosmanager.ui.window.DeleteMacrosWindow;

public class MacrosList extends VBox {
    ListView<Macros> macrosList = new ListView<>();

    public MacrosList(MacrosStorage storage, DeleteMacrosWindow deleteMacrosWindow) {
        macrosList.setItems(storage.getMacros());
        macrosList.setCellFactory(macros -> new MacrosCellView(storage, deleteMacrosWindow));

        VBox.setVgrow(macrosList, Priority.ALWAYS);
        getChildren().add(macrosList);
    }

    public ListView<Macros> getMacrosList() {
        return macrosList;
    }
}
