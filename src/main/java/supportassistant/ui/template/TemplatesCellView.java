package supportassistant.ui.template;

import javafx.geometry.Pos;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.remixicon.RemixiconAL;
import supportassistant.entity.Template;
import supportassistant.exception.TemplateException;
import supportassistant.model.TemplateModel;
import supportassistant.ui.button.Button;
import supportassistant.ui.window.TemplateWindow;
import supportassistant.ui.window.alert.ConfirmationAlert;
import supportassistant.ui.window.alert.WarningAlert;

import java.util.Optional;

import static supportassistant.utils.I18n.*;

public class TemplatesCellView extends ListCell<Template> {
    private final FontIcon editIcon = new FontIcon(RemixiconAL.EDIT_FILL);
    private final FontIcon deleteIcon = new FontIcon(RemixiconAL.DELETE_BIN_7_LINE);

    private final Label templateTitle = new Label();
    private final Button deleteButton = new Button();
    private final Button updateButton = new Button();
    private final Region spacer = new Region();
    private final HBox container = new HBox(templateTitle, spacer, updateButton, deleteButton);

    private Template template;

    public TemplatesCellView(TemplateModel model) {
        editIcon.setIconSize(15);
        updateButton.setGraphic(editIcon);

        deleteIcon.setIconSize(15);
        deleteButton.setGraphic(deleteIcon);

        updateButton.setOnAction(e -> {
            new TemplateWindow(model, template.getId(), template.getTitle(), template.getTemplate());
        });

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
                        warning.setContentText(get("message.warning.content.delete"));
                        warning.showAndWait();
                    }
                }

            }
        });

        container.setAlignment(Pos.CENTER_LEFT);
        HBox.setHgrow(spacer, Priority.ALWAYS);

        templateTitle.getStyleClass().add("cell-title");
        deleteButton.getStyleClass().add("delete-button");
        updateButton.getStyleClass().add("update-button");
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
