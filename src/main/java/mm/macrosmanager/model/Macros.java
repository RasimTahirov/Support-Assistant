package mm.macrosmanager.model;

public class Macros {
    private final int id;
    private final String title;
    private final String title_lower;
    private final String macros;

    public Macros(String title, String macros) {
        this.id = 0;
        this.title = title;
        this.title_lower = title.toLowerCase();
        this.macros = macros;
    }

    public Macros(int id, String title, String macros) {
        this.id = id;
        this.title = title;
        this.title_lower = title.toLowerCase();
        this.macros = macros;
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

    public String getMacros() {
        return macros;
    }
}
