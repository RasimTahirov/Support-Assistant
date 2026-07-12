package supportassistant.ui;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import supportassistant.model.TemplateModel;
import supportassistant.service.SettingsService;
import supportassistant.ui.pane.MainPane;
import supportassistant.ui.pane.Navigation;
import supportassistant.ui.template.TemplatesList;

public class MainView extends HBox {
    public MainView(TemplateModel model, SettingsService service) {
        TemplatesList templatesList = new TemplatesList(model);

        MainPane mainPane = new MainPane(model, service, templatesList);
        Navigation navigation = new Navigation(mainPane::setSettingVisible);

        getChildren().addAll(navigation, mainPane);

        HBox.setHgrow(mainPane, Priority.ALWAYS);
    }
}
