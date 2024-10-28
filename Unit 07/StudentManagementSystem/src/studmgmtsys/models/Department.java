package studmgmtsys.models;

import java.util.ArrayList;

public class Department extends Model {

    private String id;
    private String name;
    private ArrayList<Course> courses = new ArrayList<>();

    /**
     * Constructs a Department object with the specified ID, name, and description.
     *
     * @param id   the unique identifier for the department.
     * @param name the name of the department.
     */
    public Department(String id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Returns the department's unique ID.
     *
     * @return the ID of the department.
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the department's name.
     *
     * @return the name of the department.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns a list of courses offered by the department.
     *
     * @return an ArrayList of Course objects.
     */
    public ArrayList<Course> getCourses() {
        return courses;
    }

    /**
     * Adds a course to the department's list of courses.
     *
     * @param course the course to be added.
     */
    public void addCourse(Course course) {
        this.courses.add(course);
    }

    /**
     * Sets the name of the department.
     *
     * @param name the new name of the department.
     */
    public void setName(String name) {
        this.name = name;
    }

}
