package sm.scriptmanager.app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) {
        VBox vBox = new VBox();
        Text text = new Text("Hello ScriptManager");

        vBox.getChildren().add(text);

        Scene scene = new Scene(vBox, 500, 500);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
