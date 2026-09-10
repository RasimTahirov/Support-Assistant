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

        logger.info("Database successfully initialized");
    }

    public List<Template> loadAllTemplates() {
        List<Template> list = repository.getAll();

        logger.debug("Loaded {} templates from database", list.size());

        return list;
    }

    public List<Template> searchTemplates(String title) {
        if (title == null || title.isBlank()) {
            return repository.getAll();
        }

        List<Template> list = repository.search(title);

        logger.debug("Search by query '{}' returned {} results", title, list.size());

        return list;
    }

    public Template createTemplate(Template templates) {
        if (templates.getTemplate().isBlank() || templates.getTitle().isBlank()) {
            throw new TemplateException("Title and template cannot be empty");
        }

        Template newTemplate = repository.add(templates);

        logger.info("Successfully created new template: title - {}, id - {}", newTemplate.getTitle(), newTemplate.getId());

        return newTemplate;
    }

    public Template updateTemplate(int id, Template template) {
        if (template.getTemplate().isBlank() || template.getTitle().isBlank()) {
            throw new TemplateException("Title and template cannot be empty");
        }

        Template updateTemplate = repository.update(id, template);

        logger.info(
                "Successfully updated template: title - {}, template - {}, id - {}",
                updateTemplate.getTitle(), template.getTemplate(), updateTemplate.getId());

        return updateTemplate;
    }

    public void deleteTemplate(Template template) {
        repository.delete(template.getId());

        logger.info("Template title - {}, id - {} successfully deleted", template.getTitle(), template.getId());
    }
}
