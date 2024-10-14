/**
 * The StudentEnrollment class represents the enrollment of a student in a course.
 * It includes details about the student, the course, and the grade achieved by the student.
 */
public class StudentEnrollment {

    // The student involved in the enrollment
    private Student student;

    // The course in which the student is enrolled
    private Course course;

    // The grade assigned to the student for the course, initialized to -1 (not graded yet)
    private double grade = -1;

    /**
     * Constructs a StudentEnrollment object with the specified student and course.
     *
     * @param student the student being enrolled.
     * @param course  the course in which the student is enrolling.
     * @throws IllegalArgumentException if either the student or course is null.
     */
    public StudentEnrollment(Student student, Course course) {
        if (student == null || course == null) {
            throw new IllegalArgumentException("Student and Course must be provided");
        }

        this.student = student;
        this.course = course;

        // Add the enrollment to the student and course.
        this.student.addEnrollment(this);
        this.course.addEnrollment(this);
    }

    /**
     * Returns a description of the enrollment, including the student's name and the course name.
     *
     * @return a string representing the enrollment description.
     */
    public String getDescription() {
        return student.getName() + " enroll " + this.course.getName();
    }

    /**
     * Returns the student associated with this enrollment.
     *
     * @return the student involved in the enrollment.
     */
    public Student getStudent() {
        return this.student;
    }

    /**
     * Returns the course associated with this enrollment.
     *
     * @return the course in which the student is enrolled.
     */
    public Course getCourse() {
        return this.course;
    }

    /**
     * Returns the grade assigned to the student for this course.
     *
     * @return the grade of the student for the course.
     */
    public double getGrade() {
        return this.grade;
    }

    /**
     * Sets the grade for the student in this course.
     *
     * @param grade the grade to be assigned to the student.
     */
    public void setGrade(double grade) {
        this.grade = grade;
    }
}