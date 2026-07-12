package supportassistant.ui.pane;

import javafx.scene.control.SplitPane;
import supportassistant.model.TemplateModel;
import supportassistant.service.SettingsService;
import supportassistant.ui.template.TemplatesList;
import supportassistant.ui.window.settings.SettingsWindow;

public class MainPane extends SplitPane {
    private final TemplateSidebar sidebar;
    private final TemplateContent content;
    private final SettingsWindow settingsWindow;

    public MainPane(TemplateModel model, SettingsService service, TemplatesList templatesList) {
        this.sidebar = new TemplateSidebar(templatesList, model);
        this.content = new TemplateContent(templatesList);
        this.settingsWindow = new SettingsWindow(service, () -> setSettingVisible(false));

        setDividerPositions(0.3);
        getItems().addAll(sidebar, content);

        getStyleClass().add("main-split");
    }

    public void setSettingVisible(boolean visible) {
        double currentPosition = getDividerPositions()[0];

        getItems().set(0, visible ? settingsWindow : sidebar);

        setDividerPositions(currentPosition);
    }
}
