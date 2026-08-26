package supportassistant.app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import supportassistant.exception.TemplateException;
import supportassistant.model.TemplateModel;
import supportassistant.repository.SqlTemplateRepository;
import supportassistant.repository.TemplateRepository;
import supportassistant.service.DatabaseService;
import supportassistant.utils.*;
import supportassistant.service.ScreenRegisterService;
import supportassistant.service.SettingsService;
import supportassistant.service.TemplateService;
import supportassistant.ui.MainView;
import supportassistant.ui.window.alert.ErrorWindow;

import java.util.Locale;

public class App extends Application {
    private final Logger logger = LoggerFactory.getLogger(App.class);

    private TemplateRepository repository;
    private TemplateService templateService;
    private SettingsService settingsService;
    private TemplateModel model;
    private ScreenRegisterService screenRegister;

    @Override
    public void init() {
        this.repository = new SqlTemplateRepository();
        this.settingsService = new SettingsService();
        this.templateService = new TemplateService(repository);
        this.model = new TemplateModel(templateService);
        this.screenRegister = new ScreenRegisterService();

        templateService.initStorage();
        model.load();
        settingsService.load();

        String language = settingsService.getSettings().getLanguage();
        I18n.setLocale(Locale.of(language));
    }

    @Override
    public void start(Stage stage) {
        try {
            screenRegister.register(stage);

            MainView mainView = new MainView(model, settingsService);

            Scene scene = new Scene(mainView, 1200, 900);

            LoadStyles.apply(scene);

            stage.setTitle("Support Assistant");
            stage.setScene(scene);
            stage.show();
            stage.toFront();

            WindowFocusUtils.requestForeground();

            logger.info("Application started on OS {}", SystemInfoUtils.getOsName());
        } catch (TemplateException exception) {
            logger.error("An error occurred. Application failed to start {}", exception.getMessage(), exception);
            ErrorWindow.showInitDatabaseError();
        }
    }

    @Override
    public void stop() {
        screenRegister.unregister();

        logger.info("Application successfully shut down");
    }

    public static void main(String[] args) {
        DatabaseService.saveLogPathProperty();
        launch(args);
    }
}
