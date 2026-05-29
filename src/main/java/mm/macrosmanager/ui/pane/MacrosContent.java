package mm.macrosmanager.ui.pane;

import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import mm.macrosmanager.service.ClipboardService;
import mm.macrosmanager.ui.button.Button;

public class MacrosContent extends VBox {
    private final TextArea macros = new TextArea();
    private final Text title = new Text();

    public MacrosContent(ClipboardService clipboard) {
        Button copyButton = new Button("Скопировать");

        copyButton.setOnAction(e -> clipboard.copy(macros.getText()));

        HBox titleContainer = new HBox(title);
        HBox macrosContainer = new HBox(macros);
        HBox buttonContainer = new HBox();

        buttonContainer.getChildren().add(copyButton);

        setVgrow(macrosContainer, Priority.ALWAYS);

        macros.setEditable(false);
        macros.setWrapText(true);

        getChildren().addAll(titleContainer, macrosContainer, buttonContainer);
    }

    public void showMacros(String stringTitle, String string) {
        title.setText(stringTitle);
        macros.setText(string);
    }
}
