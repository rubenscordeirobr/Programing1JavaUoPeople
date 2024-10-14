
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.HashMap;
import java.util.Map;

/**
 * The CourseManagement class provides functionality to manage students, courses, and enrollments.
 * It supports adding new students and courses, enrolling students in courses, assigning grades, 
 * and calculating overall grades for students.
 */
public class CourseManagement {

    // List of all students in the system
    private static ArrayList<Student> students = new ArrayList<>();

    // List of all courses in the system
    private static ArrayList<Course> courses = new ArrayList<>();

    // List of all student enrollments in the system
    private static ArrayList<StudentEnrollment> enrollments = new ArrayList<>();

    // Map to store the overall grades for each student
    private static Map<Student, Double> overallGrades = new HashMap<>();

    /**
     * Returns the list of all students.
     *
     * @return an ArrayList of Student objects.
     */
    public static ArrayList<Student> getStudents() {
        return students;
    }

    /**
     * Returns the list of all courses.
     *
     * @return an ArrayList of Course objects.
     */
    public static ArrayList<Course> getCourses() {
        return courses;
    }

    /**
     * Retrieves a student by their ID.
     *
     * @param id the ID of the student.
     * @return the Student object if found, or null if not found.
     */
    public static Student getStudentById(String id) {
        return students.stream().filter(x -> x.getId().equals(id)).findFirst().orElse(null);
    }

    /**
     * Retrieves a course by its code.
     *
     * @param code the code of the course.
     * @return the Course object if found, or null if not found.
     */
    public static Course getCourseByCode(String code) {
        return courses.stream().filter(x -> x.getCode().equals(code)).findFirst().orElse(null);
    }

    /**
     * Adds a new course to the system.
     *
     * @param code     the unique code for the course.
     * @param name     the name of the course.
     * @param capacity the maximum number of students that can be enrolled.
     * @return true if the course was successfully added, false if a course with the same code already exists.
     */
    public static boolean addCourse(String code, String name, int capacity) {
        if (getCourseByCode(code) != null) {
            PrintUtils.printFail("Course with code " + code + " already exists");
            return false;
        }
        courses.add(new Course(code, name, capacity));
        return true;
    }

    /**
     * Adds a new student to the system.
     *
     * @param id   the unique ID for the student.
     * @param name the name of the student.
     * @return true if the student was successfully added, false if a student with the same ID already exists.
     */
    public static boolean addStudent(String id, String name) {
        if (getStudentById(id) != null) {
            PrintUtils.printFail("Student with ID " + id + " already exists");
            return false;
        }
        students.add(new Student(id, name));
        return true;
    }

    /**
     * Enrolls a student in a course.
     *
     * @param student the student to be enrolled.
     * @param course  the course in which to enroll the student.
     * @return true if the enrollment was successful, false otherwise.
     */
    public static boolean enrollStudent(Student student, Course course) {
        if (student.isStudentEnrolled(course)) {
            PrintUtils.printFail("Student " + student.getName() + " is already enrolled in the course " + course.getName());
            return false;
        }

        if (course.isFull()) {
            PrintUtils.printFail("The course " + course.getName() + " is full");
            return false;
        }
        enrollments.add(new StudentEnrollment(student, course));
        return true;
    }

    /**
     * Assigns a grade to a student for a specific course.
     *
     * @param student the student to be graded.
     * @param course  the course for which the grade is assigned.
     * @param grade   the grade to assign.
     * @return true if the grade was successfully assigned, false otherwise.
     */
    public static boolean assignGrade(Student student, Course course, double grade) {
        StudentEnrollment enrollment = enrollments.stream()
                .filter(x -> x.getStudent().getId().equals(student.getId()) && x.getCourse().getCode().equals(course.getCode()))
                .findFirst().orElse(null);

        if (enrollment == null) {
            PrintUtils.printFail("Student " + student.getName() + " is not enrolled in the course " + course.getName());
            return false;
        }
        enrollment.setGrade(grade);
        return true;
    }

    /**
     * Calculates the overall grade for a student across all courses they are enrolled in.
     *
     * @param student the student for whom to calculate the overall grade.
     * @return the overall grade of the student.
     */
    public static double calculateOverallGrade(Student student) {
        ArrayList<StudentEnrollment> studentEnrollments = enrollments.stream()
                .filter(x -> x.getStudent().getId().equals(student.getId()))
                .collect(Collectors.toCollection(ArrayList::new));

        double totalGradePoints = 0;
        double totalCourseGraded = 0;

        for (StudentEnrollment enrollment : studentEnrollments) {
            if (enrollment.getGrade() != -1) {
                totalGradePoints += enrollment.getGrade();
                totalCourseGraded += 1;
            }
        }

        double overallGrade = totalCourseGraded == 0 ? 0 : totalGradePoints / totalCourseGraded;
        overallGrades.put(student, overallGrade);
        return overallGrade;
    }
}