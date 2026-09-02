package supportassistant.ui.window;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import supportassistant.exception.TemplateException;
import supportassistant.model.TemplateModel;
import supportassistant.ui.button.Button;
import supportassistant.ui.window.alert.WarningAlert;
import supportassistant.utils.LoadStyles;

import static supportassistant.utils.I18n.*;

public class AddTemplateWindow {
    public void open(TemplateModel model) {
        Stage stage = new Stage();

        VBox root = new VBox();

        Text heading = new Text(get("message.template.title"));

        TextField title = new TextField();
        title.setPromptText(get("message.template.name"));

        TextArea template = new TextArea();
        template.setPromptText(get("message.template.description"));

        Button saveButton = new Button(get("text.save"));

        HBox buttonContainer = new HBox(saveButton);

        saveButton.setOnAction(e -> {
            if (title.getText().isBlank() || template.getText().isBlank()) {
                WarningAlert warning = new WarningAlert();
                warning.setContentText("Добавьте название и шаблон"); // Изменить
                warning.showAndWait();
                return;
            }

            try {
                model.add(title.getText(), template.getText());
                stage.close();
            } catch (TemplateException exception) {
                WarningAlert warning = new WarningAlert();
                warning.setContentText(exception.getMessage());
                warning.showAndWait();
            }
        });

        template.setWrapText(true);

        root.getChildren().addAll(heading, title, template, buttonContainer);

        VBox.setVgrow(template, Priority.ALWAYS);
        buttonContainer.setAlignment(Pos.CENTER);

        root.getStyleClass().add("dialog-root");
        heading.getStyleClass().add("dialog-title");
        title.getStyleClass().add("dialog-field");
        template.getStyleClass().add("dialog-textarea");

        Scene scene = new Scene(root, 400, 400);
        LoadStyles.apply(scene);

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.show();
    }
}
