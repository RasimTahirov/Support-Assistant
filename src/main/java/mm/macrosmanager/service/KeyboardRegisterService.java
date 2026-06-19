package mm.macrosmanager.service;

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import javafx.stage.Stage;

public class KeyboardRegisterService implements NativeKeyListener {
    private final Stage stage;

    public KeyboardRegisterService(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        if (e.getKeyCode() == NativeKeyEvent.VC_BACK_SLASH) {
            // VC_BACK_SLASH временно
            WindowService.open(stage);
        }
    }
}
