package supportassistant.ui.template;

import javafx.geometry.Pos;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import supportassistant.entity.Template;
import supportassistant.exception.TemplateException;
import supportassistant.model.TemplateModel;
import supportassistant.ui.button.Button;
import supportassistant.ui.window.alert.ConfirmationAlert;
import supportassistant.ui.window.alert.WarningAlert;

import java.util.Optional;

import static supportassistant.utils.I18n.*;

public class TemplatesCellView extends ListCell<Template> {
    private final Label templateTitle = new Label();
    private final Button deleteButton = new Button(get("text.delete_template"));
    private final Region spacer = new Region();
    private final HBox container = new HBox(templateTitle, spacer, deleteButton);

    private Template template;

    public TemplatesCellView(TemplateModel model) {
        deleteButton.setOnAction(e -> {
            if (template != null) {
                ConfirmationAlert confirmation = new ConfirmationAlert();

                confirmation.setTitle(get("message.confirmation.title.delete"));
                confirmation.setContentText(get("message.confirmation.content.delete") + " " + template.getTitle() + "?");

                Optional<ButtonType> result = confirmation.showAndWait();

                if (result.isPresent() && result.get() == ButtonType.OK) {
                    try {
                        model.delete(template);
                    } catch (TemplateException exception) {
                        WarningAlert warning = new WarningAlert();
                        warning.setContentText(get("message.warning.content.delete")); //sada
                        warning.showAndWait();
                    }
                }

            }
        });

        container.setAlignment(Pos.CENTER_LEFT);
        HBox.setHgrow(spacer, Priority.ALWAYS);

        templateTitle.getStyleClass().add("cell-title");
        deleteButton.getStyleClass().add("delete-button");
        container.getStyleClass().add("template-cell");
    }

    @Override
    protected void updateItem(Template template, boolean empty) {
        super.updateItem(template, empty);
        this.template = template;

        if (template == null || empty) {
            setGraphic(null);
            return;
        }

        templateTitle.setText(template.getTitle());
        setGraphic(container);
    }
}
