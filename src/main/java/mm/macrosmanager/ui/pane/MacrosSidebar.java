package mm.macrosmanager.ui.pane;

import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import mm.macrosmanager.storage.MacrosStorage;
import mm.macrosmanager.ui.button.Button;
import mm.macrosmanager.ui.macros.MacrosList;
import mm.macrosmanager.ui.window.AddMacrosWindow;

public class MacrosSidebar extends VBox {
    public MacrosSidebar(MacrosList macrosList, AddMacrosWindow addMacrosWindow, MacrosStorage storage) {
        TextField search = new TextField();

        search.setPromptText("Поиск...");
        search.textProperty().addListener((obs, oldVal, newVal) -> {
            storage.searchMacros(newVal.toLowerCase());
        });

        Button addMacrosButton = new Button("Добавить новый макрос");

        addMacrosButton.setOnAction(e -> addMacrosWindow.open(storage));

        HBox buttonContainer = new HBox(addMacrosButton);
        VBox macrosListContainer = new VBox(macrosList);

        macrosListContainer.setMaxWidth(Double.MAX_VALUE);

        VBox.setVgrow(macrosList, Priority.ALWAYS);
        VBox.setVgrow(macrosListContainer, Priority.ALWAYS);

        getChildren().addAll(search, buttonContainer, macrosListContainer);
    }
}
