package managers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import data.DataStorage;
import models.*;
import utils.*;

/**
 * The ProfessorManager class extends CrudManager and provides CRUD operations
 * for managing Professor entities, including creating, updating, and viewing professors.
 */
public class ProfessorManager extends CrudManager<Professor> {

    /**
     * Returns the description of the model managed by this class.
     * 
     * @return a String representing "Professor".
     */
    @Override
    public String getModelDescription() {
        return "Professor";
    }

    /**
     * Retrieves the list of all professors from the data storage.
     * 
     * @return an ArrayList of Professor objects.
     */
    @Override
    public ArrayList<Professor> getModels() {
        return DataStorage.getProfessors();
    }

    /**
     * Returns the header view for displaying professors.
     * 
     * @return a String representing the header for the Professor view.
     */
    @Override
    public String getViewHeader() {
        return Professor.getViewHeader();
    }

    /**
     * Retrieves the fields required for creating or updating a Professor.
     * 
     * @param isUpdating indicates whether the operation is an update.
     * @return an ArrayList of field names for the Professor model.
     */
    @Override
    public ArrayList<String> getFields(boolean isUpdating) {
        return new ArrayList<>(Arrays.asList("Name", "Birth Date"));
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
     * Creates a new Professor with the provided field values and related department.
     * 
     * @param fieldValues a HashMap containing the field names and their corresponding values.
     * @param relatedModels a HashMap of related models (including Department).
     */
    @Override
    public void create(HashMap<String, String> fieldValues, HashMap<String, Model> relatedModels) {
        String name = fieldValues.get("Name");
        String birthDate = fieldValues.get("Birth Date");

        Department department = (Department) relatedModels.get("Department");

        Professor professor = new Professor(name, birthDate, department);
        DataStorage.addProfessor(professor);
        PrintUtils.printSuccess("Professor " + name + " added successfully");
    }

    /**
     * Updates the fields of an existing Professor, including the name and birth date.
     * 
     * @param professor the Professor object to update.
     * @param fieldValues a HashMap containing the updated field values.
     */
    @Override
    public void update(Professor professor, HashMap<String, String> fieldValues) {
        String name = fieldValues.get("Name");
        String birthDateString = fieldValues.get("Birth Date");

        LocalDate birthDate = DateUtils.tryDateParser(birthDateString, professor.getBirthDate(), true);

        professor.setName(name);
        professor.setBirthDate(birthDate);

        PrintUtils.printSuccess("Professor " + name + " updated successfully");
    }

    /**
     * Finds a Professor by their unique ID.
     * 
     * @param id the ID of the Professor.
     * @return the Professor object if found, or null if not found.
     */
    @Override
    public Professor findById(String id) {
        return DataStorage.getProfessorById(id);
    }
}
