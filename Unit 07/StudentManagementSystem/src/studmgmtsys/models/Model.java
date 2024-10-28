package studmgmtsys.models;

/**
 * The abstract class Model serves as a blueprint for creating different model
 * types.
 * Each model must have an ID, a name, and a way to represent its view.
 */
public abstract class Model {

    /**
     * Returns the unique identifier (ID) of the model.
     *
     * @return the ID of the model.
     */
    public abstract String getId();

    /**
     * Returns the name of the model.
     *
     * @return the name of the model.
     */
    public abstract String getName();

    /**
     * Returns a string representation of the model.
     *
     * @return a string representation of the model.
     */
    @Override
    public String toString() {

        if (getName() == null || getName().isEmpty()) {
            return Model.class.getName() + " ID: " + getId();
        }
        // Return the name of the model
        return getName();
    }

}
