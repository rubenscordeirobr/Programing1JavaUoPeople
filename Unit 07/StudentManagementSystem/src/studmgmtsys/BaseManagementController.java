package studmgmtsys;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import studmgmtsys.models.*;
import studmgmtsys.utils.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

/**
 * Abstract base controller class for managing models in table views.
 * Provides common functionality such as editing and deleting records,
 * and displaying action buttons in a table cell.
 *
 * @param <TModel> The type of model that this management controller will handle. 
 *                 Must extend the {@link Model} class.
 */
public abstract class BaseManagementController<TModel extends Model> extends BaseController {

    /**
     * Returns the label for the edit button in the action table cell.
     * Subclasses can override this to change the label text.
     *
     * @return The label text for the edit button.
     */
    public String getEditButtonLabel() {
        return "Edit";
    }

    /**
     * Creates a table cell containing Edit and Delete buttons for each row of the table.
     * This cell will be used to perform edit and delete actions on the model represented by the row.
     *
     * @param param The table column to which this cell belongs.
     * @return A {@link TableCell} with Edit and Delete buttons.
     */
    public TableCell<TModel, Void> createActionsTableCell(final TableColumn<TModel, Void> param) {
        
        String editLabel = this.getEditButtonLabel();
        return new TableCell<>() {

            private final Button editButton = new Button(editLabel);
            private final Button deleteButton = new Button("Delete");
            private final HBox buttons = new HBox(editButton, deleteButton);

            {
                // Configure button spacing and event handlers
                buttons.setSpacing(10); // Space between buttons
                editButton.setOnAction(BaseManagementController.this::handleEdit);
                deleteButton.setOnAction(BaseManagementController.this::handlerDelete);
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(buttons); // Set HBox with buttons
                }
            }
        };
    }

    /**
     * Handles the Edit button click event by retrieving the model associated with the row 
     * and calling the {@link #editModel(TModel)} method.
     *
     * @param event The ActionEvent triggered by the Edit button.
     */
    public void handleEdit(ActionEvent event) {
        TModel model = getModelFromEvent(event);
        this.editModel(model);
    }

    /**
     * Handles the Delete button click event by showing a confirmation dialog,
     * and if confirmed, calling the {@link #removeModel(TModel)} method to delete the model.
     *
     * @param event The ActionEvent triggered by the Delete button.
     */
    public void handlerDelete(ActionEvent event) {

        // Confirmation message
        String message = "Are you sure you want to delete this record?";
        boolean confirm = AlertUtils.confirmDialog(message, "Attention");
        if (confirm) {
            // Delete the record
            TModel model = getModelFromEvent(event);
            this.removeModel(model);
        }
    }

    /**
     * Retrieves the model from the button click event by accessing the table row associated 
     * with the clicked button.
     *
     * @param event The ActionEvent triggered by the Edit/Delete button.
     * @return The model associated with the clicked row.
     */
    private TModel getModelFromEvent(ActionEvent event) {
        Button button = (Button) event.getSource();
        TableCell<TModel, Void> cell = (TableCell<TModel, Void>) button.getParent().getParent();
        return cell.getTableView().getItems().get(cell.getIndex());
    }

    /**
     * Opens a form window for editing or creating a model, and optionally sets it to modal.
     * 
     * @param fxmlFile   The path to the FXML file for the form window.
     * @param windowTitle The title of the form window.
     * @param isModal    Whether the form window should block interaction with the parent window (modal).
     * @param model      The model to be edited or created.
     */
    protected void openForm(String fxmlFile, String windowTitle, boolean isModal, TModel model) {
        try {

            // Load the FXML layout for the form
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle(windowTitle);
            stage.setScene(new Scene(root));

            // Set the model in the form controller
            BaseControllerForm<TModel> controller = fxmlLoader.getController();
            controller.setModel(model);

            // Set modality if necessary and display the form
            stage.initModality(Modality.APPLICATION_MODAL); // Block parent window if modal
            stage.showAndWait(); // Wait for the window to close before returning

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Abstract method that must be implemented by subclasses to handle model editing.
     *
     * @param model The model to be edited.
     */
    public abstract void editModel(TModel model);

    /**
     * Abstract method that must be implemented by subclasses to handle model deletion.
     *
     * @param model The model to be removed.
     */
    public abstract void removeModel(TModel model);
}
