package interfaces;
import enums.FuelType;
import enums.VehicleType;

/**
 * The Vehicle interface defines a blueprint for different types of vehicles.
 * It provides method signatures for getting and setting various properties of a
 * vehicle,
 * such as its make, model, year, and fuel type. This interface ensures that any
 * class
 * implementing it will provide the specified behavior, promoting consistency
 * and abstraction.
 */
public interface Vehicle {
    /**
     * Retrieves the unique identifier for the vehicle.
     * 
     * @return an integer representing the vehicle's unique ID.
     */
    int getId();

    /**
     * Retrieves a description of the vehicle.
     * 
     * @return a String containing detailed information about the vehicle.
     */
    String getDescription();

    /**
     * Retrieves the make of the vehicle.
     * 
     * @return a String representing the vehicle's make (e.g., "Toyota", "Ford").
     */
    String getMake();

    /**
     * Retrieves the model of the vehicle.
     * 
     * @return a String representing the vehicle's model (e.g., "Corolla",
     *         "Mustang").
     */
    String getModel();

    /**
     * Retrieves the manufacturing year of the vehicle.
     * 
     * @return an integer representing the year the vehicle was manufactured.
     */
    int getYear();

    /**
     * Retrieves the fuel type of the vehicle.
     * 
     * @return a FuelType enum representing the type of fuel the vehicle uses (e.g.,
     *         gasoline, electric).
     */
    FuelType getFuelType();

    /**
     * Retrieves the type of the vehicle.
     * 
     * @return a VehicleType enum representing the type of vehicle (e.g., car,
     *         truck, motorcycle).
     */
    VehicleType getVehicleType();

    /**
     * Sets the make of the vehicle.
     * 
     * @param make a String representing the make of the vehicle.
     */
    void setMake(String make);

    /**
     * Sets the model of the vehicle.
     * 
     * @param model a String representing the model of the vehicle.
     */
    void setModel(String model);

    /**
     * Sets the manufacturing year of the vehicle.
     * 
     * @param year an integer representing the year the vehicle was manufactured.
     */
    void setYear(int year);

    /**
     * Sets the fuel type of the vehicle.
     * 
     * @param fuelType a FuelType enum representing the type of fuel the vehicle
     *                 uses.
     */
    void setFuelType(FuelType fuelType);
}
