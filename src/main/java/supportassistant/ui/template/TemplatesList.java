package supportassistant.ui.template;

import javafx.scene.control.ListView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import supportassistant.entity.Template;
import supportassistant.model.TemplateModel;

public class TemplatesList extends VBox {
    ListView<Template> templatesList = new ListView<>();

    public TemplatesList(TemplateModel model) {
        templatesList.setItems(model.geTemplatesList());
        templatesList.setCellFactory(template -> new TemplatesCellView(model));

        VBox.setVgrow(templatesList, Priority.ALWAYS);
        getChildren().add(templatesList);
    }

    public ListView<Template> getTemplatesList() {
        return templatesList;
    }
}
