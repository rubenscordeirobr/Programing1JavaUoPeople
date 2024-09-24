import data.InitialData;
import utils.*;
import managers.*;

/**
 * The Main class serves as the entry point for the Student Management System.
 * It displays a main menu for the user to choose different management options
 * such as Department, Professor, Course, and Student management.
 */
public class Main {

    /**
     * The main method initializes the application by populating initial data
     * and displaying the main menu.
     * 
     * @param args command-line arguments (not used in this application).
     */
    public static void main(String[] args) {

        // Populating the initial data
        InitialData.populate();

        System.out.println("Welcome to Student Management for University of the People");
        System.out.println("----------------------------------------------------------");
        System.out.println("Type q or quit anytime to exit the program");

        // Show the main menu to the user
        showMainMenu();
    }

    /**
     * Displays the main menu and prompts the user to select an option.
     * The menu allows the user to manage departments, professors, courses, and students.
     */
    public static void showMainMenu() {
        System.out.println("Main Menu");
        System.out.println("0. Exit");
        System.out.println("1. Department Management");
        System.out.println("2. Professor Management");
        System.out.println("3. Course Management");
        System.out.println("4. Student Management");

        int option = InputUtils.getInt("Enter an option: ");

        switch (option) {
            case 0:
                System.out.println("Goodbye! Thank you for using Student Management System.");
                System.exit(0); // Exit the program
                break;

            case 1:
                showDepartmentManagement();
                break;

            case 2:
                showProfessorManagement();
                break;

            case 3:
                showCourseManagement();
                break;

            case 4:
                showStudentManagement();
                break;

            default:
                PrintUtils.printFail("Invalid option, please try again.");
                break;
        }

        // Show the main menu again after the user action
        showMainMenu();
    }

    /**
     * Displays the department management menu by initializing and calling the
     * DepartmentManager.
     */
    public static void showDepartmentManagement() {
        DepartmentManager departmentManager = new DepartmentManager();
        departmentManager.showMenu();
    }

    /**
     * Displays the student management menu by initializing and calling the
     * StudentManager.
     */
    public static void showStudentManagement() {
        StudentManager studentManager = new StudentManager();
        studentManager.showMenu();
    }

    /**
     * Displays the professor management menu by initializing and calling the
     * ProfessorManager.
     */
    public static void showProfessorManagement() {
        ProfessorManager professorManager = new ProfessorManager();
        professorManager.showMenu();
    }

    /**
     * Displays the course management menu by initializing and calling the
     * CourseManager.
     */
    public static void showCourseManagement() {
        CourseManager courseManager = new CourseManager();
        courseManager.showMenu();
    }
}
