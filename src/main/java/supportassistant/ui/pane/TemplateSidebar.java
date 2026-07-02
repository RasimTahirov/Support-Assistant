package supportassistant.ui.pane;

import javafx.scene.control.TextField;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import supportassistant.exception.TemplateException;
import supportassistant.model.TemplateModel;
import supportassistant.ui.button.Button;
import supportassistant.ui.template.TemplatesList;
import supportassistant.ui.window.AddTemplateWindow;
import supportassistant.ui.window.WarningWindow;

public class TemplateSidebar extends VBox {
    public TemplateSidebar(TemplatesList templatesList, AddTemplateWindow addTemplateWindow, TemplateModel model) {
        TextField search = new TextField();

        search.setPromptText("Поиск...");
        search.textProperty().addListener((obs, oldVal, newVal) -> {
            try {
                model.search(newVal.toLowerCase());
            } catch (TemplateException exception) {
                WarningWindow.showWarning("Не удалось выполнить поиск");
            }
        });

        Button addTemplateButton = new Button("Добавить новый шаблон");

        addTemplateButton.setOnAction(e -> addTemplateWindow.open(model));

        getChildren().addAll(search, addTemplateButton, templatesList);

        addTemplateButton.setMaxWidth(Double.MAX_VALUE);
        VBox.setVgrow(templatesList, Priority.ALWAYS);

        getStyleClass().add("sidebar");
        search.getStyleClass().add("search-field");
    }
}
