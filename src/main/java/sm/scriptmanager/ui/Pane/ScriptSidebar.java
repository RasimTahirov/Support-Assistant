package sm.scriptmanager.ui.Pane;

import javafx.scene.layout.VBox;
import sm.scriptmanager.ui.Button.Button;
import sm.scriptmanager.ui.script.ScriptList;
import sm.scriptmanager.ui.window.AddScriptWindow;

public class ScriptSidebar extends VBox {
    public ScriptSidebar(ScriptList scriptList, AddScriptWindow addScriptWindow) {
        Button addScriptButton = new Button("Добавить новый скрипт");

        addScriptButton.setOnAction(e -> {
            addScriptWindow.open();
        });

        getChildren().addAll(scriptList, addScriptButton);
    }
}
