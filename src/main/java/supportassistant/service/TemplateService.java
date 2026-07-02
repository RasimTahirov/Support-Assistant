package supportassistant.service;

import supportassistant.entity.Template;
import supportassistant.exception.TemplateException;
import supportassistant.repository.TemplateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class TemplateService {
    private final TemplateRepository repository;
    private static final Logger logger = LoggerFactory.getLogger(TemplateService.class);

    public TemplateService(TemplateRepository repository) {
        this.repository = repository;
    }

    public void initStorage() {
        repository.init();

        logger.info("БД успешно инициализировано");
    }

    public List<Template> loadAllTemplates() {
        List<Template> list = repository.getAll();

        logger.debug("Загружено {} шаблонов из БД", list.size());

        return list;
    }

    public List<Template> searchTemplates(String title) {
        if (title == null || title.isBlank()) {
            return repository.getAll();
        }

        List<Template> list = repository.search(title);

        logger.info("Поиск по запросу '{}' вернул {} результатов", title, list.size());

        return list;
    }

    public Template createTemplate(Template templates) {
        if (templates.getTemplate().isBlank() || templates.getTitle().isBlank()) {
            throw new TemplateException("Название и шаблон не могут быть пустыми");
        }

        Template newTemplate = repository.add(templates);

        logger.info("Успешно создан новый шаблон: title - {}, id - {}", newTemplate.getTitle(), newTemplate.getId());

        return newTemplate;
    }

    public void deleteTemplate(Template template) {
        repository.delete(template.getId());

        logger.info("Шаблон title - {}, id - {} успешно удален", template.getTitle(), template.getId());
    }
}
