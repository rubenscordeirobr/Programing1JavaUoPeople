package models;

import java.time.LocalDate;

import utils.DateUtils;
import utils.StringUtils;

/**
 * The StudentEnrollment class represents an enrollment of a student in a
 * course, taught by a professor,
 * with a specific enrollment date and grade scale. It also includes methods to
 * calculate the letter grade
 * and grade points based on the grade scale.
 */
public class StudentEnrollment extends Model {

    private Student student;
    private Professor professor;
    private Course course;
    private LocalDate enrollmentDate;
    private double gradeScale = -1; // Default value indicating no grade assigned yet.

    /**
     * Constructs a new StudentEnrollment with the specified student, course,
     * professor, and enrollment date.
     *
     * @param student        the student being enrolled.
     * @param course         the course the student is enrolling in.
     * @param professor      the professor teaching the course.
     * @param enrollmentDate the date of the enrollment.
     * @throws IllegalArgumentException if any of the parameters are null.
     */
    public StudentEnrollment(Student student, Course course, Professor professor, LocalDate enrollmentDate) {

        if (student == null || course == null || professor == null || enrollmentDate == null) {
            throw new IllegalArgumentException("Student, Course, Professor, and Enrollment Date must be provided");
        }

        this.student = student;
        this.course = course;
        this.professor = professor;
        this.enrollmentDate = enrollmentDate;
    }

    /**
     * Returns the unique ID of the enrollment, combining the student ID and course
     * ID.
     *
     * @return the enrollment ID.
     */
    public String getId() {
        return student.getId() + "_" + course.getId();
    }

    /**
     * Returns a description of the enrollment, including the student's name and the
     * course name.
     *
     * @return the enrollment name.
     */
    public String getName() {
        return student.getName() + " enroll " + course.getName();
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public Professor getProfessor() {
        return professor;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public double getGradeScale() {
        return gradeScale;
    }

    public void setGradeScale(double grade) {
        this.gradeScale = grade;
    }

    /**
     * Returns the letter grade based on the grade scale.
     *
     * @return the letter grade, or an empty string if no grade is assigned.
     */
    public String getLetterGrade() {
        if (this.gradeScale == -1) {
            return ""; // No grade assigned.
        }

        if (this.gradeScale >= 98)
            return "A+";
        if (this.gradeScale >= 93)
            return "A";
        if (this.gradeScale >= 90)
            return "A-";
        if (this.gradeScale >= 88)
            return "B+";
        if (this.gradeScale >= 83)
            return "B";
        if (this.gradeScale >= 80)
            return "B-";
        if (this.gradeScale >= 78)
            return "C+";
        if (this.gradeScale >= 73)
            return "C";
        if (this.gradeScale >= 70)
            return "C-";
        if (this.gradeScale >= 68)
            return "D+";
        if (this.gradeScale >= 63)
            return "D";
        if (this.gradeScale >= 60)
            return "D-";
        return "F"; // Below 60 is a failing grade.
    }

    /**
     * Returns the grade points corresponding to the letter grade.
     *
     * @return the grade points, or -1 if no grade is assigned.
     */
    public double getGradePoints() {
        String letterGrade = this.getLetterGrade();

        if (letterGrade.isEmpty()) {
            return -1; // No grade assigned.
        }

        switch (letterGrade) {
            case "A+":
            case "A":
                return 4.00;
            case "A-":
                return 3.67;
            case "B+":
                return 3.33;
            case "B":
                return 3.00;
            case "B-":
                return 2.67;
            case "C+":
                return 2.33;
            case "C":
                return 2.00;
            case "C-":
                return 1.67;
            case "D+":
                return 1.33;
            case "D":
                return 1.00;
            case "D-":
                return 0.67;
            case "F":
                return 0.00;
            default:
                throw new IllegalArgumentException("Invalid Letter Grade");
        }
    }

    /**
     * Returns a formatted string representing the enrollment details, including
     * student name, course name,
     * professor name, enrollment date, grade scale, and letter grade.
     *
     * @return a formatted string with enrollment details.
     */
    public String getView() {
        return StringUtils.padRight(this.getStudent().getName(), 20) + " | " +
                StringUtils.padRight(this.getCourse().getName(), 20) + " | " +
                StringUtils.padRight(this.getProfessor().getName(), 20) + " | " +
                StringUtils.padRight(DateUtils.formatDate(this.getEnrollmentDate()), 20) + " | " +
                StringUtils.padRight(String.valueOf(this.getGradeScale()), 10) + " | " +
                StringUtils.padRight(this.getLetterGrade(), 10);
    }

    /**
     * Returns a formatted header for displaying enrollment details.
     *
     * @param isIncludeStudent flag indicating whether to include the student's name
     *                         in the header.
     * @return a formatted string representing the header.
     */
    public static String getViewHeader(boolean isIncludeStudent) {
        String baseView = StringUtils.padRight("Course", 20) + " | " +
                StringUtils.padRight("Professor", 20) + " | " +
                StringUtils.padRight("Enrollment Date", 20) + " | " +
                StringUtils.padRight("Grade Scale", 10) + " | " +
                StringUtils.padRight("Letter Grade", 10);

        if (isIncludeStudent) {
            return StringUtils.padRight("Student", 20) + " | " + baseView;
        }
        return baseView;
    }
}
