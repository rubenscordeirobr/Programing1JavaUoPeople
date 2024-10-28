package studmgmtsys;

import javafx.fxml.FXML;

/**
 * Controller for the main window of the Student Management System.
 * This class handles navigation to different management screens 
 * such as departments, students, courses, and enrollments.
 */
public class MainWindowController extends BaseController {

    /**
     * Opens the Department Management window.
     */
    @FXML
    private void handleDepartments() {
        openWindow("DepartmentManagement.fxml", "Manage Departments", true);
    }

    /**
     * Opens the Student Management window.
     */
    @FXML
    private void handleStudent() {
        openWindow("StudentManagement.fxml", "Manage Students", true);
    }

    /**
     * Opens the Course Management window.
     */
    @FXML
    private void handleCourses() {
        openWindow("CourseManagement.fxml", "Manage Courses", true);
    }

    /**
     * Opens the Enrollment Management window.
     */
    @FXML
    private void handleEnrollments() {
        openWindow("EnrollmentManagement.fxml", "Manage Enrollments", true);
    }
}
