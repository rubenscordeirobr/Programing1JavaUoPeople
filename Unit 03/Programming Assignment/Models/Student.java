package Models;

import java.time.LocalDate;
import java.util.ArrayList;
import Utils.StringUtils;

/**
 * The Student class represents a student entity, which extends the Person class.
 * It has additional attributes such as an ID, a list of enrollments, and the ability to calculate GPA.
 */
public class Student extends Person {

    private static int incrementalId = 0;  // Renamed to match Java naming conventions for static fields.

    private int id;
    private ArrayList<StudentEnrollment> enrollments = new ArrayList<StudentEnrollment>();

    /**
     * Constructs a Student object with the provided name and birth date.
     * The student's ID is auto-incremented using a static counter.
     *
     * @param name      the name of the student.
     * @param birthDate the birth date of the student in String format.
     */
    public Student(String name, String birthDate) {
        super(name, birthDate);
        incrementalId++;
        this.id = incrementalId;
    }

    /**
     * Returns the student's ID as a String.
     *
     * @return the ID of the student.
     */
    public String getId() {
        return Integer.toString(this.id);
    }

    /**
     * Returns the list of student enrollments.
     *
     * @return an ArrayList of StudentEnrollment objects.
     */
    public ArrayList<StudentEnrollment> getEnrollments() {
        return enrollments;
    }

    /**
     * Calculates and returns the student's GPA based on their enrollments.
     *
     * @return the GPA of the student as a double.
     */
    public double getGPA() {
        double totalGradePoints = 0;
        double totalCredits = 0;

        // Iterate through each enrollment and calculate grade points and credits
        for (StudentEnrollment enrollment : this.enrollments) {
            if (enrollment.getGradeScale() != -1) { // Check if a valid grade is available
                totalGradePoints += enrollment.getGradePoints();
                totalCredits += 3; // Assuming each course has 3 credits
            }
        }
        return totalCredits == 0 ? 0 : totalGradePoints / totalCredits; // Avoid division by zero
    }

    /**
     * Enrolls the student in a course with a professor on the specified enrollment date.
     *
     * @param course         the course to enroll in.
     * @param professor      the professor teaching the course.
     * @param enrollmentDate the date of enrollment.
     */
    public void enroll(Course course, Professor professor, LocalDate enrollmentDate) {
        StudentEnrollment enrollment = new StudentEnrollment(this, course, professor, enrollmentDate);
        this.enrollments.add(enrollment);
    }

    /**
     * Returns a formatted string representing the student's view.
     * The view includes the student's ID, name, age, and GPA.
     *
     * @return a formatted string with student details.
     */
    public String getView() {
        return StringUtils.padRight(this.getId(), 10) + " | " +
               StringUtils.padRight(this.getName(), 20) + " | " +
               StringUtils.padRight(Integer.toString(this.getAge()), 10) + " | " +
               StringUtils.padRight(String.format("%.2f", this.getGPA()), 10); // Format GPA to 2 decimal places
    }

    /**
     * Returns a formatted string representing the student's view.
     * This implementation does not use the isIncludeDetails flag.
     *
     * @param isIncludeDetails a flag to include additional details (not used in this method).
     * @return a formatted string with student details.
     */
    public String getView(boolean isIncludeDetails) {
        return this.getView(); // Default behavior, ignoring the flag
    }

    /**
     * Returns a formatted header for displaying student details.
     *
     * @return a formatted string representing the header for student views.
     */
    public static String getViewHeader() {
        return StringUtils.padRight("ID", 10) + " | " +
               StringUtils.padRight("Name", 20) + " | " +
               StringUtils.padRight("Age", 10) + " | " +
               StringUtils.padRight("GPA", 10);
    }
}
