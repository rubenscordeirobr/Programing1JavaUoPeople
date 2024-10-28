package studmgmtsys;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import studmgmtsys.data.DataStorage;
import studmgmtsys.models.Course;
import studmgmtsys.models.Student;
import studmgmtsys.models.StudentEnrollment;
import studmgmtsys.utils.AlertUtils;

/**
 * Controller for the New Enrollment form.
 * This class handles the creation of new student enrollments.
 */
public class NewEnrollmentFormController extends BaseControllerForm<StudentEnrollment> {

    @FXML
    private ChoiceBox<Student> studentChoiceBox;

    @FXML
    private ChoiceBox<Course> courseChoiceBox;

    /**
     * Initializes the form by populating the ChoiceBoxes with students and courses.
     */
    @Override
    protected void initialize() {
        super.initialize();
        // Populate the student and course choice boxes with data from DataStorage
        studentChoiceBox.getItems().addAll(DataStorage.getStudents());
        courseChoiceBox.getItems().addAll(DataStorage.getCourses());
    }

    /**
     * Called when the UI is shown. Currently, no specific actions are performed here.
     */
    @Override
    protected void onUIShown() {
        // Any initialization that needs to happen when the UI is shown can be added here
    }

    /**
     * Handles the submit button click. Validates the selected student and course,
     * creates a new enrollment, and saves it to DataStorage.
     */
    @FXML
    private void handleSubmit() {
        // Get the selected values from the choice boxes
        Student student = studentChoiceBox.getValue();
        Course course = courseChoiceBox.getValue();

        // Validate the selected student
        if (student == null) {
            AlertUtils.showErrorAlert("The Student is required.");
            return;
        }

        // Validate the selected course
        if (course == null) {
            AlertUtils.showErrorAlert("The Course is required.");
            return;
        }

        // Check if the student is already enrolled in the course
        if (DataStorage.getEnrollment(student, course) != null) {
            AlertUtils.showErrorAlert(
                    "The Student " + student.getName() + " is already enrolled in the course " + course.getName());
            return;
        }

        // Create a new StudentEnrollment object and add it to DataStorage
        StudentEnrollment enrollment = new StudentEnrollment(student, course);
        DataStorage.addEnrollment(enrollment);
        AlertUtils.showSuccessAlert("The course has been added successfully.");
        closeForm();
    }

    /**
     * Handles the cancel button click. Closes the form without making any changes.
     */
    @FXML
    private void handleCancel() {
        closeForm();
    }

    /**
     * Closes the form window.
     */
    private void closeForm() {
        Stage stage = (Stage) studentChoiceBox.getScene().getWindow();
        stage.close();
    }
}
