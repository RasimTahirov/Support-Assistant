package mm.macrosmanager.app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mm.macrosmanager.exception.MacrosException;
import mm.macrosmanager.model.MacrosModel;
import mm.macrosmanager.repository.MacrosRepository;
import mm.macrosmanager.repository.SqlMacrosRepository;
import mm.macrosmanager.service.MacrosService;
import mm.macrosmanager.service.PlatformService;
import mm.macrosmanager.service.ScreenRegisterService;
import mm.macrosmanager.ui.MainView;
import mm.macrosmanager.ui.window.ErrorWindow;
import mm.macrosmanager.utils.WindowFocusUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App extends Application {
    private final Logger logger = LoggerFactory.getLogger(App.class);

    private final ScreenRegisterService screenRegister = new ScreenRegisterService();
    private final MacrosRepository repository = new SqlMacrosRepository();
    private final MacrosService service = new MacrosService(repository);
    private final MacrosModel model = new MacrosModel(service);

    @Override
    public void start(Stage stage) {
        try {
            service.initStorage();
            model.load();
            screenRegister.register(stage);

            MainView mainView = new MainView(model);

            Scene scene = new Scene(mainView, 1000, 700);

            stage.setTitle("Macros Manager");
            stage.setScene(scene);
            stage.show();
            stage.toFront();

            WindowFocusUtils.requestForeground();

            logger.info("Приложение запущено на ОС {}", PlatformService.getOsName());
        } catch (MacrosException exception) {
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
