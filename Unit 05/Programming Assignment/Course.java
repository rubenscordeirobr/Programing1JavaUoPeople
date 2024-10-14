import java.util.ArrayList;

/**
 * The Course class represents a course.
 * It includes attributes such as a unique code, name, capacity, and a list of enrolled students.
  */
public class Course {

    // Unique code for the course
    private String code;
    
    // Name of the course
    private String name;
    
    // Maximum number of students that can enroll in the course
    private int capacity;
    
    // List of students enrolled in the course
    private ArrayList<StudentEnrollment> studentsEnrolled = new ArrayList<>();

    /**
     * Constructs a Course object with the specified code, name, and capacity.
     *
     * @param code     the unique identifier for the course.
     * @param name     the name of the course.
     * @param capacity the maximum number of students that can be enrolled in the course.
     */
    public Course(String code, String name, int capacity) {
        this.code = code;
        this.name = name;
        this.capacity = capacity;
    }

    /**
     * Returns the unique code of the course.
     *
     * @return the code of the course.
     */
    public String getCode() {
        return code;
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
     * Sets the name of the course.
     *
     * @param name the new name of the course.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the maximum number of students that can be enrolled in the course.
     *
     * @return the maximum number of students that can be enrolled in the course.
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Sets the maximum number of students that can be enrolled in the course.
     *
     * @param capacity the new maximum number of students that can be enrolled in the course.
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Returns the description of the course, including its code and name.
     *
     * @return a string representing the description of the course.
     */
    public String getDescription() {
        return this.code + " -- " + this.name;
    }

    /**
     * Checks if the course is full.
     *
     * @return true if the number of students enrolled is equal to or exceeds the course capacity, false otherwise.
     */
    public boolean isFull() {
        return this.studentsEnrolled.size() >= capacity;
    }

    /**
     * Adds a student enrollment to the course if it is not full.
     *
     * @param enrollment the student enrollment to add.
     */
    public void addEnrollment(StudentEnrollment enrollment) {
        if (!isFull()) {
            this.studentsEnrolled.add(enrollment);
        } else {
            throw new IllegalStateException("Cannot add enrollment: Course is full.");
        }
    }
}
