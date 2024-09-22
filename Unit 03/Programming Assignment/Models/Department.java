package Models;

import java.util.ArrayList;
import Utils.StringUtils;

/**
 * The Department class represents a department within an educational institution.
 * It includes details such as ID, name, description, and lists of courses and professors
 * that are associated with the department.
 */
public class Department extends Model {

    private String id;
    private String name;
    private String description;
    private ArrayList<Course> courses = new ArrayList<>();
    private ArrayList<Professor> professors = new ArrayList<>();

    /**
     * Constructs a Department object with the specified ID, name, and description.
     *
     * @param id          the unique identifier for the department.
     * @param name        the name of the department.
     * @param description a brief description of the department.
     */
    public Department(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
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
     * Returns the department's description.
     *
     * @return the description of the department.
     */
    public String getDescription() {
        return description;
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
     * Returns a formatted string representing the department's basic view.
     * The view includes the department's ID, name, and description.
     *
     * @return a formatted string representing the department details.
     */
    public String getView() {
        return StringUtils.padRight(this.getId(), 10) + " | " +
               StringUtils.padRight(this.getName(), 20) + " | " +
               StringUtils.padRight(this.getDescription(), 60);
    }

    /**
     * Returns a detailed view of the department, including courses and professors, if specified.
     *
     * @param isIncludeDetails a flag indicating whether to include courses and professors.
     * @return a detailed or basic formatted string based on the flag.
     */
    public String getView(boolean isIncludeDetails) {
        if (!isIncludeDetails) {
            return getView();
        }

        StringBuilder sb = new StringBuilder();
        String view = getView();
        sb.append(view);

        // Include Courses
        if (this.courses.size() > 0) {
            sb.append("\n\n");
            sb.append(StringUtils.padRight("", 10)).append("Courses: \n");
            sb.append(StringUtils.padRight("", 10)).append(Course.getViewHeader()).append("\n");

            for (Course course : this.courses) {
                if (!course.getDepartment().getId().equals(this.getId())) {
                    throw new IllegalArgumentException(
                        "Course " + course.getName() + " is not in the department " + this.getName());
                }
                sb.append(StringUtils.padRight("", 10)).append(course.getView()).append("\n");
            }
        }

        // Include Professors
        if (this.professors.size() > 0) {
            sb.append("\n\n");
            sb.append(StringUtils.padRight("", 10)).append("Professors: \n");
            sb.append(StringUtils.padRight("", 10)).append(Professor.getViewHeader()).append("\n");

            for (Professor professor : this.professors) {
                if (!professor.getDepartment().getId().equals(this.getId())) {
                    throw new IllegalArgumentException(
                        "Professor " + professor.getName() + " is not in the department " + this.getName());
                }
                sb.append(StringUtils.padRight("", 10)).append(professor.getView()).append("\n");
            }
        }

        if (this.courses.size() > 0 || this.professors.size() > 0) {
            sb.append("\n").append(StringUtils.repeat("-", view.length()));
        }

        return sb.toString();
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
     * Adds a professor to the department's list of professors.
     *
     * @param professor the professor to be added.
     */
    public void addProfessor(Professor professor) {
        this.professors.add(professor);
    }

    /**
     * Returns a formatted header for displaying department details.
     *
     * @return a formatted string representing the header for department views.
     */
    public static String getViewHeader() {
        return StringUtils.padRight("ID", 10) + " | " +
               StringUtils.padRight("Name", 20) + " | " +
               StringUtils.padRight("Description", 60);
    }

    /**
     * Sets the name of the department.
     *
     * @param name the new name of the department.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the description of the department.
     *
     * @param description the new description of the department.
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
