package studmgmtsys;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import studmgmtsys.data.DataStorage;
import studmgmtsys.models.Course;
import studmgmtsys.models.Department;
import studmgmtsys.utils.AlertUtils;
import studmgmtsys.utils.Utils;

/**
 * Controller for the Course form.
 * Handles adding and editing courses, and validates user input before saving to DataStorage.
 */
public class CourseFormController extends BaseControllerForm<Course> {

    @FXML
    private TextField idField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField capacityField;

    @FXML
    private ChoiceBox<Department> departmentChoiceBox;

    /**
     * Initializes the form by populating the department choice box
     * with available departments from DataStorage.
     */
    @Override
    protected void initialize() {
        super.initialize();
        // Load departments into the choice box
        departmentChoiceBox.getItems().addAll(DataStorage.getDepartments());
    }

    /**
     * Called when the UI is fully displayed. If the course is being edited,
     * the fields are populated with the course data.
     */
    @Override
    protected void onUIShown() {
        Course course = this.getModel();

        // If course is not null, it means we are editing an existing course
        if (course != null) {
            // Disable editing of the ID field for existing courses
            idField.setEditable(false);

            // Populate the form fields with the course data
            idField.setText(course.getId());
            nameField.setText(course.getName());
            capacityField.setText(String.valueOf(course.getCapacity()));
            departmentChoiceBox.setValue(course.getDepartment());
        }
    }

    /**
     * Handles the submit button click. Validates the form fields and either adds
     * a new course or updates an existing course, then closes the form.
     */
    @FXML
    private void handleSubmit() {
        // Get the values from the text fields
        String id = idField.getText();
        String name = nameField.getText();
        int capacity = Utils.tryParseInt(capacityField.getText());
        Department department = departmentChoiceBox.getValue();

        // Validate the input fields
        if (id.isEmpty()) {
            AlertUtils.showErrorAlert("The field ID is required.");
            return;
        }

        if (name.isEmpty()) {
            AlertUtils.showErrorAlert("The field Name is required.");
            return;
        }

        if (capacity <= 0) {
            AlertUtils.showErrorAlert("The field Capacity must be greater than 0.");
            return;
        }

        if (department == null) {
            AlertUtils.showErrorAlert("The field Department is required.");
            return;
        }

        Course course = this.getModel();
        boolean isNewCourse = course == null;

        // Check if the course is new and ensure the ID is unique
        if (isNewCourse) {
            if (DataStorage.getCourseById(id) != null) {
                AlertUtils.showErrorAlert("The Course ID already exists.");
                return;
            }

            // Create a new course
            course = new Course(id, name, capacity, department);
            DataStorage.addCourse(course);
            AlertUtils.showSuccessAlert("The course has been added successfully.");

        } else {
            // Update the existing course
            course.setName(name);
            course.setCapacity(capacity);
            course.setDepartment(department);
            AlertUtils.showSuccessAlert("The course has been updated successfully.");
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
