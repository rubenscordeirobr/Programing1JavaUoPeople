package studmgmtsys.data;

import java.util.HashMap;
import java.util.ArrayList;
import studmgmtsys.models.*;

/**
 * The DataStorage class provides in-memory storage for various models such as
 * Departments, Students, Courses, and StudentEnrollments. It offers
 * methods to retrieve, add, and manage these models.
 */
public class DataStorage {

    private static HashMap<String, Department> departments = new HashMap<>();
    private static HashMap<String, Student> students = new HashMap<>();
    private static HashMap<String, Course> courses = new HashMap<>();
    private static HashMap<String, StudentEnrollment> enrollments = new HashMap<>();

    /**
     * Retrieves all Departments as a list.
     * 
     * @return an ArrayList of Department objects.
     */
    public static ArrayList<Department> getDepartments() {
        return new ArrayList<>(departments.values());
    }

    /**
     * Retrieves all Students as a list.
     * 
     * @return an ArrayList of Student objects.
     */
    public static ArrayList<Student> getStudents() {
        return new ArrayList<>(students.values());
    }

    /**
     * Retrieves all Courses as a list.
     * 
     * @return an ArrayList of Course objects.
     */
    public static ArrayList<Course> getCourses() {
        return new ArrayList<>(courses.values());
    }

    /**
     * Retrieves all StudentEnrollments as a list.
     * 
     * @return an ArrayList of StudentEnrollment objects.
     */
    public static ArrayList<StudentEnrollment> getEnrollments() {
        return new ArrayList<>(enrollments.values());
    }

    /**
     * Finds a Department by its unique ID.
     * 
     * @param id the ID of the Department.
     * @return the Department object if found, or null if not found.
     */
    public static Department getDepartmentById(String id) {
        return departments.get(id);
    }

    /**
     * Finds a Student by their unique ID.
     * 
     * @param id the ID of the Student.
     * @return the Student object if found, or null if not found.
     */
    public static Student getStudentById(String id) {
        return students.get(id);
    }

    /**
     * Finds a Course by its unique ID.
     * 
     * @param id the ID of the Course.
     * @return the Course object if found, or null if not found.
     */
    public static Course getCourseById(String id) {
        return courses.get(id);
    }

    /**
     * Finds a StudentEnrollment by the Student and Course objects.
     * 
     * @param student the Student object.
     * @param course the Course object.
     * @return the StudentEnrollment object if found, or null if not found.
     */
    public static StudentEnrollment getEnrollment(Student student, Course course) {
        String id = StudentEnrollment.getId(student, course);
        return enrollments.get(id);
    }

    /**
     * Adds a new Department to the storage.
     * 
     * @param department the Department object to add.
     */
    public static void addDepartment(Department department) {
        if (departments.containsKey(department.getId())) {
            throw new IllegalArgumentException("Department ID " + department.getId() + " already exists");
        }
        departments.put(department.getId(), department);
    }

    /**
     * Adds a new Student to the storage.
     * 
     * @param student the Student object to add.
     */
    public static void addStudent(Student student) {
        if (students.containsKey(student.getId())) {
            throw new IllegalArgumentException("Student ID " + student.getId() + " already exists");
        }
        students.put(student.getId(), student);
    }

    /**
     * Adds a new Course to the storage.
     * 
     * @param course the Course object to add.
     */
    public static void addCourse(Course course) {
        if (courses.containsKey(course.getId())) {
            throw new IllegalArgumentException("Course CODE " + course.getId() + " already exists");
        }
        courses.put(course.getId(), course);
    }

    /**
     * Adds a new StudentEnrollment to the storage.
     * 
     * @param enrollment the StudentEnrollment object to add.
     */
    public static void addEnrollment(StudentEnrollment enrollment) {
        
        if (enrollments.containsKey(enrollment.getId())) {
            String errorMessage = "Student " + enrollment.getStudent().getName() +
                    " is already enrolled in the course " + enrollment.getCourse().getName();
            throw new IllegalArgumentException(errorMessage);
        }
        enrollments.put(enrollment.getId(), enrollment);
    }

    /**
     * Removes a Department from the storage.
     * 
     * @param department the Department object to remove.
     * @return true if the Department was successfully removed, false otherwise.
     */
    public static boolean removeDepartment(Department department) {
        return departments.remove(department.getId()) != null;
    }

    /**
     * Removes a Course from the storage.
     * 
     * @param course the Course object to remove.
     * @return true if the Course was successfully removed, false otherwise.
     */
    public static boolean removeCourse(Course course) {
        return courses.remove(course.getId()) != null;
    }

    /**
     * Removes a Student from the storage.
     * 
     * @param student the Student object to remove.
     * @return true if the Student was successfully removed, false otherwise.
     */
    public static boolean removeStudent(Student student) {
        return students.remove(student.getId()) != null;
    }


    /**
     * Removes a StudentEnrollment from the storage.
     * 
     * @param enrollment the StudentEnrollment object to remove.
     * @return true if the StudentEnrollment was successfully removed, false otherwise.
     */
    public static boolean removeEnrollment(StudentEnrollment enrollment) {
        return enrollments.remove(enrollment.getId()) != null;
    }

}
