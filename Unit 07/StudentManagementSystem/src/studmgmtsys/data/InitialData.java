package studmgmtsys.data;

import studmgmtsys.models.*;

/**
 * The InitialData class is responsible for populating the application
 * with sample data for departments,  courses, and students. It also
 * simulates course enrollments.
 */
public class InitialData {

    /**
     * Populates the system with sample data for departments,  courses,
     * and students.
     * This method acts as the entry point for the data population process.
     */
    public static void populate() {
        System.out.println("Populating data...");
        populateDepartments();
        populateCourses();
        populateStudents();
    }

    /**
     * Populates the system with sample departments, including Business
     * Administration,
     * Computer Science, Health Science, and Mathematics.
     */
    private static void populateDepartments() {
        System.out.println("Populating departments...");

        Department businessAdministration = new Department("BUS", "Business Administration");
        Department computerScience = new Department("CS", "Computer Science");
        Department mathematics = new Department("MATH", "Mathematics");

        DataStorage.addDepartment(businessAdministration);
        DataStorage.addDepartment(computerScience);
        DataStorage.addDepartment(mathematics);
    }

    public static void populateCourses() {
        System.out.println("Populating courses...");

        Department businessAdministration = DataStorage.getDepartmentById("BUS");
        Department computerScience = DataStorage.getDepartmentById("CS");
        Department mathematics = DataStorage.getDepartmentById("MATH");

        // Computer Science Courses
        DataStorage.addCourse(new Course("CS1101", "Programming Fundamentals", 30, computerScience));
        DataStorage.addCourse(new Course("CS1102", "Programming 1", 30, computerScience));

        // Business Administration Courses
        DataStorage.addCourse(new Course("BUS1101", "Principles of Business Management ", 25, businessAdministration));

        // Mathematics Courses
        DataStorage.addCourse(new Course("MATH1201", "College Algebra", 20, mathematics));
    }

    /**
     * Populates the system with sample students and enrolls them in courses. Also
     * assigns grades to students.
     */
    public static void populateStudents() {
        System.out.println("Populating students...");

        // Create students
        Student rubens = new Student("S01", "Rubens Cordeiro");
        Student pedro = new Student("S02", "Pedro Silva");
        Student maria = new Student("S03", "Maria Aparecida");
        Student carlos = new Student("S04", "Carlos Santos");

        // Add students to data storage
        DataStorage.addStudent(rubens);
        DataStorage.addStudent(pedro);
        DataStorage.addStudent(maria);
        DataStorage.addStudent(carlos);
  
        // Rubens enrolls in Programming Fundamentals
        Course programmingFundamentals = DataStorage.getCourseById("CS1101");
        Course programming1 = DataStorage.getCourseById("CS1102");
        Course businessManagement = DataStorage.getCourseById("BUS1101");

        StudentEnrollment rubensEnrollment1 = new StudentEnrollment(rubens, programmingFundamentals);
        StudentEnrollment rubensEnrollment2 = new StudentEnrollment(rubens, programming1);

        // Pedro enrolls in Programming Fundamentals
        StudentEnrollment pedroEnrollment1 = new StudentEnrollment(pedro, programmingFundamentals);
        StudentEnrollment pedroEnrollment2 = new StudentEnrollment(pedro, programming1);

        // Maria enrolls in Programming Fundamentals
        StudentEnrollment mariaEnrollment1 = new StudentEnrollment(maria, businessManagement);

        DataStorage.addEnrollment(rubensEnrollment1);
        DataStorage.addEnrollment(rubensEnrollment2);
        DataStorage.addEnrollment(pedroEnrollment1);
        DataStorage.addEnrollment(pedroEnrollment2);
        DataStorage.addEnrollment(mariaEnrollment1);

    }
}
