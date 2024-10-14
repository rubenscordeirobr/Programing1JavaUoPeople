import java.util.ArrayList;

/**
 * The Main class provides an entry point for the Course Enrollment and Grade
 * Management System.
 * It allows users to interact with the system by adding courses, students,
 * enrolling students, assigning grades,
 * and viewing student or course information.
 */
public class Main {

    private static final String COLUMN = " | ";

    /**
     * The main method serves as the entry point for the program.
     * It initializes the system, shows the welcome message, and displays the main
     * menu.
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {

        // Populating the initial data
        System.out.println("Welcome to Course Enrollment and Grade Management System");
        System.out.println("----------------------------------------------------------");
        System.out.println("Type q or quit anytime to exit the program");
        
        // Main loop to display the menu
        while (true) {
            // Show the main menu to the user
            displayMenu();
        }
    }

    /**
     * Displays the main menu and prompts the user to select an option.
     * The menu allows the user to manage courses, students, enrollments, and
     * grades.
     */
    public static void displayMenu() {

        System.out.println("Main Menu");
        System.out.println("0. Exit or type 'q' to quit anytime");
        System.out.println("1. Add New Course");
        System.out.println("2. Add New Student");
        System.out.println("3. List All Courses");
        System.out.println("4. List All Students");
        System.err.println("5. Enroll Student in Course");
        System.out.println("6. Assign Grade to Student");
        System.out.println("7. Calculate Overall Grade of Student");

        int option = InputUtils.getInt("Enter an option (1-7): ");

        switch (option) {
            case 0:
                System.out.println("Goodbye! Thank you for using Student Management System.");
                System.exit(0); // Exit the program
                break;

            case 1:
                addCourse();
                break;

            case 2:
                addStudent();
                break;

            case 3:
                listAllCourses();
                break;

            case 4:
                listAllStudents();
                break;
            case 5:
                enrollStudent();
                break;

            case 6:
                assignGrade();
                break;

            case 7:
                calculateOverallGrade();
                break;

            default:
                PrintUtils.printFail("Invalid option, please try again.");
                break;
        }
    }

    /**
     * Prompts the user for course details and adds a new course to the system.
     */
    public static void addCourse() {

        String code = InputUtils.getString("Enter the course code: ", true);
        String name = InputUtils.getString("Enter the course name: ", true);
        int capacity = InputUtils.getInt("Enter the course capacity: ");

        if (CourseManagement.addCourse(code, name, capacity)) {
            PrintUtils.printSuccess("Course " + name + " added successfully");
        }
    }

    /**
     * Prompts the user for student details and adds a new student to the system.
     */
    public static void addStudent() {

        String id = InputUtils.getString("Enter the student ID: ", true);
        String name = InputUtils.getString("Enter the student name: ", true);

        CourseManagement.addStudent(id, name);
        PrintUtils.printSuccess("Student " + name + " added successfully");
    }

    /**
     * Lists all courses in the system, displaying their details.
     */
    public static void listAllCourses() {

        ArrayList<Course> courses = CourseManagement.getCourses();

        if (courses.size() == 0) {
            PrintUtils.printFail("No courses found");
            return;
        }

        System.out.println("Courses: " + courses.size() + " courses found");

        String header = COLUMN + StringUtils.padRight("Code", 10) + COLUMN +
                StringUtils.padRight("Name", 30) + COLUMN +
                StringUtils.padRight("Capacity", 10);

        System.out.println(header);

        for (Course course : courses) {
            String row = COLUMN + StringUtils.padRight(course.getCode(), 10) + COLUMN +
                    StringUtils.padRight(course.getName(), 30) + COLUMN +
                    StringUtils.padRight(String.valueOf(course.getCapacity()), 10) + COLUMN;
            System.out.println(row);
        }
    }

    /**
     * Lists all students in the system, displaying their details.
     */
    public static void listAllStudents() {

        ArrayList<Student> students = CourseManagement.getStudents();

        if (students.size() == 0) {
            PrintUtils.printFail("No students found");
            return;
        }

        System.out.println("Students: " + students.size() + " students found");

        String header = COLUMN + StringUtils.padRight("ID", 10) + COLUMN +
                StringUtils.padRight("Name", 30) + COLUMN +
                StringUtils.padRight("Overall Grade", 15) + COLUMN;

        System.out.println(header);

        for (Student student : students) {

            double overallGrade = CourseManagement.calculateOverallGrade(student);

            String overallGradeString = overallGrade == -1 ? "N/A" : String.valueOf(overallGrade);
            String row = COLUMN + StringUtils.padRight(student.getId(), 10) + COLUMN +
                    StringUtils.padRight(student.getName(), 30) + COLUMN +
                    StringUtils.padRight(overallGradeString, 15) + COLUMN;

            System.out.println(row);
        }
    }

    /**
     * Enrolls a student in a course after validating the student's ID and the
     * course code.
     */
    public static void enrollStudent() {

        String studentId = InputUtils.getString("Enter the student ID: ");
        Student student = CourseManagement.getStudentById(studentId);
        if (student == null) {
            PrintUtils.printFail("Student with ID " + studentId + " not found");
            return;
        }

        String courseCode = InputUtils.getString("Enter the course code: ");
        Course course = CourseManagement.getCourseByCode(courseCode);
        if (course == null) {
            PrintUtils.printFail("Course with code " + courseCode + " not found");
            return;
        }

        CourseManagement.enrollStudent(student, course);
        PrintUtils.printSuccess("Student " + student.getName() + " enrolled in course " + course.getName());
    }

    /**
     * Assigns a grade to a student for a specific course.
     */
    public static void assignGrade() {

        String studentId = InputUtils.getString("Enter the student ID: ");
        Student student = CourseManagement.getStudentById(studentId);
        if (student == null) {
            PrintUtils.printFail("Student with ID " + studentId + " not found");
            return;
        }

        String courseCode = InputUtils.getString("Enter the course code: ");
        Course course = CourseManagement.getCourseByCode(courseCode);
        if (course == null) {
            PrintUtils.printFail("Course with code " + courseCode + " not found");
            return;
        }

        double grade = InputUtils
                .getDouble("Enter the grade for student " + student.getName() + " in course " + course.getName(), true);

        if (grade < 0 || grade > 100) {
            PrintUtils.printFail("Invalid grade. Grade must be between 0 and 100");
            return;
        }

        CourseManagement.assignGrade(student, course, grade);
        PrintUtils.printSuccess(
                "Grade " + grade + " assigned to student " + student.getName() + " in course " + course.getName());
    }

    /**
     * Calculates the overall grade for a student across all enrolled courses and
     * displays it.
     */
    public static void calculateOverallGrade() {

        String studentId = InputUtils.getString("Enter the student ID: ");
        Student student = CourseManagement.getStudentById(studentId);
        if (student == null) {
            PrintUtils.printFail("Student with ID " + studentId + " not found");
            return;
        }

        double overallGrade = CourseManagement.calculateOverallGrade(student);
        if (overallGrade == -1) {
            PrintUtils.printFail("Student " + student.getName() + " is not enrolled in any courses");
            return;
        }

        PrintUtils.printSuccess("Overall grade of student " + student.getName() + " is " + overallGrade);
    }
}