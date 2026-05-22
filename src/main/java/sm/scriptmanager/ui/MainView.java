package sm.scriptmanager.ui;

import javafx.scene.control.SplitPane;
import sm.scriptmanager.storage.ScriptStorage;
import sm.scriptmanager.ui.Pane.ScriptContent;
import sm.scriptmanager.ui.Pane.ScriptSidebar;
import sm.scriptmanager.ui.script.ScriptList;
import sm.scriptmanager.ui.window.AddScriptWindow;

public class MainView extends SplitPane {
    private final ScriptSidebar sidebar;
    private final ScriptContent content;

    public MainView(ScriptStorage storage) {
        AddScriptWindow addScriptWindow = new AddScriptWindow(storage);
        ScriptList scriptList = new ScriptList(storage);

        this.content = new ScriptContent();
        this.sidebar = new ScriptSidebar(scriptList, addScriptWindow);

        scriptList.getScriptList()
                .getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, old, val) -> {
                    content.showScript(val.getScript());
                });

        getItems().addAll(sidebar, content);
    }
}
