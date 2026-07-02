package supportassistant.ui.window;

import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import supportassistant.exception.TemplateException;
import supportassistant.model.TemplateModel;
import supportassistant.ui.button.Button;

public class AddTemplateWindow {
    public void open(TemplateModel model) {
        Stage stage = new Stage();

        VBox root = new VBox();

        TextField title = new TextField();
        title.setPromptText("Название");

        TextArea template = new TextArea();
        template.setPromptText("Шаблон");

        Button saveButton = new Button("Сохранить");

        saveButton.setOnAction(e -> {
            if (title.getText().isBlank() || template.getText().isBlank()) {
                WarningWindow.showWarning("Добавьте название и шаблон");
                return;
            }

            try {
                model.add(title.getText(), template.getText());
                stage.close();
            } catch (TemplateException exception) {
                WarningWindow.showWarning(exception.getMessage());
            }
        });

        root.getChildren().addAll(title, template, saveButton);

        Scene scene = new Scene(root, 400, 400);

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.show();
    }
}
