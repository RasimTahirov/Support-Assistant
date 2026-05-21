package sm.scriptmanager.ui.script;

import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import sm.scriptmanager.model.Script;
import sm.scriptmanager.storage.ScriptStorage;

public class ScriptList extends VBox {
    ListView<Script> scriptList = new ListView<>();

    public ScriptList(ScriptStorage storage) {
        scriptList.setItems(storage.getScripts());
        scriptList.setCellFactory(scripts -> new ScriptCellView(storage));

        getChildren().add(scriptList);
    }
}
