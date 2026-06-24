package mm.macrosmanager.ui.window;

import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mm.macrosmanager.exception.MacrosException;
import mm.macrosmanager.model.MacrosModel;
import mm.macrosmanager.ui.button.Button;

public class AddMacrosWindow {
    public void open(MacrosModel model) {
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
                model.add(title.getText(), macros.getText());
                stage.close();
            } catch (MacrosException exception) {
                WarningWindow.showWarning(exception.getMessage());
            }
        });

        root.getChildren().addAll(title, macros, saveButton);

        Scene scene = new Scene(root, 400, 400);

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.show();
    }
}
