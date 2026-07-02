package supportassistant.ui.window;

import javafx.application.Platform;
import javafx.scene.control.Alert;

import static javafx.scene.control.Alert.AlertType.ERROR;

public class ErrorWindow {
    public static void showInitDatabaseError() {
        Alert alert = new Alert(ERROR);

        alert.setHeaderText("Не удалось запустить приложение.");
        alert.setContentText("Произошла ошибка при инициализации базы данных. Приложение будет закрыто.");

        alert.showAndWait();
        Platform.exit();
        System.exit(0);
    }

    public static void showUnregisterError() {
        Alert alert = new Alert(ERROR);

        alert.setHeaderText("Не удалось завершить процесс");
        alert.setContentText("""
                Не удалось корректно завершить работу приложения. Закройте его вручную через Диспетчер задач.
                """);

        alert.showAndWait();
    }
}
