package mm.macrosmanager.service;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import javafx.stage.Stage;
import mm.macrosmanager.ui.window.ErrorWindow;
import mm.macrosmanager.ui.window.WarningWindow;

public class ScreenRegisterService {
    public void register(Stage stage) {
        try {
            GlobalScreen.registerNativeHook();
            GlobalScreen.addNativeKeyListener(new KeyboardRegisterService(stage));
        } catch (NativeHookException exception) {
            System.out.println("Нет подключение к JNativeHook");
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
            System.out.println("Не закрылся JNativeHook");
            ErrorWindow.showUnregisterError();
        }
    }
}
