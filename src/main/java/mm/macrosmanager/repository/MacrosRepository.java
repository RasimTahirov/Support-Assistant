package mm.macrosmanager.repository;

import mm.macrosmanager.entity.Macros;

import java.util.List;

public interface MacrosRepository {
    void init();
    List<Macros> getAll();
    List<Macros> search(String title);
    Macros add(Macros macros);
    void delete(int id);
}
