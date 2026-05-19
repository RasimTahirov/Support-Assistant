package sm.scriptmanager.model;

public class Script {
    private final String title;
    private final String script;

    public Script(String title, String script) {
        this.title = title;
        this.script = script;
    }

    public String getTitle() {
        return title;
    }

    public String getScript() {
        return script;
    }
}
