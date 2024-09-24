package managers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import data.DataStorage;
import models.*;
import utils.*;

/**
 * The DepartmentManager class extends the CrudManager class and provides 
 * implementation for managing CRUD operations on Department objects.
 */
public class DepartmentManager extends CrudManager<Department> {

    /**
     * Returns the description of the model managed by this class.
     * 
     * @return a String representing the description "Department".
     */
    @Override
    public String getModelDescription() {
        return "Department";
    }

    /**
     * Retrieves the list of all Department models from the data storage.
     * 
     * @return an ArrayList of Department models.
     */
    @Override
    public ArrayList<Department> getModels() {
        return DataStorage.getDepartments();
    }

    /**
     * Returns the header view for displaying Department models.
     * 
     * @return a String representing the header for Department view.
     */
    @Override
    public String getViewHeader() {
        return Department.getViewHeader();
    }

    /**
     * Retrieves the fields required for creating or updating a Department.
     * When creating, the "Id" field is included, but it is omitted during updates.
     * 
     * @param isUpdating indicates whether the operation is an update.
     * @return an ArrayList of field names.
     */
    @Override
    public ArrayList<String> getFields(boolean isUpdating) {
        ArrayList<String> fields = new ArrayList<>(Arrays.asList("Name", "Description"));
        if (!isUpdating) {
            fields.add(0, "Id"); // Add "Id" field for creation
        }
        return fields;
    }

    /**
     * Creates a new Department with the provided field values and adds it to the data storage.
     * 
     * @param fieldValues a HashMap containing field names and their corresponding values.
     * @param relatedModels a HashMap of related models (not used in this implementation).
     */
    @Override
    public void create(HashMap<String, String> fieldValues, HashMap<String, Model> relatedModels) {
        String id = fieldValues.get("Id");
        String name = fieldValues.get("Name");
        String description = fieldValues.get("Description");

        Department department = new Department(id, name, description);
        DataStorage.addDepartment(department);
        PrintUtils.printSuccess("Department " + name + " added successfully");
    }

    /**
     * Updates the fields of an existing Department.
     * 
     * @param department the Department object to update.
     * @param fieldValues a HashMap containing the updated field values.
     */
    @Override
    public void update(Department department, HashMap<String, String> fieldValues) {
        String name = fieldValues.get("Name");
        String description = fieldValues.get("Description");

        department.setName(name);
        department.setDescription(description);
        PrintUtils.printSuccess("Department " + name + " updated successfully");
    }

    /**
     * Finds a Department by its unique ID.
     * 
     * @param id the ID of the Department.
     * @return the Department object if found, or null if not found.
     */
    @Override
    public Department findById(String id) {
        return DataStorage.getDepartmentById(id);
    }
}
