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
import supportassistant.service.PlatformService;
import supportassistant.service.ScreenRegisterService;
import supportassistant.service.TemplateService;
import supportassistant.ui.MainView;
import supportassistant.ui.window.ErrorWindow;
import supportassistant.utils.WindowFocusUtils;

public class App extends Application {
    private final Logger logger = LoggerFactory.getLogger(App.class);

    private final ScreenRegisterService screenRegister = new ScreenRegisterService();
    private final TemplateRepository repository = new SqlTemplateRepository();
    private final TemplateService service = new TemplateService(repository);
    private final TemplateModel model = new TemplateModel(service);

    @Override
    public void start(Stage stage) {
        try {
            service.initStorage();
            model.load();
            screenRegister.register(stage);

            MainView mainView = new MainView(model);

            Scene scene = new Scene(mainView, 1000, 700);

            stage.setTitle("Support Assistant");
            stage.setScene(scene);
            stage.show();
            stage.toFront();

            WindowFocusUtils.requestForeground();

            logger.info("Приложение запущено на ОС {}", PlatformService.getOsName());
        } catch (TemplateException exception) {
            logger.error("Возникла ошибка. Приложение не запущено {}", exception.getMessage(), exception);
            ErrorWindow.showInitDatabaseError();
        }
    }

    @Override
    public void stop() {
        screenRegister.unregister();

        logger.info("Приложение успешно завершило работу");
    }

    public static void main(String[] args) {
        PlatformService.saveLogPathProperty();
        launch(args);
    }
}
