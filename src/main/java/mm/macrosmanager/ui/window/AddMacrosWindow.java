package mm.macrosmanager.ui.window;

import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mm.macrosmanager.model.Macros;
import mm.macrosmanager.storage.MacrosStorage;
import mm.macrosmanager.ui.button.Button;

import java.sql.SQLException;

public class AddMacrosWindow {
    public void open(MacrosStorage storage) {
        Stage stage = new Stage();

        VBox root = new VBox();

        TextField title = new TextField();
        title.setPromptText("Название");

        TextArea macros = new TextArea();
        macros.setPromptText("Макрос");

        Button saveButton = new Button("Сохранить");

        saveButton.setOnAction(e -> {
            if (title.getText().isBlank() || macros.getText().isBlank()) {
                WarningWindow.showWarning("Добавьте название и макрос");
                return;
            }

            try {
                storage.addMacros(new Macros(title.getText(), macros.getText()));
            } catch (SQLException exception) {
                System.out.println("SQL ошибка " + exception.getMessage());
                WarningWindow.showWarning(exception.getMessage());
            }

            stage.close();
        });

        root.getChildren().addAll(title, macros, saveButton);

        Scene scene = new Scene(root, 400, 400);

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.show();
    }
}
