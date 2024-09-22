package Models;

import java.time.LocalDate;

import Utils.DateUtils;
import Utils.StringUtils;

public class StudentEnrollment extends Model {

    private Student student;
    private Professor professor;
    private Course course;
    private LocalDate enrollmentDate;
    private double gradeScale;

    public StudentEnrollment(Student student, Course course, Professor professor, LocalDate enrollmentDate) {
        this.student = student;
        this.course = course;
        this.professor = professor;
        this.enrollmentDate = enrollmentDate;
    }

    public String getId() {
        return student.getId() + "_" + course.getId();
    }

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

    public String getLetterGrade() {

        if (this.gradeScale == -1) {

            return "";
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

        return "F";

    }

    public double getGradePoints() {

        String letterGrade = this.getLetterGrade();

        if (letterGrade == "")
            return -1;

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

    public String getView() {

        return StringUtils.padRight(this.getStudent().getName(), 20) + " | " +
                StringUtils.padRight(this.getCourse().getName(), 20) + " | " +
                StringUtils.padRight(this.getProfessor().getName(), 20) + " | " +
                StringUtils.padRight(DateUtils.formatDate( this.getEnrollmentDate()), 20) + " | " +
                StringUtils.padRight(this.getGradeScale(), 10) + " | " +
                StringUtils.padRight(this.getLetterGrade(), 10);
    }

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
