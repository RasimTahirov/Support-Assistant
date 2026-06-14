package mm.macrosmanager.storage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mm.macrosmanager.database.MacrosDatabase;
import mm.macrosmanager.model.Macros;

import java.sql.SQLException;
import java.util.List;

public class MacrosStorage {
    private final ObservableList<Macros> macrosList = FXCollections.observableArrayList();

    public void initDatabase() throws SQLException {
        MacrosDatabase.init();
    }

    public void loadFromDatabase() throws SQLException {
        macrosList.setAll(MacrosDatabase.getAllMacros());
    }

    public void searchMacros(String title) throws SQLException {
        if (title == null || title.isBlank()) {
            loadFromDatabase();
            return;
        }

        List<Macros> searchResults = MacrosDatabase.searchMacros(title);

        macrosList.setAll(searchResults);
    }

    public void addMacros(Macros macros) throws SQLException {
        if (macros.getMacros().isBlank() || macros.getTitle().isBlank()) return;

        int id = MacrosDatabase.addMacros(macros);

        Macros newMacros = new Macros(id, macros.getTitle(), macros.getMacros());

        macrosList.add(newMacros);
    }

    public void deleteMacros(Macros macros) throws SQLException {
        MacrosDatabase.deleteMacros(macros.getId());

        macrosList.remove(macros);
    }

    public ObservableList<Macros> getMacros() {
        return macrosList;
    }
}
