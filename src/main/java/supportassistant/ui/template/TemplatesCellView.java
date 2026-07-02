package supportassistant.ui.template;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import supportassistant.entity.Template;
import supportassistant.model.TemplateModel;
import supportassistant.ui.button.Button;
import supportassistant.ui.window.ConfirmationWindow;

public class TemplatesCellView extends ListCell<Template> {
    private final TemplateModel model;

    public TemplatesCellView(TemplateModel model) {
        this.model = model;
    }

    @Override
    protected void updateItem(Template template, boolean empty) {
        super.updateItem(template, empty);

        if (template == null || empty) {
            setGraphic(null);
            return;
        }

        Label templateTitle = new Label(template.getTitle());
        Button deleteTemplate = new Button("Удалить");

        deleteTemplate.setOnAction(e -> ConfirmationWindow.showDeleteTemplateConfirmation(
                "Удаление",
                "Удалить шаблон",
                template,
                model
        ));

        HBox hBox = new HBox(50, templateTitle, deleteTemplate);
        setGraphic(hBox);
    }
}
