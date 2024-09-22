package Models;

import java.util.ArrayList;
import Utils.StringUtils;

/**
 * The Professor class represents a professor entity, which extends the Person class.
 * It has additional attributes such as an ID, department, and a list of courses.
 * The professor's ID is automatically generated using an incremental approach.
 */
public class Professor extends Person {

    private static int incrementalId = 0;  // Renamed to match Java naming conventions for static fields.

    private int id;
    private Department department;
    private ArrayList<Course> courses = new ArrayList<Course>();

    /**
     * Constructs a Professor object with a name, birth date, and department.
     * The ID is automatically assigned using an incremental counter.
     *
     * @param name the name of the professor.
     * @param birthDate the birth date of the professor in String format.
     * @param department the department to which the professor belongs.
     */
    public Professor(String name, String birthDate, Department department) {
        super(name, birthDate);
        incrementalId++;
        this.id = incrementalId;
        this.department = department;
        department.addProfessor(this);
    }

    /**
     * Returns the ID of the professor as a String.
     *
     * @return the ID of the professor.
     */
    public String getId() {
        return Integer.toString(this.id);
    }

    /**
     * Returns the department to which the professor belongs.
     *
     * @return the department of the professor.
     */
    public Department getDepartment() {
        return department;
    }

    /**
     * Returns the list of courses taught by the professor.
     *
     * @return an ArrayList of courses.
     */
    public ArrayList<Course> getCourses() {
        return courses;
    }

    /**
     * Adds a course to the list of courses taught by the professor.
     *
     * @param course the course to be added.
     */
    public void addCourse(Course course) {
        this.courses.add(course);
    }

    /**
     * Returns a formatted string representing the professor's view.
     * The view contains the professor's ID, name, age, and department name.
     *
     * @return a formatted string with professor details.
     */
    public String getView() {
        return StringUtils.padRight(this.getId(), 10) + " | " +
               StringUtils.padRight(this.getName(), 20) + " | " +
               StringUtils.padRight(Integer.toString(this.getAge()), 10) + " | " +
               StringUtils.padRight(this.getDepartment().getName(), 20);
    }

    /**
     * Returns a formatted header for displaying professor details.
     *
     * @return a formatted string representing the header.
     */
    public static String getViewHeader() {
        return StringUtils.padRight("ID", 10) + " | " +
               StringUtils.padRight("Name", 20) + " | " +
               StringUtils.padRight("Age", 10) + " | " +
               StringUtils.padRight("Department", 20);
    }
}
