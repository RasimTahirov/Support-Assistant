package mm.macrosmanager.service;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import javafx.stage.Stage;

public class ScreenRegister implements NativeKeyListener {
    public void register(Stage stage) {
        try {
            GlobalScreen.registerNativeHook();
            GlobalScreen.addNativeKeyListener(new KeyboardRegister(stage));
        } catch (NativeHookException e) {
            System.out.println(e.getMessage());
        }
    }
}
