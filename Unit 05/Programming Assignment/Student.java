import java.util.ArrayList;

/**
 * The Student class represents a student.
 * It includes attributes such as a unique id, name, and a list of enrolled courses.
 */
public class Student {

    // Unique identifier for the student
    private String id;

    // Name of the student
    private String name;

    // List of enrollments that the student is associated with
    private ArrayList<StudentEnrollment> coursesEnrolled = new ArrayList<>();

    /**
     * Constructs a Student object with the provided ID and name.
     *
     * @param id   the ID of the student.
     * @param name the name of the student.
     */
    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Returns the student's ID as a String.
     *
     * @return the ID of the student.
     */
    public String getId() {
        return this.id;
    }
  
    /**
     * Returns the name of the student.
     *
     * @return the name of the student.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets a new name for the student.
     *
     * @param name the new name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Adds an enrollment to the student's list of enrollments.
     *
     * @param enrollment the enrollment to be added.
     */
    public void addEnrollment(StudentEnrollment enrollment) {
        this.coursesEnrolled.add(enrollment);
    }

    /**
     * Checks if the student is enrolled in a given course.
     *
     * @param course the course to check for enrollment.
     * @return true if the student is enrolled in the given course, false otherwise.
     */
    public boolean isStudentEnrolled(Course course) {
        return this.coursesEnrolled.stream()
                .anyMatch(enrollment -> enrollment.getCourse().getCode().equals(course.getCode()));
    }

}
