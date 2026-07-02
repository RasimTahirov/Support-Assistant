package supportassistant.entity;

public class Template {
    private final int id;
    private final String title;
    private final String title_lower;
    private final String template;

    public Template(String title, String template) {
        this.id = 0;
        this.title = title;
        this.title_lower = title.toLowerCase();
        this.template = template;
    }

    public Template(int id, String title, String template) {
        this.id = id;
        this.title = title;
        this.title_lower = title.toLowerCase();
        this.template = template;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getTitle_lower() {
        return title_lower;
    }

    public String getTemplate() {
        return template;
    }
}
