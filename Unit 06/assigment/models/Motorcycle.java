package models;
import enums.FuelType;
import enums.MotorcycleType;
import enums.VehicleType;
import interfaces.MotorcycleVehicle;

/**
 * Represents a specific type of Vehicle: Motorcycle.
 * The Motorcycle class extends VehicleBase and implements MotorcycleVehicle.
 * It includes additional properties such as the number of wheels and motorcycle
 * type.
 */
public class Motorcycle extends VehicleBase implements MotorcycleVehicle {

    private int numberOfWheels; // Number of wheels on the motorcycle
    private MotorcycleType motorcycleType; // Type of the motorcycle (e.g., Cruiser, Sport, Dirt Bike)

    /**
     * Constructs a new Motorcycle with the specified make, model, year, fuel type,
     * number of wheels, and motorcycle type.
     * 
     * @param make           the make (manufacturer) of the motorcycle.
     * @param model          the model of the motorcycle.
     * @param year           the manufacturing year of the motorcycle.
     * @param fuelType       the type of fuel the motorcycle uses.
     * @param numberOfWheels the number of wheels on the motorcycle.
     * @param motorcycleType the specific type of motorcycle (e.g., Cruiser, Sport).
     */
    public Motorcycle(String make, String model, int year, FuelType fuelType, int numberOfWheels,
            MotorcycleType motorcycleType) {
        super(make, model, year, fuelType);
        this.numberOfWheels = numberOfWheels;
        this.motorcycleType = motorcycleType;
    }

    /**
     * Constructs a new Motorcycle using an instance of VehicleData along with
     * additional motorcycle-specific properties.
     * 
     * @param data           an instance of VehicleData containing the make, model,
     *                       year, and fuel type.
     * @param numberOfWheels the number of wheels on the motorcycle.
     * @param motorcycleType the specific type of motorcycle (e.g., Cruiser, Sport).
     */
    public Motorcycle(VehicleData data, int numberOfWheels, MotorcycleType motorcycleType) {
        super(data);
        this.numberOfWheels = numberOfWheels;
        this.motorcycleType = motorcycleType;
    }

    /**
     * Retrieves the vehicle type as a VehicleType enum.
     * For this class, it always returns VehicleType.MOTORCYCLE.
     * 
     * @return the vehicle type, which is always VehicleType.MOTORCYCLE.
     */
    @Override
    public VehicleType getVehicleType() {
        return VehicleType.MOTORCYCLE;
    }

    /**
     * Retrieves the number of wheels on the motorcycle.
     * 
     * @return the number of wheels on the motorcycle.
     */
    @Override
    public int getNumberOfWheels() {
        return this.numberOfWheels;
    }

    /**
     * Retrieves the motorcycle type as a MotorcycleType enum.
     * 
     * @return the specific type of the motorcycle (e.g., Cruiser, Sport).
     */
    @Override
    public MotorcycleType getMotorcycleType() {
        return this.motorcycleType;
    }

    /**
     * Sets the number of wheels for the motorcycle.
     * 
     * @param wheels the number of wheels to be set for the motorcycle.
     */
    @Override
    public void setNumberOfWheels(int wheels) {
        this.numberOfWheels = wheels;
    }

    /**
     * Sets the motorcycle type.
     * 
     * @param motorcycleType the specific type of motorcycle (e.g., Cruiser, Sport)
     *                       to be set.
     */
    @Override
    public void setMotorcycleType(MotorcycleType motorcycleType) {
        this.motorcycleType = motorcycleType;
    }

    /**
     * Returns a string representation of the motorcycle, including details from the
     * base class (make, model, etc.)
     * and motorcycle-specific details (number of wheels, motorcycle type).
     * 
     * @return a String representation of the motorcycle's details.
     */
    @Override
    public String toString() {
        return super.toString() +
                "Number of Wheels: " + this.numberOfWheels + "\n" +
                "Motorcycle Type: " + this.motorcycleType.getName() + "\n";
    }
}
