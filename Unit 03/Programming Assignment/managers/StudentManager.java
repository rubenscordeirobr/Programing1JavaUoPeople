package managers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import data.DataStorage;
import models.*;
import utils.*;

/**
 * The StudentManager class extends CrudManager and provides CRUD operations
 * for managing Student entities, including enrolling students in courses and setting grades.
 */
public class StudentManager extends CrudManager<Student> {

    /**
     * Returns the description of the model managed by this class.
     * 
     * @return a String representing "Student".
     */
    @Override
    public String getModelDescription() {
        return "Student";
    }

    /**
     * Retrieves the list of all students from the data storage.
     * 
     * @return an ArrayList of Student objects.
     */
    @Override
    public ArrayList<Student> getModels() {
        return DataStorage.getStudents();
    }

    /**
     * Returns the header view for displaying students.
     * 
     * @return a String representing the header for the Student view.
     */
    @Override
    public String getViewHeader() {
        return Student.getViewHeader();
    }

    /**
     * Retrieves the fields required for creating or updating a Student.
     * 
     * @param isUpdating indicates whether the operation is an update.
     * @return an ArrayList of field names.
     */
    @Override
    public ArrayList<String> getFields(boolean isUpdating) {
        return new ArrayList<>(Arrays.asList("Name", "Birth Date"));
    }

    /**
     * Creates a new Student with the provided field values and adds them to the data storage.
     * 
     * @param fieldValues a HashMap containing the field names and their corresponding values.
     * @param relatedModels a HashMap of related models (not used in this implementation).
     */
    @Override
    public void create(HashMap<String, String> fieldValues, HashMap<String, Model> relatedModels) {
        String name = fieldValues.get("Name");
        String birthDate = fieldValues.get("Birth Date");

        Student student = new Student(name, birthDate);
        DataStorage.addStudent(student);
        PrintUtils.printSuccess("Student " + name + " added successfully");
    }

    /**
     * Updates the fields of an existing Student, including name and birth date.
     * 
     * @param model the Student object to update.
     * @param fieldValues a HashMap containing the updated field values.
     */
    @Override
    public void update(Student model, HashMap<String, String> fieldValues) {
        String name = fieldValues.get("Name");
        String birthDateString = fieldValues.get("Birth Date");
        LocalDate birthDate = DateUtils.tryDateParser(birthDateString, model.getBirthDate(), true);
        model.setName(name);
        model.setBirthDate(birthDate);
        PrintUtils.printSuccess("Student " + name + " updated successfully");
    }

    /**
     * Finds a Student by their unique ID.
     * 
     * @param id the ID of the Student.
     * @return the Student object if found, or null if not found.
     */
    @Override
    public Student findById(String id) {
        return DataStorage.getStudentById(id);
    }

    /**
     * Enrolls a student in a course by selecting a student, course, and professor.
     * Checks if the student is already enrolled before proceeding.
     */
    public void enrollStudentInCourse() {
        this.view(false);

        Student student = this.chooseModel("Student", DataStorage.getStudents());
        Course course = this.chooseModel("Course", DataStorage.getCourses());

        if (student.isEnrolled(course)) {
            PrintUtils.printFail("Student is already enrolled in this course");
            return;
        }

        Professor professor = this.chooseModel("Professor", DataStorage.getProfessors());
        LocalDate enrollmentDate = LocalDate.now();
        student.enroll(course, professor, enrollmentDate);
        PrintUtils.printSuccess("Student enrolled in " + course.getName() + " successfully");
    }

    /**
     * Sets the grade for a student in a selected course.
     * 
     * Checks if the student is enrolled in the course before allowing the grade to be set.
     */
    public void setStudentGrade() {
        this.view(false);

        Student student = this.chooseModel("Student", DataStorage.getStudents());
        Course course = this.chooseModel("Course", DataStorage.getCourses());

        if (!student.isEnrolled(course)) {
            PrintUtils.printFail("Student is not enrolled in this course");
            return;
        }

        double gradeScale = InputUtils.getDouble("Enter the grade scale (0 to 100): ", false);
        student.setGradeScale(course, gradeScale);
        PrintUtils.printSuccess("Grade set successfully for " + student.getName() + " in " + course.getName());
    }

    /**
     * Deletes a student from the data storage after confirming with the user.
     */
    public void delete() {
        PrintUtils.println("Deleting Student");

        this.view(false);
        PrintUtils.println("Choose the ID of the " + this.getModelDescription() + " to delete");
        String id = InputUtils.getString("Enter the ID: ", false);

        Student model = this.findById(id);
        if (model == null) {
            PrintUtils.printFail("Invalid " + this.getModelDescription() + " ID");
            return;
        }

        PrintUtils.printPrompt("Are you sure you want to delete " + model.getName() + "? ");
        String confirm = InputUtils.getString("Enter Y to confirm or any key to cancel: ", false);
        if (confirm.equalsIgnoreCase("Y")) {
            DataStorage.removeStudent(model);
            PrintUtils.printSuccess(this.getModelDescription() + " deleted successfully");
        } else {
            PrintUtils.printFail("Delete operation cancelled");
        }
    }
}
