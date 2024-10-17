package interfaces;
import enums.MotorcycleType;

/**
 * The MotorcycleVehicle interface extends the Vehicle interface, adding
 * specific properties and behaviors for motorcycles.
 * This interface includes additional methods related to motorcycles, such as
 * the number of wheels and the motorcycle type.
 * Extending Vehicle demonstrates inheritance, allowing us to create specialized
 * vehicle types like motorcycles.
 */
public interface MotorcycleVehicle extends Vehicle {
    /**
     * Retrieves the number of wheels the motorcycle has.
     * 
     * @return an integer representing the number of wheels on the motorcycle.
     */
    int getNumberOfWheels();

    /**
     * Retrieves the type of motorcycle.
     * 
     * @return a MotorcycleType enum representing the specific type of the
     *         motorcycle (e.g., cruiser, sportbike).
     */
    MotorcycleType getMotorcycleType();

    /**
     * Sets the number of wheels for the motorcycle.
     * 
     * @param wheels an integer representing the number of wheels to be set.
     */
    void setNumberOfWheels(int wheels);

    /**
     * Sets the type of the motorcycle.
     * 
     * @param motorcycleType a MotorcycleType enum representing the type of
     *                       motorcycle.
     */
    void setMotorcycleType(MotorcycleType motorcycleType);
}
