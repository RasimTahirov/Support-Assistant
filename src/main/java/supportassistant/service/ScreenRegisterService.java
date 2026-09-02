package supportassistant.service;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import javafx.stage.Stage;
import supportassistant.ui.window.alert.ErrorAlert;
import supportassistant.ui.window.alert.WarningAlert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import supportassistant.utils.I18n;

public class ScreenRegisterService {
    private static final Logger logger = LoggerFactory.getLogger(ScreenRegisterService.class);

    public void register(Stage stage) {
        try {
            GlobalScreen.registerNativeHook();
            GlobalScreen.addNativeKeyListener(new KeyboardRegisterService(stage));
        } catch (NativeHookException exception) {
            logger.warn("JNativeHook failed to connect {}", exception.getMessage(), exception);

            WarningAlert warning = new WarningAlert();
            warning.setContentText("Failed to enable hotkeys. You may need to grant the application permission in system settings");
            warning.showAndWait();
        }
    }

    public void unregister() {
        try {
            if (GlobalScreen.isNativeHookRegistered()) {
                GlobalScreen.unregisterNativeHook();
            }
        } catch (NativeHookException exception) {
            logger.error("JNativeHook failed to disconnect, application not closed {}", exception.getMessage(), exception);

            ErrorAlert error = new ErrorAlert();

            error.setHeaderText(I18n.get("message.error.header.unregister"));
            error.setContentText(I18n.get("message.error.content.unregister"));
            error.showAndWait();
        }
    }
}
