package mm.macrosmanager.model;

public class Macros {
    private final int id;
    private final String title;
    private final String macros;

    public Macros(String title, String macros) {
        this.id = 0;
        this.title = title;
        this.macros = macros;
    }

    public Macros(int id, String title, String macros) {
        this.id = id;
        this.title = title;
        this.macros = macros;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getMacros() {
        return macros;
    }
}
