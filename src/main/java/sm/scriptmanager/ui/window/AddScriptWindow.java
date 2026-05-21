package sm.scriptmanager.ui.window;

import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sm.scriptmanager.model.Script;
import sm.scriptmanager.storage.ScriptStorage;
import sm.scriptmanager.ui.Button.Button;

public class AddScriptWindow {
    private final ScriptStorage storage;

    public AddScriptWindow(ScriptStorage storage) {
        this.storage = storage;
    }

    public void open() {
        Stage stage = new Stage();

        VBox root = new VBox();

        TextField title = new TextField();
        title.setPromptText("Название");

        TextArea script = new TextArea();
        script.setPromptText("Скрипт");

        Button saveButton = new Button("Сохранить");

        saveButton.setOnAction(e -> {
            if (title.getText().isBlank() || script.getText().isBlank()) {
                System.out.println("ПУСТО");
                return;
            }

            storage.addScript(new Script(title.getText(), script.getText()));

            stage.close();
        });

        root.getChildren().addAll(title, script, saveButton);

        Scene scene = new Scene(root, 400, 400);

        stage.setScene(scene);
        stage.show();
    }
}
