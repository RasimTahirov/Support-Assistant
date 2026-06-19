package mm.macrosmanager.app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mm.macrosmanager.exception.MacrosException;
import mm.macrosmanager.model.MacrosModel;
import mm.macrosmanager.repository.MacrosRepository;
import mm.macrosmanager.repository.SqlMacrosRepository;
import mm.macrosmanager.service.MacrosService;
import mm.macrosmanager.service.ScreenRegisterService;
import mm.macrosmanager.ui.MainView;
import mm.macrosmanager.ui.window.ErrorWindow;
import mm.macrosmanager.utils.WindowFocusUtils;

public class App extends Application {
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
        } catch (MacrosException exception) {
            System.out.println("SQL ошибка " + exception.getMessage());
            ErrorWindow.showInitDatabaseError();
        }
    }

    @Override
    public void stop() {
        screenRegister.unregister();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
