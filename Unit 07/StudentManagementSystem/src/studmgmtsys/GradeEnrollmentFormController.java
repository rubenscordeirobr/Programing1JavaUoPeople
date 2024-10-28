package studmgmtsys;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import studmgmtsys.models.StudentEnrollment;
import studmgmtsys.utils.AlertUtils;
import studmgmtsys.utils.Utils;

/**
 * Controller for the Grade Enrollment form.
 * Grading a student enrollment.
 */
public class GradeEnrollmentFormController extends BaseControllerForm<StudentEnrollment> {

    @FXML
    private TextField studentField;

    @FXML
    private TextField courseField;

    @FXML
    private TextField gradeField;

    /**
     * Called when the UI is shown. Populates the fields with student and course information
     * and sets the grade if it has already been assigned.
     */
    @Override
    protected void onUIShown() {
        StudentEnrollment enrollment = this.getModel();

        // Display the student's name and the course name
        this.studentField.setText(enrollment.getStudent().getName());
        this.courseField.setText(enrollment.getCourse().getName());

        // If the enrollment is already graded, display the grade
        if (enrollment.isGraded()) {
            this.gradeField.setText(String.valueOf(enrollment.getGrade()));
        }

        // Set the student and course fields to read-only
        this.studentField.setEditable(false);
        this.courseField.setEditable(false);
    }

    /**
     * Handles the submit button click. Validates the input and sets the grade for the enrollment.
     */
    @FXML
    private void handleSubmit() {
        // Check if the grade field is empty
        if (this.gradeField.getText().isEmpty()) {
            AlertUtils.showErrorAlert("Please enter a grade.");
            return;
        }

        // Parse the grade input and validate it
        int grade = Utils.tryParseInt(this.gradeField.getText());
        if (grade < 0 || grade > 100) {
            AlertUtils.showErrorAlert("The grade must be between 0 and 100.");
            return;
        }

        // Set the grade for the enrollment
        StudentEnrollment enrollment = this.getModel();
        enrollment.setGrade(grade);
        AlertUtils.showSuccessAlert("The course has been graded successfully.");
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
        Stage stage = (Stage) studentField.getScene().getWindow();
        stage.close();
    }
}
