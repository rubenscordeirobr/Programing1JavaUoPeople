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
 * Controller class for managing the Course Management interface.
 * Extends {@link BaseManagementController} to handle course-related actions 
 * such as adding, editing, and removing courses.
 */
public class CourseManagementController extends BaseManagementController<Course> {

    @FXML
    private VBox courseManagement;

    @FXML
    private TableView<Course> courseTable;

    @FXML
    private TableColumn<Course, String> idColumn;

    @FXML
    private TableColumn<Course, String> nameColumn;

    @FXML
    private TableColumn<Course, String> departmentColumn;

    @FXML
    private TableColumn<Course, Void> actionColumn;

    // Observable list to hold the courses data for the table view
    private ObservableList<Course> courses;

    /**
     * Initializes the course management screen.
     * Sets up the table columns and loads the course data from the DataStorage.
     */
    @FXML
    public void initialize() {
        // Set cell value factories for the course columns
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        departmentColumn.setCellValueFactory(new PropertyValueFactory<>("department"));

        // Set cell factory for the action column to generate Edit/Delete buttons
        Callback<TableColumn<Course, Void>, TableCell<Course, Void>> cellFactory = this::createActionsTableCell;
        actionColumn.setCellFactory(cellFactory);

        // Load the courses from DataStorage and set them in the table view
        courses = FXCollections.observableArrayList(DataStorage.getCourses());
        courseTable.setItems(courses);
    }

    /**
     * Handles the action to add a new course by opening the form window.
     * After closing the form, the table is refreshed to reflect any added courses.
     */
    @FXML
    private void handleNewCourse() {
        // Open the form to add a new course (null model indicates a new course)
        openForm("CourseForm.fxml", "Add Course", true, null);
        // Refresh the course table after adding a new course
        refreshTable();
    }

    /**
     * Handles the action to edit an existing course by opening the form window
     * with the course data.
     *
     * @param course The course to be edited.
     */
    @Override
    public void editModel(Course course) {
        // Open the form with the course data to edit
        openForm("CourseForm.fxml", "Edit Course", true, course);
        // Refresh the course table after updating the course
        refreshTable();
    }

    /**
     * Handles the action to close the course management window.
     */
    @FXML
    private void handleClose() {
        // Close the current window
        Stage stage = (Stage) courseManagement.getScene().getWindow();
        closeWindow(stage);
    }

    /**
     * Removes the specified course from the data storage and refreshes the table.
     *
     * @param course The course to be removed.
     */
    @Override
    public void removeModel(Course course) {
        // Remove the course from data storage
        DataStorage.removeCourse(course);
        // Refresh the table to reflect the changes
        refreshTable();
    }

    /**
     * Refreshes the course table by reloading the courses from the data storage.
     */
    private void refreshTable() {
        // Reload the courses from DataStorage and update the observable list
        courses.setAll(DataStorage.getCourses());
    }

}
