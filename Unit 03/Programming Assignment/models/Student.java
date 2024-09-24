package models;

import java.time.LocalDate;
import java.util.ArrayList;

import utils.StringUtils;

/**
 * The Student class represents a student entity, which extends the Person
 * class.
 * It has additional attributes such as an ID, a list of enrollments, and the
 * ability to calculate GPA.
 */
public class Student extends Person {

    private static int incrementalId = 0; // Renamed to match Java naming conventions for static fields.

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
        double totalCourseGraded = 0;

        // Iterate through each enrollment and calculate grade points and credits
        for (StudentEnrollment enrollment : this.enrollments) {
            if (enrollment.getGradeScale() != -1) { // Check if a valid grade is available
                totalGradePoints += enrollment.getGradePoints();
                totalCourseGraded += 1; 
            }
        }
        return totalCourseGraded == 0 ? 0 : totalGradePoints / totalCourseGraded; // Avoid division by zero
    }

    /**
     * Enrolls the student in a course with a professor on the specified enrollment
     * date.
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
     * Checks if the student is enrolled in the specified course.
     *
     * @param course the course to check for enrollment.
     * @return true if the student is enrolled in the course, false otherwise.
     */
    public boolean isEnrolled(Course course) {
        return this.enrollments.stream().anyMatch(e -> e.getCourse().getId().equals(course.getId()));
    }

    /**
     * Sets the grade scale for the student in the specified course.
     *
     * @param course the course for which the grade scale is being set.
     * @param grade  the grade scale to set.
     * @throws IllegalArgumentException if the student is not enrolled in the
     *                                  course.
     */
    public void setGradeScale(Course course, double grade) {

        StudentEnrollment enrollment = this.enrollments.stream()
                .filter(e -> e.getCourse().getId().equals(course.getId())).findFirst().orElse(null);

        if (enrollment == null) {
            throw new IllegalArgumentException("Student is not enrolled in the course");
        }
        enrollment.setGradeScale(grade);
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
     * Returns a formatted string representing the student's details.
     * This method optionally includes additional details, such as enrollments and
     * GPA,
     * based on the value of the {@code isIncludeDetails} parameter.
     *
     * <p>
     * If {@code isIncludeDetails} is set to {@code true}, the returned string will
     * include a detailed view of the student's enrollments and GPA (Grade Point
     * Average).
     * If {@code isIncludeDetails} is set to {@code false}, only the basic student
     * information
     * (ID, name, age, and GPA) is returned.
     * </p>
     *
     * @param isIncludeDetails a flag that determines whether to include additional
     *                         details
     *                         such as the student's enrollments and GPA.
     *                         If {@code true}, the view will include detailed
     *                         information.
     *                         If {@code false}, only basic information will be
     *                         shown.
     * @return a formatted string containing the student's information. If
     *         {@code isIncludeDetails}
     *         is {@code true}, the string will include a list of enrollments and
     *         GPA.
     */
    public String getView(boolean isIncludeDetails) {

        if (!isIncludeDetails) {
            return getView();
        }

        StringBuilder sb = new StringBuilder();
        String view = getView();
        sb.append(view);

        if (this.enrollments.size() > 0) {

            sb.append("\n\n");
            sb.append(StringUtils.padRight("", 10)).append("Enrollments: \n");
            sb.append(StringUtils.padRight("", 10)).append(StudentEnrollment.getViewHeader(false)).append("\n");

            for (StudentEnrollment enrollment : this.enrollments) {

                sb.append(StringUtils.padRight("", 10));
                sb.append(enrollment.getView());
                sb.append("\n");
            }

            sb.append(StringUtils.padRight("", 10));
            sb.append("GPA (Grade Point Average): ");
            sb.append(String.format("%.2f", this.getGPA()));
            sb.append("\n");
        }

        return sb.toString();
    }

    /**
     * Returns a formatted header for displaying student details.
     *
     * @return a formatted string representing the header.
     */
    public static String getViewHeader() {
        return StringUtils.padRight("ID", 10) + " | " +
                StringUtils.padRight("Name", 20) + " | " +
                StringUtils.padRight("Age", 10) + " | " +
                StringUtils.padRight("GPA", 10);
    }
}
