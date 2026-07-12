package supportassistant.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import supportassistant.entity.Settings;
import supportassistant.utils.PathUtils;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;

import java.io.File;

public class SettingsService {
    private final Logger logger = LoggerFactory.getLogger(SettingsService.class);

    private final String settingsFile;

    private Settings settings = new Settings();
    private ObjectMapper mapper;

    public SettingsService() {
        settingsFile = getSettingsFilePath();
    }

    public void load() {
        this.mapper = new ObjectMapper();

        File file = new File(settingsFile);

        try {
            if (file.exists()) {
                settings = mapper.readValue(file, Settings.class);
                logger.info("Settings loaded successfully from");
            } else {
                settings = new Settings();
                save();
                logger.info("Settings file not found, created new default settings");
            }
        } catch (JacksonException exception) {
            logger.warn("Failed to read file {}", exception.getMessage());
        }
    }

    public void save() {
        this.mapper = new ObjectMapper();

        File file = new File(settingsFile);

        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, settings);
            logger.info("Settings saved successfully");
        } catch (JacksonException exception) {
            logger.warn("Failed to save settings {}", exception.getMessage());
        }
    }

    private String getSettingsFilePath() {
        return PathUtils.getFilePath("settings.json");
    }

    public Settings getSettings() {
        return settings;
    }
}
