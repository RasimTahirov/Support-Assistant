package sm.scriptmanager.storage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sm.scriptmanager.model.Script;

public class ScriptStorage {
    private final ObservableList<Script> scripts = FXCollections.observableArrayList();

    public void addScript(Script script) {
        scripts.add(script);
    }

    public ObservableList<Script> getScripts() {
        return scripts;
    }
}
