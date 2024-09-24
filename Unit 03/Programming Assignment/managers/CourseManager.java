package managers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import data.DataStorage;
import models.*;
import utils.*;

/**
 * The CourseManager class extends CrudManager and provides CRUD operations
 * for managing Course entities, including creating, updating, and viewing courses.
 */
public class CourseManager extends CrudManager<Course> {

    /**
     * Returns the description of the model managed by this class.
     * 
     * @return a String representing "Course".
     */
    @Override
    public String getModelDescription() {
        return "Course";
    }

    /**
     * Retrieves the list of all courses from the data storage.
     * 
     * @return an ArrayList of Course objects.
     */
    @Override
    public ArrayList<Course> getModels() {
        return DataStorage.getCourses();
    }

    /**
     * Returns the header view for displaying courses.
     * 
     * @return a String representing the header for the Course view.
     */
    @Override
    public String getViewHeader() {
        return Course.getViewHeader();
    }

    /**
     * Retrieves the fields required for creating or updating a Course.
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
     * Prompts the user to choose a related model (in this case, a Department)
     * and returns it as part of the related models map.
     * 
     * @return a HashMap of related models with Department as a key.
     */
    @Override
    public HashMap<String, Model> getRelatedModels() {
        HashMap<String, Model> relatedModels = new HashMap<>();
        Model department = this.chooseModel("Department", DataStorage.getDepartments());
        relatedModels.put("Department", department);
        return relatedModels;
    }

    /**
     * Creates a new Course with the provided field values and related department.
     * 
     * @param fieldValues a HashMap containing the field names and their corresponding values.
     * @param relatedModels a HashMap of related models (including Department).
     */
    @Override
    public void create(HashMap<String, String> fieldValues, HashMap<String, Model> relatedModels) {
        String id = fieldValues.get("Id");
        String name = fieldValues.get("Name");
        String description = fieldValues.get("Description");
        Department department = (Department) relatedModels.get("Department");

        Course course = new Course(id, name, description, department);
        DataStorage.addCourse(course);
        PrintUtils.printSuccess("Course " + name + " added successfully");
    }

    /**
     * Updates the fields of an existing Course, including the name and description.
     * 
     * @param model the Course object to update.
     * @param fieldValues a HashMap containing the updated field values.
     */
    @Override
    public void update(Course model, HashMap<String, String> fieldValues) {
        String name = fieldValues.get("Name");
        String description = fieldValues.get("Description");
        model.setName(name);
        model.setDescription(description);

        PrintUtils.printSuccess("Course " + name + " updated successfully");
    }

    /**
     * Finds a Course by its unique ID.
     * 
     * @param id the ID of the Course.
     * @return the Course object if found, or null if not found.
     */
    @Override
    public Course findById(String id) {
        return DataStorage.getCourseById(id);
    }
}
