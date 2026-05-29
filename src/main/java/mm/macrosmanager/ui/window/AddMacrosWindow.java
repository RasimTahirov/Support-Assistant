package mm.macrosmanager.ui.window;

import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import mm.macrosmanager.model.Macros;
import mm.macrosmanager.storage.MacrosStorage;
import mm.macrosmanager.ui.button.Button;

public class AddMacrosWindow {
    private final MacrosStorage storage;

    public AddMacrosWindow(MacrosStorage storage) {
        this.storage = storage;
    }

    public void open() {
        Stage stage = new Stage();

        VBox root = new VBox();

        TextField title = new TextField();
        title.setPromptText("Название");

        TextArea macros = new TextArea();
        macros.setPromptText("Макрос");

        Button saveButton = new Button("Сохранить");

        saveButton.setOnAction(e -> {
            if (title.getText().isBlank() || macros.getText().isBlank()) {
                Text text = new Text("Добавьте название и скрипт");
                root.getChildren().add(text);

                return;
            }

            storage.addMacros(new Macros(title.getText(), macros.getText()));

            stage.close();
        });

        root.getChildren().addAll(title, macros, saveButton);

        Scene scene = new Scene(root, 400, 400);

        stage.setScene(scene);
        stage.show();
    }
}
