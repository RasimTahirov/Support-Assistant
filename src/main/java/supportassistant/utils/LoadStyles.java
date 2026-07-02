package supportassistant.utils;

import javafx.scene.Scene;
import javafx.scene.control.DialogPane;
import javafx.scene.text.Font;

import java.util.Objects;

public class LoadStyles {
    private static final String CSS = Objects.requireNonNull(
            LoadStyles.class.getResource("/css/styles.css")).toExternalForm();

    public static void apply(Scene scene) {
        scene.getStylesheets().add(CSS);

        Font.loadFont(
                LoadStyles.class.getResourceAsStream("/fonts/RobotoMono-Medium.ttf"),
                14
        );

        Font.loadFont(
                LoadStyles.class.getResourceAsStream("/fonts/RobotoMono-ExtraLight.ttf"),
                14);

        Font.loadFont(
                LoadStyles.class.getResourceAsStream("/fonts/RobotoMono-Light.ttf"),
                14);
    }

    public static void apply(DialogPane dialogPane) {
        dialogPane.getStylesheets().add(CSS);
        dialogPane.getStyleClass().add("app-dialog");
    }
}
