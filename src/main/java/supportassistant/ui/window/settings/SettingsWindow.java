package supportassistant.ui.window.settings;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.remixicon.RemixiconAL;
import supportassistant.Interface.SettingsInterface;
import supportassistant.service.SettingsService;
import supportassistant.ui.button.Button;
import supportassistant.utils.I18n;

import java.util.ArrayList;
import java.util.List;

public class SettingsWindow extends VBox {
    private final List<SettingsInterface> settingsList = new ArrayList<>();

    public SettingsWindow(SettingsService settingsService, Runnable onClose) {
        LanguageSettings language = new LanguageSettings(settingsService);

        FontIcon closeIcon = new FontIcon(RemixiconAL.CLOSE_CIRCLE_LINE);
        closeIcon.setIconSize(25);
        closeIcon.setIconColor(Color.web("#7B6EF6"));

        settingsList.add(language);

        Label settingsLabel = new Label(I18n.get("label.settings_title"));
        Button saveButton = new Button(I18n.get("button.save"));
        Region region = new Region();
        Button closeButton = new Button();

        saveButton.setOnAction(e -> {
            for (int i = 0; i < settingsList.size(); i++) {
                SettingsInterface list = settingsList.get(i);
                list.apply();
            }

            settingsService.save();
        });

        closeButton.setGraphic(closeIcon);
        closeButton.setOnAction(e -> onClose.run());

        HBox closeButtonContainer = new HBox(closeButton);
        HBox settingsLabelContainer = new HBox(settingsLabel);
        HBox saveButtonContainer = new HBox(saveButton);

        getChildren().addAll(closeButtonContainer, settingsLabelContainer, language, region, saveButtonContainer);

        HBox.setHgrow(closeButtonContainer, Priority.ALWAYS);
        HBox.setHgrow(settingsLabelContainer, Priority.ALWAYS);
        HBox.setHgrow(settingsLabelContainer, Priority.ALWAYS);
        VBox.setVgrow(region, Priority.ALWAYS);

        closeButtonContainer.setAlignment(Pos.TOP_RIGHT);
        settingsLabelContainer.setAlignment(Pos.TOP_CENTER);
        saveButtonContainer.setAlignment(Pos.BOTTOM_CENTER);

        getStyleClass().add("settings-window");
        settingsLabelContainer.getStyleClass().add("settings-label-container");
        settingsLabel.getStyleClass().add("settings-label");
    }
}