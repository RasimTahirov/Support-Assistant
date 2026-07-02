package supportassistant.repository;

import supportassistant.entity.Template;

import java.util.List;

public interface TemplateRepository {
    void init();
    List<Template> getAll();
    List<Template> search(String title);
    Template add(Template template);
    void delete(int id);
}
