package supportassistant.service;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import javafx.stage.Stage;
import supportassistant.ui.window.ErrorWindow;
import supportassistant.ui.window.WarningWindow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScreenRegisterService {
    private static final Logger logger = LoggerFactory.getLogger(ScreenRegisterService.class);

    public void register(Stage stage) {
        try {
            GlobalScreen.registerNativeHook();
            GlobalScreen.addNativeKeyListener(new KeyboardRegisterService(stage));
        } catch (NativeHookException exception) {
            logger.warn("JNativeHook не подключился {}", exception.getMessage(), exception);
            WarningWindow.showWarning("""
                    Не удалось включить горячие клавиши
                    Возможно, нужно выдать приложению разрешение в настройках системы
                    """);
        }
    }

    public void unregister() {
        try {
            if (GlobalScreen.isNativeHookRegistered()) {
                GlobalScreen.unregisterNativeHook();
            }
        } catch (NativeHookException exception) {
            logger.error("JNativeHook не отключился, приложение не закрыто {}", exception.getMessage(), exception);
            ErrorWindow.showUnregisterError();
        }
    }
}
