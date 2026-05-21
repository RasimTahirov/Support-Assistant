package sm.scriptmanager.app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sm.scriptmanager.storage.ScriptStorage;
import sm.scriptmanager.ui.MainView;
import sm.scriptmanager.ui.script.ScriptList;
import sm.scriptmanager.ui.window.AddScriptWindow;

public class App extends Application {
    private final ScriptStorage storage = new ScriptStorage();

    private final ScriptList scriptList = new ScriptList(storage);
    private final AddScriptWindow addScriptWindow = new AddScriptWindow(storage);

    @Override
    public void start(Stage stage) {
        MainView mainView = new MainView(scriptList, addScriptWindow, stage);

        Scene scene = new Scene(mainView, 500, 500);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
