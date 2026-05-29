package mm.macrosmanager.model;

public class Macros {
    private final String title;
    private final String macros;

    public Macros(String title, String macros) {
        this.title = title;
        this.macros = macros;
    }

    public String getTitle() {
        return title;
    }

    public String getMacros() {
        return macros;
    }
}
