package studmgmtsys;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import studmgmtsys.data.DataStorage;
import studmgmtsys.models.Department;
import studmgmtsys.utils.AlertUtils;

/**
 * Controller for the Department form.
 * Handles adding and editing departments, and validates user input before saving to DataStorage.
 */
public class DepartmentFormController extends BaseControllerForm<Department> {

    @FXML
    private TextField idField;

    @FXML
    private TextField nameField;

    /**
     * Called when the UI is fully displayed. If the department is being edited,
     * the fields are populated with the department data.
     */
    @Override
    protected void onUIShown() {
        Department department = this.getModel();

        // If the department is not null, it means we are editing an existing department
        if (department != null) {
            // Disable editing of the ID field for existing departments
            idField.setEditable(false);

            // Populate the form fields with the department data
            idField.setText(department.getId());
            nameField.setText(department.getName());
        }
    }

    /**
     * Handles the submit button click. Validates the form fields and either adds
     * a new department or updates an existing department, then closes the form.
     */
    @FXML
    private void handleSubmit() {
        // Get the values from the text fields
        String id = idField.getText();
        String name = nameField.getText();

        // Validate the input fields
        if (id.isEmpty()) {
            AlertUtils.showErrorAlert("The field ID is required.");
            return;
        }

        if (name.isEmpty()) {
            AlertUtils.showErrorAlert("The field Name is required.");
            return;
        }

        Department department = this.getModel();
        boolean isNewDepartment = department == null;

        // If it's a new department, check if the ID already exists
        if (isNewDepartment) {
            if (DataStorage.getDepartmentById(id) != null) {
                AlertUtils.showErrorAlert("The Department ID already exists.");
                return;
            }

            // Create a new department
            department = new Department(id, name);
            DataStorage.addDepartment(department);
            AlertUtils.showSuccessAlert("The department has been added successfully.");

        } else {
            // Update the existing department
            department.setName(name);
            AlertUtils.showSuccessAlert("The department has been updated successfully.");
        }

        // Close the form
        closeForm();
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
