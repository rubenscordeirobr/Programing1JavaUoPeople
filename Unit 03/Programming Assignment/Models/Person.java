package Models;

import java.time.LocalDate;
import Utils.DateUtils;

/**
 * The Person class is an abstract representation of a person entity.
 * It extends the Model class and provides common attributes and methods for
 * persons,
 * such as name, birthdate, and age calculation.
 */
abstract class Person extends Model {

    private String name;
    private LocalDate birthDate;

    /**
     * Constructs a Person object with the provided name and birth date.
     * The birthDate is parsed using the utility method tryDateParser.
     *
     * @param name      the name of the person.
     * @param birthDate the birth date of the person in String format.
     */
    protected Person(String name, String birthDate) {
        this.name = name;
        this.birthDate = DateUtils.tryDateParser(birthDate, LocalDate.now());
    }

    /**
     * Returns the name of the person.
     *
     * @return the name of the person.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the birth date of the person.
     *
     * @return the birth date of the person.
     */
    public LocalDate getBirthDate() {
        return birthDate;
    }

    /**
     * Calculates and returns the age of the person.
     *
     * @return the age of the person in years.
     */
    public int getAge() {
        return DateUtils.calculateAge(this.birthDate);
    }

    /**
     * Returns a full description of the person, including their name and age.
     *
     * @return a String representing the full description of the person.
     */
    public String getFullDescription() {
        return this.name + " - " + this.getAge() + " years old";
    }

    /**
     * Sets a new name for the person.
     *
     * @param name the new name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets a new birth date for the person.
     *
     * @param birthDate the new birth date to set as a LocalDate object.
     */
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
