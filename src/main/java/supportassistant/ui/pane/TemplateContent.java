package supportassistant.ui.pane;

import javafx.geometry.Pos;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import supportassistant.service.ClipboardService;
import supportassistant.ui.button.Button;
import supportassistant.ui.template.TemplatesList;
import supportassistant.utils.I18n;

public class TemplateContent extends VBox {
    public TemplateContent(TemplatesList templatesList) {
        ClipboardService clipboard = new ClipboardService();

        Button copyButton = new Button(I18n.get("button.copy"));
        TextArea template = new TextArea();
        Text title = new Text();

        copyButton.setOnAction(e -> clipboard.copy(template.getText()));
        HBox buttonContainer = new HBox(copyButton);

        templatesList.getTemplatesList()
                .getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, old, val) -> {
                    if (val != null) {
                        title.setText(val.getTitle());
                        template.setText(val.getTemplate());
                    }
                });

        templatesList.getTemplatesList()
                .getSelectionModel()
                .selectFirst();

        template.setEditable(false);
        template.setWrapText(true);

        getChildren().addAll(title, template, buttonContainer);

        VBox.setVgrow(template, Priority.ALWAYS);
        buttonContainer.setAlignment(Pos.CENTER_RIGHT);

        getStyleClass().add("content-pane");
        title.getStyleClass().add("content-title");
        template.getStyleClass().add("template-area");
    }
}
