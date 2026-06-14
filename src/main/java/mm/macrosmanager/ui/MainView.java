package mm.macrosmanager.ui;

import javafx.scene.control.SplitPane;
import mm.macrosmanager.service.ClipboardService;
import mm.macrosmanager.storage.MacrosStorage;
import mm.macrosmanager.ui.pane.MacrosContent;
import mm.macrosmanager.ui.pane.MacrosSidebar;
import mm.macrosmanager.ui.macros.MacrosList;
import mm.macrosmanager.ui.window.AddMacrosWindow;

public class MainView extends SplitPane {
    private final MacrosSidebar sidebar;
    private final MacrosContent content;

    public MainView(MacrosStorage storage) {
        AddMacrosWindow addMacrosWindow = new AddMacrosWindow();
        MacrosList macrosList = new MacrosList(storage);
        ClipboardService clipboardService = new ClipboardService();

        this.content = new MacrosContent(clipboardService);
        this.sidebar = new MacrosSidebar(macrosList, addMacrosWindow, storage);

        macrosList.getMacrosList()
                .getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, old, val) -> {
                    if (val != null) {
                        content.showMacros(val.getTitle(), val.getMacros());
                    }
                });

        setDividerPositions(0.3);

        getItems().addAll(sidebar, content);
    }
}
