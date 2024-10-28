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
 * Controller class for managing the Student Management interface.
 * Extends {@link BaseManagementController} to handle student-related actions 
 * such as adding, editing, and removing students.
 */
public class StudentManagementController extends BaseManagementController<Student> {

    @FXML
    private VBox studentManagement;

    @FXML
    private TableView<Student> studentTable;

    @FXML
    private TableColumn<Student, String> idColumn;

    @FXML
    private TableColumn<Student, String> nameColumn;

    @FXML
    private TableColumn<Student, Void> actionColumn;

    // Observable list to hold the student data for the table view
    private ObservableList<Student> students;

    /**
     * Initializes the student management screen.
     * Sets up the table columns and loads the student data from DataStorage.
     */
    @FXML
    public void initialize() {
        // Set cell value factories for the student columns
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        // Set cell factory for the action column to generate Edit/Delete buttons
        Callback<TableColumn<Student, Void>, TableCell<Student, Void>> cellFactory = this::createActionsTableCell;
        actionColumn.setCellFactory(cellFactory);

        // Load the students from DataStorage and set them in the table view
        students = FXCollections.observableArrayList(DataStorage.getStudents());
        studentTable.setItems(students);
    }

    /**
     * Handles the action to add a new student by opening the form window.
     * After closing the form, the table is refreshed to reflect any added students.
     */
    @FXML
    private void handleNewStudent() {
        // Open the form to add a new student (null model indicates a new student)
        openForm("StudentForm.fxml", "Add Student", true, null);
        // Refresh the student table after adding a new student
        refreshTable();
    }

    /**
     * Handles the action to edit an existing student by opening the form window
     * with the student data.
     *
     * @param student The student to be edited.
     */
    @Override
    public void editModel(Student student) {
        // Open the form with the student data to edit
        openForm("StudentForm.fxml", "Edit Student", true, student);
        // Refresh the student table after updating the student
        refreshTable();
    }

    /**
     * Handles the action to close the student management window.
     */
    @FXML
    private void handleClose() {
        // Close the current window
        Stage stage = (Stage) studentManagement.getScene().getWindow();
        closeWindow(stage);
    }

    /**
     * Removes the specified student from the data storage and refreshes the table.
     *
     * @param model The student to be removed.
     */
    @Override
    public void removeModel(Student model) {
        // Remove the student from data storage
        DataStorage.removeStudent(model);
        // Refresh the table to reflect the changes
        refreshTable();
    }

    /**
     * Refreshes the student table by reloading the students from the data storage.
     */
    private void refreshTable() {
        // Reload the students from DataStorage and update the observable list
        students.setAll(DataStorage.getStudents());
    }

}
