package supportassistant.ui.button;

public class Button extends javafx.scene.control.Button {
    public Button(String text) {
        super(text);

        getStyleClass().add("primary-button");
    }
}
