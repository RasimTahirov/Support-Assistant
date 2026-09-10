package supportassistant.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import supportassistant.entity.Template;
import supportassistant.service.TemplateService;

import java.util.List;

public class TemplateModel {
    private final TemplateService service;

    private final ObservableList<Template> templatesList = FXCollections.observableArrayList();

    public TemplateModel(TemplateService service) {
        this.service = service;
    }

    public ObservableList<Template> getTemplatesList() {
        return templatesList;
    }

    public void load() {
        List<Template> data = service.loadAllTemplates();
        templatesList.setAll(data);
    }

    public void search(String title) {
        List<Template> data = service.searchTemplates(title);
        templatesList.setAll(data);
    }

    public void add(String title, String content) {
        Template template = new Template(title, content);
        Template saved = service.createTemplate(template);

        templatesList.add(saved);
    }

    public void update(int id, String title, String content) {
        Template template = new Template(id, title, content);
        Template updated = service.updateTemplate(id, template);

        for (int i = 0; i < templatesList.size(); i++) {
            if (templatesList.get(i).getId() == id) {
                templatesList.set(i, updated);
                return;
            }
        }
    }

    public void delete(Template template) {
        service.deleteTemplate(template);
        templatesList.remove(template);
    }
}
