package supportassistant.service;

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicBoolean;

public class KeyboardRegisterService implements NativeKeyListener {
    private final Stage stage;
    private final AtomicBoolean isKeyPressed = new AtomicBoolean(false);

    public KeyboardRegisterService(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        if (e.getKeyCode() == NativeKeyEvent.VC_F8) {
            if (isKeyPressed.compareAndSet(false, true)) {
                WindowService.open(stage);
            }
        }
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {
        if (e.getKeyCode() == NativeKeyEvent.VC_F8) {
            isKeyPressed.set(false);
        }
    }
}