package mm.macrosmanager.storage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mm.macrosmanager.model.Macros;

public class MacrosStorage {
    private final ObservableList<Macros> macros = FXCollections.observableArrayList();

    public void addMacros(Macros macros) {
        if (macros.getMacros().isBlank() || macros.getTitle().isBlank()) {
            return;
        }

        this.macros.add(macros);
    }

    public ObservableList<Macros> getMacros() {
        return macros;
    }
}
