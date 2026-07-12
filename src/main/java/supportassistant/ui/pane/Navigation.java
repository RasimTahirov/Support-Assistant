package supportassistant.ui.pane;

import javafx.animation.RotateTransition;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.remixicon.RemixiconMZ;
import supportassistant.ui.button.Button;

import java.util.function.Consumer;

public class Navigation extends VBox {
    private final FontIcon settingsIcon = new FontIcon(RemixiconMZ.SETTINGS_3_FILL);

    private boolean isSettingOpen;

    public Navigation(Consumer<Boolean> settingsVisible) {
        settingsIcon.setIconSize(25);
        settingsIcon.setIconColor(Color.web("#7B6EF6"));

        Button settingsButton = new Button();
        Region region = new Region();

        settingsButton.setGraphic(settingsIcon);

        settingsButton.setOnAction(e -> {
            isSettingOpen = !isSettingOpen;

            settingsVisible.accept(isSettingOpen);
        });

        settingsButton.setOnMouseEntered(e -> hoverIn(settingsIcon));
        settingsButton.setOnMouseExited(e -> hoverOut(settingsIcon));

        VBox.setVgrow(region, Priority.ALWAYS);

        setMinWidth(50);
        setMaxWidth(50);

        HBox buttonContainer = new HBox(settingsButton);

        getChildren().addAll(region, buttonContainer);

        buttonContainer.setAlignment(Pos.CENTER);

        settingsButton.getStyleClass().add("settings-button");
    }

    private static void hoverIn(FontIcon icon) {
        RotateTransition hoverIn = new RotateTransition(Duration.millis(300), icon);
        hoverIn.setToAngle(90);
        hoverIn.play();
    }

    private static void hoverOut(FontIcon icon) {
        RotateTransition hoverOut = new RotateTransition(Duration.millis(300), icon);
        hoverOut.setToAngle(0);
        hoverOut.play();
    }
}
