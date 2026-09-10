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

import static supportassistant.utils.I18n.get;

public class TemplateWindow {
    private Stage stage;
    private VBox vBox;
    private Text heading;
    private TextField title;
    private TextArea template;
    private Button saveButton;
    private HBox buttonContainer;

    public TemplateWindow(TemplateModel model) {
        this.heading = new Text(get("message.template.title.creation"));
        initUi();

        saveButton.setOnAction(e -> handleButton(() -> {
            model.add(title.getText(), template.getText());
        }));

        stage.show();
    }

    public TemplateWindow(TemplateModel model, int id, String titleTest, String templateTest) { // ИСПРАВИТ!!!
        this.heading = new Text(get("message.template.title.editing"));
        initUi();

        this.title.setText(titleTest);
        this.template.setText(templateTest);

        saveButton.setOnAction(e -> handleButton(() -> {
            model.update(id, title.getText(), template.getText());
        }));

        stage.show();
    }

    private void handleButton(Runnable save) {
        if (title.getText().isBlank() || template.getText().isBlank()) {
            WarningAlert warning = new WarningAlert();
            warning.setContentText(get("message.warning.content.template"));
            warning.showAndWait();
            return;
        }

        try {
            save.run();
            stage.close();
        } catch (TemplateException exception) {
            WarningAlert warning = new WarningAlert();
            warning.setContentText(exception.getMessage());
            warning.showAndWait();
        }
    }

    private void initUi() {
        this.stage = new Stage();
        this.vBox = new VBox();
        this.title = new TextField();
        this.template = new TextArea();
        this.saveButton = new Button(get("text.save"));
        this.buttonContainer = new HBox(saveButton);

        title.setPromptText(get("message.template.name"));
        template.setPromptText(get("message.template.description"));

        template.setWrapText(true);

        vBox.getChildren().addAll(heading, title, template, buttonContainer);

        VBox.setVgrow(template, Priority.ALWAYS);
        buttonContainer.setAlignment(Pos.CENTER);

        vBox.getStyleClass().add("dialog-root");
        heading.getStyleClass().add("dialog-title");
        title.getStyleClass().add("dialog-field");
        template.getStyleClass().add("dialog-textarea");

        Scene scene = new Scene(vBox, 400, 400);
        LoadStyles.apply(scene);

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
    }
}
