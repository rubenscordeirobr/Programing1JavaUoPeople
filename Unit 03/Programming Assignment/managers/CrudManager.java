package managers;

import java.util.ArrayList;
import java.util.HashMap;
import models.*;
import utils.*;

/**
 * The CrudManager class provides a generic structure for managing CRUD operations 
 * (Create, Read, Update, Delete) on any model that extends the Model class.
 * 
 * @param <TModel> the type of model managed by this class.
 */
public abstract class CrudManager<TModel extends Model> {

    /**
     * Returns the description of the model managed by this class.
     * 
     * @return a String representing the description of the model.
     */
    public abstract String getModelDescription();

    /**
     * Retrieves the list of all models managed by this class.
     * 
     * @return an ArrayList of models.
     */
    public abstract ArrayList<TModel> getModels();

    /**
     * Returns a formatted header for displaying model data.
     * 
     * @return a String representing the header view of the model.
     */
    public abstract String getViewHeader();

    /**
     * Retrieves the list of fields required for creating or updating a model.
     * 
     * @param isUpdating indicates whether the operation is an update.
     * @return an ArrayList of Strings representing field names.
     */
    public abstract ArrayList<String> getFields(boolean isUpdating);

    /**
     * Finds a model by its unique ID.
     * 
     * @param id the ID of the model.
     * @return the found model or null if no model with the given ID exists.
     */
    public abstract TModel findById(String id);

    /**
     * Creates a new model instance with the provided field values and related models.
     * 
     * @param fieldValues a HashMap containing field names and their values.
     * @param relatedModels a HashMap of related models.
     */
    public abstract void create(HashMap<String, String> fieldValues, HashMap<String, Model> relatedModels);

    /**
     * Updates an existing model with new field values.
     * 
     * @param model the model to update.
     * @param fieldValues a HashMap of updated field values.
     */
    public abstract void update(TModel model, HashMap<String, String> fieldValues);

    /**
     * Displays a management menu for CRUD operations on the model, along with additional options 
     * for specific cases like student management.
     */
    public void showMenu() {

        String modelDescription = this.getModelDescription();

        System.out.println("----------------------------------------------------------");
        System.out.println("Welcome to the " + modelDescription + " Management");

        System.out.println("0. Back to Main Menu");
        System.out.println("1. Add " + modelDescription);
        System.out.println("2. Update " + modelDescription);
        System.out.println("3. View " + modelDescription + "s");
        System.out.println("4. View " + modelDescription + "s with Details");

        // Check for specific StudentManager options
        if (this instanceof StudentManager) {
            System.out.println("5. Enroll Student in Course");
            System.out.println("6. Set Grade for Student in Course");
            System.out.println("7. Delete " + modelDescription);
        }

        int option = InputUtils.getInt("Enter an option: ");

        switch (option) {
            case 0:
                return;
            case 1:
                add();
                break;
            case 2:
                update();
                break;
            case 3:
                view(false);
                break;
            case 4:
                view(true);
                break;
            case 5:
                if (this instanceof StudentManager) {
                    ((StudentManager) this).enrollStudentInCourse();
                }
                break;
            case 6:
                if (this instanceof StudentManager) {
                    ((StudentManager) this).setStudentGrade();
                }
                break;
            case 7:
                if (this instanceof StudentManager) {
                    ((StudentManager) this).delete();
                }
                break;
            default:
                PrintUtils.printFail("Invalid option, please try again.");
                break;
        }

        showMenu(); // Display the menu again after performing an action
    }

    /**
     * Displays a list of models, either with or without details, depending on the parameter.
     * 
     * @param isIncludeDetails flag indicating whether to include additional details in the view.
     */
    public void view(boolean isIncludeDetails) {

        // Get the list of models
        ArrayList<TModel> models = this.getModels();

        // Check if any models exist
        if (models.size() == 0) {
            PrintUtils.printFail("No " + this.getModelDescription() + " found");
            return;
        }

        // Display the models
        
        System.out.println("View " + this.getModelDescription());
        String headerView = this.getViewHeader();
        System.out.println(headerView);
        System.out.println(StringUtils.repeat("-", headerView.length()));

        for (TModel model : models) {
            String view = model.getView(isIncludeDetails);
            PrintUtils.println(view);
        }

        // print total number of models
        System.out.println("Total " + this.getModelDescription() + "s: " + models.size());


    }

    /**
     * Prompts the user for input and creates a new model instance.
     */
    public void add() {

        PrintUtils.println("Adding " + this.getModelDescription());
        PrintUtils.println("Enter the following information:");

        HashMap<String, Model> relatedModels = this.getRelatedModels();

        ArrayList<String> fields = this.getFields(false);
        HashMap<String, String> fieldValues = new HashMap<>();

        for (String field : fields) {
            String value = InputUtils.getString(field + ": ", true);
            fieldValues.put(field, value);
        }
        this.create(fieldValues, relatedModels);
    }

    /**
     * Prompts the user to select a model by its ID and update its fields.
     */
    public void update() {

        this.view(false);
        PrintUtils.println("Choose the ID of the " + this.getModelDescription() + " to update");
        String id = InputUtils.getString("Enter the ID: ", true);

        TModel model = this.findById(id);
        if (model == null) {
            PrintUtils.printFail("Invalid " + this.getModelDescription() + " ID");
            return;
        }

        PrintUtils.println("Updating " + model.getName());
        ArrayList<String> fields = this.getFields(true);
        HashMap<String, String> fieldValues = new HashMap<>();

        for (String field : fields) {
            String value = InputUtils.getString(field + ": ", true);
            fieldValues.put(field, value);
        }
        this.update(model, fieldValues);
    }

    /**
     * Returns related models, typically used for linking models (e.g., foreign key relationships).
     * This method can be overridden in subclasses.
     * 
     * @return a HashMap of related models.
     */
    public HashMap<String, Model> getRelatedModels() {
        return new HashMap<>();
    }

    /**
     * Prompts the user to choose a model from a list and returns the selected model.
     * 
     * @param description the description of the model to choose (e.g., "Department").
     * @param models the list of available models to choose from.
     * @param <T> the type of model.
     * @return the selected model.
     */
    public <T extends Model> T chooseModel(String description, ArrayList<T> models) {

        System.out.println("Choose a " + description);

        String header = StringUtils.padRight("ID", 10) + " | " + StringUtils.padRight("Name", 50);
        System.out.println(header);

        for (T model : models) {
            String line = StringUtils.padRight(model.getId(), 10) + " | " + StringUtils.padRight(model.getName(), 50);
            System.out.println(line);
        }

        String id = InputUtils.getString("Enter the ID: ", true);

        T model = models.stream().filter(m -> m.getId().equals(id)).findFirst().orElse(null);

        if (model == null) {
            PrintUtils.printFail("Invalid " + description + " ID");
            return chooseModel(description, models); // Re-prompt if invalid
        }
        return model;
    }
}
