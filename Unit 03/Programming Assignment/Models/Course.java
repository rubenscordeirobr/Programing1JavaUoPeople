package Models;

import Utils.StringUtils;

/**
 * The Course class represents a course within a department.
 * It includes attributes such as ID, name, description, and a reference to the department.
 */
public class Course extends Model {

    private Department department;
    private String id;
    private String name;
    private String description;

    /**
     * Constructs a Course object with the specified ID, name, description, and department.
     * The course is automatically added to the department's list of courses.
     *
     * @param id          the unique identifier for the course.
     * @param name        the name of the course.
     * @param description a brief description of the course.
     * @param department  the department to which the course belongs.
     */
    public Course(String id, String name, String description, Department department) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.department = department;
        department.addCourse(this);  // Automatically associate this course with the department.
    }

    /**
     * Returns the department to which the course belongs.
     *
     * @return the department of the course.
     */
    public Department getDepartment() {
        return department;
    }

    /**
     * Returns the unique ID of the course.
     *
     * @return the ID of the course.
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the name of the course.
     *
     * @return the name of the course.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns a brief description of the course.
     *
     * @return the description of the course.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the full description of the course, including its ID and name.
     *
     * @return a string representing the full description of the course.
     */
    public String getFullDescription() {
        return this.id + " -- " + this.name;
    }

    /**
     * Returns a formatted string representing the course's view.
     * The view includes the course's ID, name, and department name.
     *
     * @return a formatted string with course details.
     */
    public String getView() {
        return StringUtils.padRight(this.getId(), 10) + " | " +
               StringUtils.padRight(this.getName(), 20) + " | " +
               StringUtils.padRight(this.getDepartment().getName(), 20);
    }

    /**
     * Returns a formatted header for displaying course details.
     *
     * @return a formatted string representing the header for course views.
     */
    public static String getViewHeader() {
        return StringUtils.padRight("ID", 10) + " | " +
               StringUtils.padRight("Name", 20) + " | " +
               StringUtils.padRight("Department", 20);
    }

    /**
     * Sets the name of the course.
     *
     * @param name the new name of the course.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the description of the course.
     *
     * @param description the new description of the course.
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
