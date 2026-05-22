package sm.scriptmanager.ui.Pane;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import sm.scriptmanager.service.ClipboardService;
import sm.scriptmanager.ui.Button.Button;

public class ScriptContent extends VBox {
    private final Text text = new Text();

    public ScriptContent(ClipboardService clipboard) {
        Button copyButton = new Button("Скопировать");

        copyButton.setOnAction(e -> clipboard.copy(text.getText()));

        getChildren().addAll(text, copyButton);
    }

    public void showScript(String string) {
        text.setText(string);
    }
}
