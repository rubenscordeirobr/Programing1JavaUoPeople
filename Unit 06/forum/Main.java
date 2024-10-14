import java.util.ArrayList;
import java.util.List;

// Interface that defines the contract for model operations.
// This interface promotes polymorphism by allowing multiple classes to implement common behaviors.
interface IModel {
    boolean isValid();  // Each model must define its validation logic.
    boolean save();     // Each model must define how to save itself.
    boolean update();   // Each model must define how to update itself.
    boolean delete();   // Each model must define how to delete itself.
}

// Abstract class Model implementing IModel.
// Demonstrates inheritance and provides base logic for saving, updating, and deleting models.
abstract class Model implements IModel {

    // Abstract method to validate the model. Subclasses must override this method.
    // Demonstrates method overriding, as subclasses provide their own validation logic.
    public abstract boolean isValid();

    // Save method with default behavior.
    // This method demonstrates dynamic binding, as the exact save logic is defined at runtime based on the subclass.
    public boolean save() {
        return save(true); // Calls the overloaded save method with exception control.
    }

    // Overloaded save method that handles whether to throw an exception or not.
    // Demonstrates method overriding since subclasses can override the save logic if needed.
    public boolean save(boolean throwException) {
        if (!isValid()) {
            String className = this.getClass().getSimpleName();
            String errorMessage = String.format("The model %s is not valid", className);

            if (throwException) {
                throw new RuntimeException(errorMessage);
            }

            System.err.println(errorMessage);
            return false;
        }

        try {
            return internalSave(); // Calls the internal save logic (dynamic binding).
        } catch (Exception e) {
            String className = this.getClass().getSimpleName();
            String errorMessage = String.format("Error while saving the model %s. Error: %s", className, e.getMessage());

            if (throwException) {
                throw new RuntimeException(errorMessage);
            }

            System.err.println(errorMessage);
            return false;
        }
    }

    // Public method to update a model. Subclasses can override this method if needed.
    public boolean update() {
        return update(true);
    }

    // Overloaded update method with exception handling.
    public boolean update(boolean throwException) {
        throw new UnsupportedOperationException("Update method not implemented yet.");
    }

    // Public method to delete a model.
    public boolean delete() {
        return delete(true);
    }

    // Overloaded delete method with exception handling.
    public boolean delete(boolean throwException) {
        throw new UnsupportedOperationException("Delete method not implemented yet.");
    }

    // Abstract method for subclasses to provide their own save logic.
    // Demonstrates polymorphism: Subclasses can implement this differently.
    protected abstract boolean internalSave();
}

// Abstract Person class that extends Model and implements the isValid method.
// Demonstrates inheritance: Person inherits from Model and gains its functionality.
abstract class Person extends Model {

    // Encapsulated fields (demonstrating encapsulation).
    private String name;
    private int age;

    // Constructor to initialize Person objects.
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Implements the isValid method from Model.
    // Demonstrates method overriding, as each subclass can customize validation logic.
    @Override
    public boolean isValid() {
        return name != null && !name.isEmpty() && age > 0;
    }

    // Getter methods for encapsulated fields (demonstrating encapsulation).
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}

// Concrete Student class that extends Person.
// Demonstrates inheritance and polymorphism.
class Student extends Person {

    // Constructor for Student class.
    public Student(String name, int age) {
        super(name, age); // Calls the constructor of Person (superclass).
    }

    // Overriding the isValid method from Person.
    // Demonstrates method overriding.
    @Override
    public boolean isValid() {
        return super.isValid(); // Calls the parent class's validation method.
    }

    // Implements the internalSave method.
    // Demonstrates polymorphism and dynamic binding: Actual method is called at runtime.
    @Override
    protected boolean internalSave() {
        // Actual save logic goes here
        System.out.println("Saving Student to the database...");
        return true; // Returns true to indicate success
    }
}

// Concrete Professor class that extends Person and adds an additional field.
// Demonstrates inheritance, polymorphism, and method overriding.
class Professor extends Person {

    // Additional field specific to Professor.
    private String department;

    // Constructor for Professor class.
    public Professor(String name, String department, int age) {
        super(name, age); // Calls the constructor of Person (superclass).
        this.department = department; // Initialize the department field.
    }

    // Overriding the isValid method to add department-specific validation.
    // Demonstrates method overriding.
    @Override
    public boolean isValid() {
        return super.isValid() && department != null && !department.isEmpty();
    }

    // Implements the internalSave method.
    // Demonstrates polymorphism and dynamic binding: Actual method is called at runtime.
    @Override
    protected boolean internalSave() {
        // Actual save logic goes here
        System.out.println("Saving Professor to the database...");
        return true; // Returns true to indicate success
    }

    // Getter for the department field (demonstrating encapsulation).
    public String getDepartment() {
        return department;
    }
}

// Main class demonstrating the use of polymorphism, dynamic binding, inheritance, and method overriding.
public class Main {
    public static void main(String[] args) {

        // Create a list of Person objects (both Students and Professors).
        // Demonstrates polymorphism: Both Student and Professor can be added to the list as they inherit from Person.
        List<Person> people = new ArrayList<>();

        // Add Student and Professor objects to the list (valid and invalid objects).
        people.add(new Student("Alice", 22));             // Valid Student
        people.add(new Student("John", -5));              // Invalid Student (negative age)
        people.add(new Professor("Dr. Smith", "Physics", 45)); // Valid Professor
        people.add(new Professor("Dr. Jane", "", 50));    // Invalid Professor (empty department)

        // Loop through the list and check if each person is valid.
        for (Person person : people) {
            // Demonstrates dynamic binding: The actual method called depends on the runtime object type (Student or Professor).
            if (person.isValid()) {
                // If valid, save the person (calls internalSave() via dynamic binding).
                System.out.println(person.getClass().getSimpleName() + " is valid. Saving...");
                person.save();  // Dynamic binding ensures the correct save method is called.
            } else {
                // If not valid, print an error message.
                System.err.println(person.getClass().getSimpleName() + " is not valid.");
            }
        }
    }
}
