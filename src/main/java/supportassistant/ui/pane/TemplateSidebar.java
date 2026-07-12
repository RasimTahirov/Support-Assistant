package supportassistant.ui.pane;

import javafx.scene.control.TextField;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import supportassistant.exception.TemplateException;
import supportassistant.model.TemplateModel;
import supportassistant.ui.button.Button;
import supportassistant.ui.template.TemplatesList;
import supportassistant.ui.window.AddTemplateWindow;
import supportassistant.ui.window.alert.WarningWindow;
import supportassistant.utils.I18n;

public class TemplateSidebar extends VBox {
    public TemplateSidebar(TemplatesList templatesList, TemplateModel model) {
        AddTemplateWindow addTemplateWindow = new AddTemplateWindow();

        TextField search = new TextField();

        search.setPromptText(I18n.get("search"));
        search.textProperty().addListener((obs, oldVal, newVal) -> {
            try {
                model.search(newVal.toLowerCase());
            } catch (TemplateException exception) {
                WarningWindow.showWarning(I18n.get("message.warning.search"));
            }
        });

        Button addTemplateButton = new Button(I18n.get("button.add_template"));

        addTemplateButton.setOnAction(e -> addTemplateWindow.open(model));

        getChildren().addAll(search, addTemplateButton, templatesList);

        addTemplateButton.setMaxWidth(Double.MAX_VALUE);
        VBox.setVgrow(templatesList, Priority.ALWAYS);

        getStyleClass().add("sidebar");
        search.getStyleClass().add("search-field");
    }
}
