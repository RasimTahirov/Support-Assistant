package mm.macrosmanager.service;

import mm.macrosmanager.entity.Macros;
import mm.macrosmanager.repository.MacrosRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class MacrosService {
    private final MacrosRepository repository;
    private static final Logger logger = LoggerFactory.getLogger(MacrosService.class);

    public MacrosService(MacrosRepository repository) {
        this.repository = repository;
    }

    public void initStorage() {
        repository.init();

        logger.info("БД успешно инициализировано");
    }

    public List<Macros> loadAllMacros() {
        List<Macros> list = repository.getAll();

        logger.debug("Загружено {} макросов из БД", list.size());

        return list;
    }

    public List<Macros> searchMacros(String title) {
        if (title == null || title.isBlank()) {
            return repository.getAll();
        }

        List<Macros> list = repository.search(title);

        logger.info("Поиск по запросу '{}' вернул {} результатов", title, list.size());

        return list;
    }

    public Macros createMacros(Macros macros) {
        if (macros.getMacros().isBlank() || macros.getTitle().isBlank()) {
            throw new IllegalArgumentException("Название и макрос не могут быть пустыми");
        }

        Macros newMacros = repository.add(macros);

        logger.info("Успешно создан новый макрос: title - {}, id - {}", newMacros.getTitle(), newMacros.getId());

        return newMacros;
    }

    public void deleteMacros(Macros macros) {
        repository.delete(macros.getId());

        logger.info("Макрос title - {}, id - {} успешно удален", macros.getTitle(), macros.getId());
    }
}
