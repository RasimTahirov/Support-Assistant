package mm.macrosmanager.app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mm.macrosmanager.database.MacrosDatabase;
import mm.macrosmanager.service.ScreenRegister;
import mm.macrosmanager.storage.MacrosStorage;
import mm.macrosmanager.ui.MainView;

public class App extends Application {
    private final MacrosStorage storage = new MacrosStorage();
    private final ScreenRegister screenRegister = new ScreenRegister();

    @Override
    public void start(Stage stage) {
        MacrosDatabase.init();
        storage.loadFromDatabase();
        screenRegister.register(stage);

        MainView mainView = new MainView(storage);

        Scene scene = new Scene(mainView, 1000, 700);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
