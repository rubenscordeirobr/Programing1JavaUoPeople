package models;
import enums.FuelType;
import enums.VehicleType;
import interfaces.Vehicle;

/**
 * Abstract class representing the base for all vehicle types.
 * Implements the Vehicle interface and provides common functionality
 * such as make, model, year, and fuel type.
 */
public abstract class VehicleBase implements Vehicle {
    private static int nextId = 1; // Static counter to assign unique IDs to each vehicle

    private int id; // Unique identifier for the vehicle
    private String make; // Manufacturer of the vehicle
    private String model; // Model of the vehicle
    private int year; // Manufacturing year of the vehicle
    private FuelType fuelType; // Type of fuel the vehicle uses

    /**
     * Constructs a new VehicleBase with the specified make, model, year, and fuel
     * type.
     * The vehicle is automatically assigned a unique ID.
     * 
     * @param make     the make (manufacturer) of the vehicle.
     * @param model    the model of the vehicle.
     * @param year     the manufacturing year of the vehicle.
     * @param fuelType the type of fuel the vehicle uses.
     */
    public VehicleBase(String make, String model, int year, FuelType fuelType) {
        this.id = nextId++; // Assigns a unique ID and increments the counter
        this.make = make;
        this.model = model;
        this.year = year;
        this.fuelType = fuelType;
    }

    /**
     * Constructs a new VehicleBase using an instance of VehicleData (or
     * VehicleInitData).
     * The vehicle is automatically assigned a unique ID.
     * 
     * @param data an instance of VehicleData containing the make, model, year, and
     *             fuel type.
     */
    public VehicleBase(VehicleData data) {
        this(data.make, data.model, data.year, data.fuelType); // Calls the main constructor
    }

    /**
     * Retrieves the unique ID of the vehicle.
     * 
     * @return the unique ID of the vehicle.
     */
    @Override
    public int getId() {
        return this.id;
    }

    /**
     * Provides a description of the vehicle, including type, make, model, and year.
     * 
     * @return a String describing the vehicle.
     */
    @Override
    public String getDescription() {
        return this.getVehicleType().getName() + " - " + this.make + " " + this.model + " (" + this.year + ")";
    }

    /**
     * Retrieves the make (manufacturer) of the vehicle.
     * 
     * @return the make of the vehicle.
     */
    @Override
    public String getMake() {
        return this.make;
    }

    /**
     * Retrieves the model of the vehicle.
     * 
     * @return the model of the vehicle.
     */
    @Override
    public String getModel() {
        return this.model;
    }

    /**
     * Retrieves the manufacturing year of the vehicle.
     * 
     * @return the year of the vehicle.
     */
    @Override
    public int getYear() {
        return this.year;
    }

    /**
     * Retrieves the type of fuel the vehicle uses.
     * 
     * @return the fuel type of the vehicle.
     */
    @Override
    public FuelType getFuelType() {
        return this.fuelType;
    }

    /**
     * Sets the make (manufacturer) of the vehicle.
     * 
     * @param make the make of the vehicle.
     */
    @Override
    public void setMake(String make) {
        this.make = make;
    }

    /**
     * Sets the model of the vehicle.
     * 
     * @param model the model of the vehicle.
     */
    @Override
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Sets the manufacturing year of the vehicle.
     * 
     * @param year the year of the vehicle.
     */
    @Override
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Sets the type of fuel the vehicle uses.
     * 
     * @param fuelType the fuel type of the vehicle.
     */
    @Override
    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    /**
     * Abstract method that must be implemented by subclasses to return the specific
     * type of the vehicle.
     * 
     * @return a VehicleType enum representing the type of the vehicle.
     */
    @Override
    public abstract VehicleType getVehicleType();

    /**
     * Returns a string representation of the vehicle with its details.
     * 
     * @return a String containing the vehicle's type, make, model, year, and fuel
     *         type.
     */
    @Override
    public String toString() {
        return "Type of Vehicle: " + this.getVehicleType().getName() + "\n" +
                "Make: " + this.make + "\n" +
                "Model: " + this.model + "\n" +
                "Year: " + this.year + "\n" +
                "Fuel Type: " + this.fuelType.getName() + "\n";
    }

    /**
     * Retrieves the next available unique ID for a new vehicle.
     * 
     * @return an integer representing the next available ID.
     */
    public static int getNextId() {
        return VehicleBase.nextId;
    }
}