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
 * Controller class for managing the Department Management interface.
 * Extends {@link BaseManagementController} to handle department-related actions 
 * such as adding, editing, and removing departments.
 */
public class DepartmentManagementController extends BaseManagementController<Department> {

    @FXML
    private VBox departmentManagement;

    @FXML
    private TableView<Department> departmentTable;

    @FXML
    private TableColumn<Department, String> idColumn;

    @FXML
    private TableColumn<Department, String> nameColumn;

    @FXML
    private TableColumn<Department, Void> actionColumn;

    // Observable list to hold the departments data for the table view
    private ObservableList<Department> departments;

    /**
     * Initializes the department management screen.
     * Sets up the table columns and loads the department data from the DataStorage.
     */
    @FXML
    public void initialize() {
        // Set cell value factories for the department columns
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        // Set cell factory for the action column to generate Edit/Delete buttons
        Callback<TableColumn<Department, Void>, TableCell<Department, Void>> cellFactory = this::createActionsTableCell;
        actionColumn.setCellFactory(cellFactory);

        // Load the departments from DataStorage and set them in the table view
        departments = FXCollections.observableArrayList(DataStorage.getDepartments());
        departmentTable.setItems(departments);
    }

    /**
     * Handles the action to add a new department by opening the form window.
     * After closing the form, the table is refreshed to reflect any added departments.
     */
    @FXML
    private void handleNewDepartment() {
        // Open the form to add a new department (null model indicates a new department)
        openForm("DepartmentForm.fxml", "Add Department", true, null);
        // Refresh the department table after adding a new department
        refreshTable();
    }

    /**
     * Handles the action to edit an existing department by opening the form window
     * with the department data.
     *
     * @param department The department to be edited.
     */
    @Override
    public void editModel(Department department) {
        // Open the form with the department data to edit
        openForm("DepartmentForm.fxml", "Edit Department", true, department);
        // Refresh the department table after updating the department
        refreshTable();
    }

    /**
     * Handles the action to close the department management window.
     */
    @FXML
    private void handleClose() {
        // Close the current window
        Stage stage = (Stage) departmentManagement.getScene().getWindow();
        closeWindow(stage);
    }

    /**
     * Removes the specified department from the data storage and refreshes the table.
     *
     * @param department The department to be removed.
     */
    @Override
    public void removeModel(Department department) {
        // Remove the department from data storage
        DataStorage.removeDepartment(department);
        // Refresh the table to reflect the changes
        refreshTable();
    }

    /**
     * Refreshes the department table by reloading the departments from the data storage.
     */
    private void refreshTable() {
        // Reload the departments from DataStorage and update the observable list
        departments.setAll(DataStorage.getDepartments());
    }

}
