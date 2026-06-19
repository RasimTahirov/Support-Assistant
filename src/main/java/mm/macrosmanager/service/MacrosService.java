package mm.macrosmanager.service;

import mm.macrosmanager.entity.Macros;
import mm.macrosmanager.repository.MacrosRepository;

import java.util.List;

public class MacrosService {
    private final MacrosRepository repository;

    public MacrosService(MacrosRepository repository) {
        this.repository = repository;
    }

    public void initStorage() {
        repository.init();
    }

    public List<Macros> loadAllMacros() {
        return repository.getAll();
    }

    public List<Macros> searchMacros(String title) {
        if (title == null || title.isBlank()) {
            return repository.getAll();
        }

        return repository.search(title);
    }

    public Macros createMacros(Macros macros) {
        if (macros.getMacros().isBlank() || macros.getTitle().isBlank()) {
            throw new IllegalArgumentException("Название и макрос не могут быть пустыми");
        }

        return repository.add(macros);
    }

    public void deleteMacros(Macros macros) {
        repository.delete(macros.getId());
    }
}
