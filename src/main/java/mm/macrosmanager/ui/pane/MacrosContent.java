package mm.macrosmanager.ui.pane;

import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import mm.macrosmanager.service.ClipboardService;
import mm.macrosmanager.ui.button.Button;
import mm.macrosmanager.ui.macros.MacrosList;

public class MacrosContent extends VBox {
    private final TextArea macros = new TextArea();
    private final Text title = new Text();

    public MacrosContent(MacrosList macrosList, ClipboardService clipboard) {
        Button copyButton = new Button("Скопировать");

        copyButton.setOnAction(e -> clipboard.copy(macros.getText()));

        HBox titleContainer = new HBox(title);
        HBox macrosContainer = new HBox(macros);
        HBox buttonContainer = new HBox();

        macrosList.getMacrosList()
                .getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, old, val) -> {
                    if (val != null) {
                        title.setText(val.getTitle());
                        macros.setText(val.getMacros());
                    }
                });

        buttonContainer.getChildren().add(copyButton);

        setVgrow(macrosContainer, Priority.ALWAYS);

        macros.setEditable(false);
        macros.setWrapText(true);

        getChildren().addAll(titleContainer, macrosContainer, buttonContainer);
    }
}
