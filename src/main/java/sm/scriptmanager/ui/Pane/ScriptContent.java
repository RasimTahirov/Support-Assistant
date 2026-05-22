package sm.scriptmanager.ui.Pane;

import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class ScriptContent extends HBox {
    private final Text text = new Text();

    public ScriptContent() {
        getChildren().add(text);

    }

    public void showScript(String string) {
        text.setText(string);
    }
}
