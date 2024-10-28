package studmgmtsys;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import studmgmtsys.data.DataStorage;
import studmgmtsys.models.Student;
import studmgmtsys.utils.AlertUtils;

/**
 * Controller for the Student form.
 * This class handles the creation and editing of student records.
 */
public class StudentFormController extends BaseControllerForm<Student> {
    @FXML
    private TextField idField;

    @FXML
    private TextField nameField;

    /**
     * Called when the UI is shown. Populates the fields with student information
     * if editing an existing student.
     */
    @Override
    protected void onUIShown() {
        Student student = this.getModel();
        // If the student is not null, it means we are editing an existing student
        if (student != null) {
            idField.setEditable(false); // Prevent editing of the ID field

            // Populate the fields with the student's existing data
            idField.setText(student.getId());
            nameField.setText(student.getName());
        }
    }

    /**
     * Handles the submit button click. Validates the input fields and either adds
     * a new student or updates an existing student, then closes the form.
     */
    @FXML
    private void handleSubmit() {
        // Get the values from the text fields
        String id = idField.getText();
        String name = nameField.getText();

        // Validate input fields
        if (id.isEmpty()) {
            AlertUtils.showErrorAlert("The field ID is required.");
            return;
        }

        if (name.isEmpty()) {
            AlertUtils.showErrorAlert("The field Name is required.");
            return;
        }

        Student student = this.getModel();
        boolean isNewStudent = student == null;

        // If it's a new student, check if the ID already exists
        if (isNewStudent) {
            if (DataStorage.getStudentById(id) != null) {
                AlertUtils.showErrorAlert("The student ID already exists.");
                return;
            }

            // Create a new Student object and add it to DataStorage
            student = new Student(id, name);
            DataStorage.addStudent(student);
            AlertUtils.showSuccessAlert("The student has been added successfully.");

        } else {
            // Update the existing student object
            student.setName(name);
            AlertUtils.showSuccessAlert("The student has been updated successfully.");
        }

        closeForm(); // Close the form after processing
    }

    /**
     * Handles the cancel button click. Closes the form without making changes.
     */
    @FXML
    private void handleCancel() {
        closeForm();
    }

    /**
     * Closes the form window.
     */
    private void closeForm() {
        Stage stage = (Stage) idField.getScene().getWindow();
        stage.close();
    }
}
