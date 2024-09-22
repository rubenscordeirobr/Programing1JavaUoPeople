package Models;

/**
 * The abstract class Model serves as a blueprint for creating different model types.
 * Each model must have an ID, a name, and a way to represent its view.
 */
public abstract class Model {

    /**
     * Returns the name of the model.
     *
     * @return the name of the model.
     */
    public abstract String getName();

    /**
     * Returns the unique identifier (ID) of the model.
     *
     * @return the ID of the model.
     */
    public abstract String getId();

    /**
     * Returns the view of the model. Optionally, additional details can be included.
     *
     * @param isIncludeDetails flag indicating whether to include details in the view.
     * @return the view of the model, with or without details based on the flag.
     */
    public String getView(boolean isIncludeDetails) {
        return this.getView(); // Default behavior is to call the abstract method getView.
    }

    /**
     * Abstract method to return the view of the model.
     * Subclasses should provide their own implementation.
     *
     * @return the view of the model.
     */
    public abstract String getView();
}
