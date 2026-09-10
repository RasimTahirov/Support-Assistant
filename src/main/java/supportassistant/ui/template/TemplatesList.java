package supportassistant.ui.template;

import javafx.scene.control.ListView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import supportassistant.entity.Template;
import supportassistant.model.TemplateModel;

public class TemplatesList extends VBox {
    ListView<Template> templatesList = new ListView<>();

    public TemplatesList(TemplateModel model) {
        templatesList.setItems(model.getTemplatesList());
        templatesList.setCellFactory(template -> new TemplatesCellView(model));

        getChildren().add(templatesList);

        VBox.setVgrow(templatesList, Priority.ALWAYS);

        templatesList.getStyleClass().add("template-list");
    }

    public ListView<Template> getTemplatesList() {
        return templatesList;
    }
}
