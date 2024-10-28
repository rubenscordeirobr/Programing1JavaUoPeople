package studmgmtsys;

import javafx.application.Platform;
import javafx.fxml.FXML;
import studmgmtsys.models.Model;

/**
 * Abstract base controller class for forms in the Student Management System application.
 * This class extends {@link BaseController} and adds functionality specific to handling
 * models in form-based interfaces.
 *
 * @param <TModel> The type of model that this form controller will handle. 
 *                 Must extend the {@link Model} class.
 */
public abstract class BaseControllerForm<TModel extends Model> extends BaseController {

    // Generic model to hold form data
    private TModel model;

    /**
     * Sets the model associated with this form.
     * 
     * @param model The model to be set.
     */
    public void setModel(TModel model) {
        this.model = model;
    }

    /**
     * Retrieves the current model associated with this form.
     * 
     * @return The model associated with this form.
     */
    public TModel getModel() {
        return model;
    }

    /**
     * Checks if the current form is working with a new (unsaved) model.
     * 
     * @return {@code true} if the model is {@code null} (indicating a new model),
     *         {@code false} otherwise.
     */
    public boolean getIsNewModel() {
        return model == null;
    }

    /**
     * Initializes the form and prepares the UI.
     * This method is automatically called by JavaFX after the FXML file is loaded.
     */
    @FXML
    protected void initialize() {
        // Use Platform.runLater to ensure this runs after the UI is fully displayed
        Platform.runLater(this::onUIShown);
    }

    /**
     * Abstract method to be implemented by subclasses, defining what should happen 
     * when the UI is first shown. Typically used to populate the form with data.
     */
    protected abstract void onUIShown();
}
