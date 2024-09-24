package data;

import java.time.LocalDate;
import models.*;


/**
 * The InitialData class is responsible for populating the application
 * with sample data for departments, professors, courses, and students. It also
 * simulates course enrollments and assigns grades to students.
 */
public class InitialData {

    /**
     * Populates the system with sample data for departments, professors, courses, and students.
     * This method acts as the entry point for the data population process.
     */
    public static void populate() {
        System.out.println("Populating data...");
        populateDepartments();
        populateProfessors();
        populateCourses();
        populateStudents();
    }

    /**
     * Populates the system with sample departments, including Business Administration,
     * Computer Science, Health Science, and Mathematics.
     */
    private static void populateDepartments() {
        System.out.println("Populating departments...");

        Department businessAdministration = new Department("BUS", "Business Administration", "Business Administration Department");
        Department computerScience = new Department("CS", "Computer Science", "Computer Science Department");
        Department healthScience = new Department("HS", "Health Science", "Health Science Department");
        Department mathematics = new Department("MATH", "Mathematics", "Mathematics Department");

        DataStorage.addDepartment(businessAdministration);
        DataStorage.addDepartment(computerScience);
        DataStorage.addDepartment(healthScience);
        DataStorage.addDepartment(mathematics);
    }

    /**
     * Populates the system with sample professors and assigns them to their respective departments.
     */
    private static void populateProfessors() {
        System.out.println("Populating professors...");

        Department businessAdministration = DataStorage.getDepartmentById("BUS");
        Department computerScience = DataStorage.getDepartmentById("CS");
        Department healthScience = DataStorage.getDepartmentById("HS");
        Department mathematics = DataStorage.getDepartmentById("MATH");

        Professor professorIsaac = new Professor("Isaac Ayetuoma", "", computerScience);
        Professor professorJane = new Professor("Jane White", "05/03/1975", computerScience);
        Professor professorJohn = new Professor("John Smith", "10/15/1970", businessAdministration);
        Professor professorAlice = new Professor("Alice Walker", "05/20/1980", healthScience);
        Professor professorBob = new Professor("Bob Green", "08/25/1985", mathematics);
        Professor professorDavid = new Professor("David Brown", "12/30/1990", mathematics);

        DataStorage.addProfessor(professorBob);
        DataStorage.addProfessor(professorAlice);
        DataStorage.addProfessor(professorJane);
        DataStorage.addProfessor(professorJohn);
        DataStorage.addProfessor(professorIsaac);
        DataStorage.addProfessor(professorDavid);
    }

    /**
     * Populates the system with sample courses and assigns them to their respective departments.
     */
    public static void populateCourses() {
        System.out.println("Populating courses...");

        Department businessAdministration = DataStorage.getDepartmentById("BUS");
        Department computerScience = DataStorage.getDepartmentById("CS");
        Department healthScience = DataStorage.getDepartmentById("HS");
        Department mathematics = DataStorage.getDepartmentById("MATH");

        // Computer Science Courses
        DataStorage.addCourse(new Course("CS1101", "Programming Fundamentals", "Introduction to Programming Fundamentals", computerScience));
        DataStorage.addCourse(new Course("CS1102", "Programming 1", "Introduction to Programming 1", computerScience));
        DataStorage.addCourse(new Course("CS1103", "Programming 2", "Introduction to Programming 2", computerScience));
        DataStorage.addCourse(new Course("CS1104", "Computer Systems", "Introduction to Computer Systems", computerScience));
        DataStorage.addCourse(new Course("CS1105", "Digital Electronics", "Introduction to Digital Electronics", computerScience));

        // Business Administration Courses
        DataStorage.addCourse(new Course("BUS1102", "Basic Accounting", "Introduction to Accounting", businessAdministration));
        DataStorage.addCourse(new Course("BUS1103", "Microeconomics", "Introduction to Microeconomics", businessAdministration));
        DataStorage.addCourse(new Course("BUS1104", "Macroeconomics", "Introduction to Macroeconomics", businessAdministration));
        DataStorage.addCourse(new Course("BUS1105", "Business Communications", "Introduction to Business Communications", businessAdministration));
        DataStorage.addCourse(new Course("BUS2201", "Principles of Marketing", "Introduction to Principles of Marketing", businessAdministration));

        // Mathematics Courses
        DataStorage.addCourse(new Course("MATH1201", "College Algebra", "Introduction to College Algebra", mathematics));
        DataStorage.addCourse(new Course("MATH1211", "Calculus", "Introduction to Calculus", mathematics));
        DataStorage.addCourse(new Course("MATH1302", "Discrete Mathematics", "Introduction to Discrete Mathematics", mathematics));
        DataStorage.addCourse(new Course("MATH1280", "Introduction to Statistics", "Introduction to Statistics", mathematics));
        DataStorage.addCourse(new Course("MATH1281", "Statistical Inference", "Introduction to Statistical Inference", mathematics));

        // Health Science Courses
        DataStorage.addCourse(new Course("HS1101", "Introduction to Health Science", "Introduction to Health Science", healthScience));
        DataStorage.addCourse(new Course("HS2211", "Human Anatomy & Physiology", "Introduction to Human Anatomy & Physiology", healthScience));
        DataStorage.addCourse(new Course("HS2212", "Infectious Diseases", "Introduction to Infectious Diseases", healthScience));
        DataStorage.addCourse(new Course("HS2611", "Nutrition", "Introduction to Nutrition", healthScience));
        DataStorage.addCourse(new Course("HS2711", "Community and Public Health", "Introduction to Community and Public Health", healthScience));
    }

    /**
     * Populates the system with sample students and enrolls them in courses. Also assigns grades to students.
     */
    public static void populateStudents() {
        System.out.println("Populating students...");

        // Create students
        Student rubens = new Student("Rubens Cordeiro", "03/10/1983");
        Student jane = new Student("Jane Smith", "05/20/1990");
        Student alice = new Student("Alice Green", "08/25/1995");
        Student bob = new Student("Bob Brown", "12/30/2000");

        // Get professors
        Professor professorIsaac = DataStorage.getProfessorByName("Isaac Ayetuoma");
        Professor professorJane = DataStorage.getProfessorByName("Jane White");
 
        // Enroll students in courses
        rubens.enroll(DataStorage.getCourseById("CS1102"), professorIsaac, LocalDate.now());
        rubens.enroll(DataStorage.getCourseById("CS1105"), professorIsaac, LocalDate.now());

        jane.enroll(DataStorage.getCourseById("BUS1102"), professorJane, LocalDate.now());
        jane.enroll(DataStorage.getCourseById("BUS1103"), professorJane, LocalDate.now());

        alice.enroll(DataStorage.getCourseById("CS1104"), professorIsaac, LocalDate.now());
        alice.enroll(DataStorage.getCourseById("CS1105"), professorIsaac, LocalDate.now());

        bob.enroll(DataStorage.getCourseById("CS1102"), professorIsaac, LocalDate.now());
        bob.enroll(DataStorage.getCourseById("CS1105"), professorIsaac, LocalDate.now());

        // Assign grades to students
        rubens.setGradeScale(DataStorage.getCourseById("CS1102"), 95.7);
        rubens.setGradeScale(DataStorage.getCourseById("CS1105"), 85.0);

        jane.setGradeScale(DataStorage.getCourseById("BUS1102"), 75.0);
        jane.setGradeScale(DataStorage.getCourseById("BUS1103"), 80.0);

        alice.setGradeScale(DataStorage.getCourseById("CS1104"), 90.0);
        alice.setGradeScale(DataStorage.getCourseById("CS1105"), 85.0);

        bob.setGradeScale(DataStorage.getCourseById("CS1102"), 85.0);
        bob.setGradeScale(DataStorage.getCourseById("CS1105"), 90.0);

        // Add students to data storage
        DataStorage.addStudent(rubens);
        DataStorage.addStudent(jane);
        DataStorage.addStudent(alice);
        DataStorage.addStudent(bob);
    }
}
