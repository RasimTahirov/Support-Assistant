package sm.scriptmanager.app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sm.scriptmanager.storage.ScriptStorage;
import sm.scriptmanager.ui.MainView;

public class App extends Application {
    private final ScriptStorage storage = new ScriptStorage();

    @Override
    public void start(Stage stage) {
        MainView mainView = new MainView(storage);

        Scene scene = new Scene(mainView, 700, 700);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
