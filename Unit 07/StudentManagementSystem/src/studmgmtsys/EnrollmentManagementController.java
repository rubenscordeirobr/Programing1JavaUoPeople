package studmgmtsys;

import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import studmgmtsys.data.DataStorage;
import studmgmtsys.models.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

/**
 * Controller class for managing the Enrollment Management interface.
 * Extends {@link BaseManagementController} to handle student enrollment actions 
 * such as adding, grading, and removing enrollments.
 */
public class EnrollmentManagementController extends BaseManagementController<StudentEnrollment> {

    @FXML
    private VBox enrollmentManagement;

    @FXML
    private TableView<StudentEnrollment> enrollmentTable;

    @FXML
    private TableColumn<StudentEnrollment, String> studentColumn;

    @FXML
    private TableColumn<StudentEnrollment, String> courseColumn;

    @FXML
    private TableColumn<StudentEnrollment, String> gradeColumn;

    @FXML
    private TableColumn<StudentEnrollment, Void> actionColumn;

    // Observable list to hold the enrollments data for the table view
    private ObservableList<StudentEnrollment> enrollments;

    /**
     * Initializes the enrollment management screen.
     * Sets up the table columns and loads the enrollment data from the DataStorage.
     */
    @FXML
    public void initialize() {
        // Set cell value factories for the enrollment columns
        studentColumn.setCellValueFactory(new PropertyValueFactory<>("student"));
        courseColumn.setCellValueFactory(new PropertyValueFactory<>("course"));
        gradeColumn.setCellValueFactory(new PropertyValueFactory<>("gradeDescription"));

        // Set cell factory for the action column to generate Grade/Edit/Delete buttons
        Callback<TableColumn<StudentEnrollment, Void>, TableCell<StudentEnrollment, Void>> cellFactory = this::createActionsTableCell;
        actionColumn.setCellFactory(cellFactory);

        // Load the enrollments from DataStorage and set them in the table view
        enrollments = FXCollections.observableArrayList(DataStorage.getEnrollments());
        enrollmentTable.setItems(enrollments);
    }

    /**
     * Returns the label for the edit button in the action table cell.
     * In this case, the label is "Grade" to reflect grading functionality.
     *
     * @return The label text for the edit button.
     */
    @Override
    public String getEditButtonLabel() {
        return "Grade";
    }

    /**
     * Handles the action to add a new enrollment by opening the form window.
     * After closing the form, the table is refreshed to reflect any new enrollments.
     */
    @FXML
    private void handleNewEnrollment() {
        // Open the form to add a new enrollment
        openForm("NewEnrollmentForm.fxml", "New Enrollment", true, null);
        // Refresh the table after adding a new enrollment
        refreshTable();
    }

    /**
     * Handles the action to edit an enrollment by opening the grading form.
     * This allows updating the student's grade for the selected enrollment.
     *
     * @param enrollment The enrollment to be graded.
     */
    @Override
    public void editModel(StudentEnrollment enrollment) {
        // Open the form to grade the enrollment
        openForm("GradeEnrollmentForm.fxml", "Grade Enrollment", true, enrollment);
        // Refresh the table after grading the enrollment
        refreshTable();
    }

    /**
     * Handles the action to close the enrollment management window.
     */
    @FXML
    private void handleClose() {
        // Close the current window
        Stage stage = (Stage) enrollmentManagement.getScene().getWindow();
        closeWindow(stage);
    }

    /**
     * Removes the specified enrollment from the data storage and refreshes the table.
     *
     * @param enrollment The enrollment to be removed.
     */
    @Override
    public void removeModel(StudentEnrollment enrollment) {
        // Remove the enrollment from data storage
        DataStorage.removeEnrollment(enrollment);
        // Refresh the table to reflect the changes
        refreshTable();
    }

    /**
     * Refreshes the enrollment table by reloading the enrollments from the data storage.
     */
    private void refreshTable() {
        // Reload the enrollments from DataStorage and update the observable list
        enrollments.setAll(DataStorage.getEnrollments());
    }

}
