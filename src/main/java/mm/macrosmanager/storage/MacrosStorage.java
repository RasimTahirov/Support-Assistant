package mm.macrosmanager.storage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mm.macrosmanager.database.MacrosDatabase;
import mm.macrosmanager.model.Macros;

import java.sql.SQLException;
import java.util.List;

public class MacrosStorage {
    private final ObservableList<Macros> macrosList = FXCollections.observableArrayList();

    public void loadFromDatabase() {
        macrosList.setAll(MacrosDatabase.getAllMacros());
    }

    public void searchMacros(String title) {
        if (title == null || title.isBlank()) {
            loadFromDatabase();
            return;
        }

        try {
            List<Macros> searchResults = MacrosDatabase.searchMacros(title);

            macrosList.setAll(searchResults);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void addMacros(Macros macros) {
        if (macros.getMacros().isBlank() || macros.getTitle().isBlank()) return;

        try {
            int id = MacrosDatabase.addMacros(macros);

            Macros newMacros = new Macros(id, macros.getTitle(), macros.getMacros());

            macrosList.add(newMacros);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteMacros(Macros macros) {
        MacrosDatabase.deleteMacros(macros.getId());

        macrosList.remove(macros);
    }

    public ObservableList<Macros> getMacros() {
        return macrosList;
    }
}
