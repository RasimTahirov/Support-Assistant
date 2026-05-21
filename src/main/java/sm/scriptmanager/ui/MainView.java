package sm.scriptmanager.ui;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sm.scriptmanager.ui.Button.Button;
import sm.scriptmanager.ui.script.ScriptList;
import sm.scriptmanager.ui.window.AddScriptWindow;

public class MainView extends VBox {
    public MainView(ScriptList scriptList, AddScriptWindow addScriptWindow, Stage stage) {
        Button addScriptButton = new Button("Добавить новый скрипт");

        addScriptButton.setOnAction(e -> {
            addScriptWindow.open();
        });

        getChildren().addAll(scriptList, addScriptButton);
    }
}
