package mm.macrosmanager.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mm.macrosmanager.entity.Macros;
import mm.macrosmanager.service.MacrosService;

import java.util.List;

public class MacrosModel {
    private final MacrosService service;

    private final ObservableList<Macros> macrosList = FXCollections.observableArrayList();

    public MacrosModel(MacrosService service) {
        this.service = service;
    }

    public ObservableList<Macros> getMacrosList() {
        return macrosList;
    }

    public void load() {
        List<Macros> data = service.loadAllMacros();
        macrosList.setAll(data);
    }

    public void search(String title) {
        List<Macros> data = service.searchMacros(title);
        macrosList.setAll(data);
    }

    public void add(String title, String content) {
        Macros macros = new Macros(title, content);
        Macros saved = service.createMacros(macros);

        macrosList.add(saved);
    }

    public void delete(Macros macros) {
        service.deleteMacros(macros);
        macrosList.remove(macros);
    }
}
