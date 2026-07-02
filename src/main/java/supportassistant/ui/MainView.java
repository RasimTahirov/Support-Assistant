package supportassistant.ui;

import javafx.scene.control.SplitPane;
import supportassistant.model.TemplateModel;
import supportassistant.service.ClipboardService;
import supportassistant.ui.pane.TemplateContent;
import supportassistant.ui.pane.TemplateSidebar;
import supportassistant.ui.template.TemplatesList;
import supportassistant.ui.window.AddTemplateWindow;

public class MainView extends SplitPane {
    public MainView(TemplateModel model) {
        AddTemplateWindow addTemplateWindow = new AddTemplateWindow();
        TemplatesList templatesList = new TemplatesList(model);
        ClipboardService clipboardService = new ClipboardService();

        TemplateContent content = new TemplateContent(templatesList, clipboardService);
        TemplateSidebar sidebar = new TemplateSidebar(templatesList, addTemplateWindow, model);

        setDividerPositions(0.3);

        getItems().addAll(sidebar, content);

        getStyleClass().add("main-split");
    }
}
