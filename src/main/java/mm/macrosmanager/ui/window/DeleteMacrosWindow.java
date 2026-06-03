package mm.macrosmanager.ui.window;

import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import mm.macrosmanager.model.Macros;
import mm.macrosmanager.storage.MacrosStorage;
import mm.macrosmanager.ui.button.Button;

public class DeleteMacrosWindow {
    public void open(MacrosStorage storage, Macros macros) {
        Stage stage = new Stage();

        HBox hBox = new HBox();

        Button yes = new Button("Да");
        Button no = new Button("Отменить");

        yes.setOnAction(e -> {
            storage.deleteMacros(macros);

            stage.close();
        });
        no.setOnAction(e -> stage.close());

        hBox.getChildren().addAll(yes, no);

        Scene scene = new Scene(hBox, 150, 150);

        stage.setScene(scene);
        stage.show();
    }
}
