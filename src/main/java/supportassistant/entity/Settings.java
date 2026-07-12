package supportassistant.entity;

import java.util.Locale;

public class Settings {
    private String language = Locale.getDefault().getLanguage();

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
