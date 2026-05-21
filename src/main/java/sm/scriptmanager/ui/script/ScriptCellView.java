package sm.scriptmanager.ui.script;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import sm.scriptmanager.model.Script;
import sm.scriptmanager.storage.ScriptStorage;

public class ScriptCellView extends ListCell<Script> {
    public ScriptCellView(ScriptStorage storage) {}

    @Override
    protected void updateItem(Script script, boolean empty) {
        super.updateItem(script, empty);

        if (script == null || empty) {
            setGraphic(null);
            return;
        }

        Label scriptTitle = new Label(script.getTitle());

        HBox hBox = new HBox(50, scriptTitle);
        setGraphic(hBox);
    }
}
