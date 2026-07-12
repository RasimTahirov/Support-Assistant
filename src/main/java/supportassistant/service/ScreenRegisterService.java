package supportassistant.service;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import javafx.stage.Stage;
import supportassistant.ui.window.alert.ErrorWindow;
import supportassistant.ui.window.alert.WarningWindow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScreenRegisterService {
    private static final Logger logger = LoggerFactory.getLogger(ScreenRegisterService.class);

    public void register(Stage stage) {
        try {
            GlobalScreen.registerNativeHook();
            GlobalScreen.addNativeKeyListener(new KeyboardRegisterService(stage));
        } catch (NativeHookException exception) {
            logger.warn("JNativeHook failed to connect {}", exception.getMessage(), exception);
            WarningWindow.showWarning("""
                    Failed to enable hotkeys
                    You may need to grant the application permission in system settings
                    """);
        }
    }

    public void unregister() {
        try {
            if (GlobalScreen.isNativeHookRegistered()) {
                GlobalScreen.unregisterNativeHook();
            }
        } catch (NativeHookException exception) {
            logger.error("JNativeHook failed to disconnect, application not closed {}", exception.getMessage(), exception);
            ErrorWindow.showUnregisterError();
        }
    }
}
