package supportassistant.ui.window.settings;

import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import supportassistant.Interface.SettingsInterface;
import supportassistant.service.SettingsService;
import supportassistant.utils.I18n;
import supportassistant.utils.LanguageCode;

import java.util.Locale;

public class LanguageSettings extends HBox implements SettingsInterface {
    private final ComboBox<String> comboBox;
    private final SettingsService service;

    public LanguageSettings(SettingsService service) {
        this.service = service;
        this.comboBox = new ComboBox<>();

        LanguageCode languageCode = new LanguageCode();

        Label languageLabel = new Label(I18n.get("label.language"));
        Region region = new Region();

        comboBox.getItems().addAll(languageCode.getRussian(), languageCode.getEnglish());
        comboBox.setValue(service.getSettings().getLanguage().toUpperCase());

        comboBox.setOnAction(e -> {
            String code = comboBox.getValue();
            I18n.setLocale(Locale.of(code));
        });

        HBox languageContainer = new HBox(languageLabel, region, comboBox);

        getChildren().addAll(languageContainer);

        HBox.setHgrow(languageContainer, Priority.ALWAYS);
        HBox.setHgrow(region, Priority.ALWAYS);
        languageContainer.setAlignment(Pos.CENTER_LEFT);

        comboBox.getStyleClass().add("como-box-settings");
        languageContainer.getStyleClass().add("language-settings-container");
    }

    @Override
    public void apply() {
        String code = comboBox.getValue();
        service.getSettings().setLanguage(code);
    }
}