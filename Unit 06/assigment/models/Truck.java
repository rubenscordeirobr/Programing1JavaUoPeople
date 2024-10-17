package models;
import enums.FuelType;
import enums.TransmissionType;
import enums.TruckType;
import enums.VehicleType;
import interfaces.TruckVehicle;

/**
 * Represents a specific type of Vehicle: Truck.
 * The Truck class extends VehicleBase and implements TruckVehicle.
 * It includes additional properties such as the payload capacity and truck
 * type.
 */
public class Truck extends VehicleBase implements TruckVehicle {

    private double cargoCapacity; // Cargo capacity of the truck in tons
    private TransmissionType transmissionType; // Transmission type of the truck (Manual or Automatic)
    private TruckType truckType; // Type of the truck (e.g., Pickup, Semi)

    /**
     * Constructs a new Truck with the specified make, model, year, fuel type, cargo
     * capacity, transmission type, and truck type.
     * 
     * @param make             the make (manufacturer) of the truck.
     * @param model            the model of the truck.
     * @param year             the manufacturing year of the truck.
     * @param fuelType         the type of fuel the truck uses.
     * @param cargoCapacity    the cargo capacity of the truck in tons.
     * @param transmissionType the transmission type of the truck (Manual or
     *                         Automatic).
     * @param truckType        the specific type of truck (e.g., Pickup, Semi).
     */
    public Truck(String make, String model, int year, FuelType fuelType, double cargoCapacity,
            TransmissionType transmissionType, TruckType truckType) {
        super(make, model, year, fuelType);
        this.cargoCapacity = cargoCapacity;
        this.transmissionType = transmissionType;
        this.truckType = truckType;
    }

    /**
     * Constructs a new Truck using an instance of VehicleData along with additional
     * truck-specific properties.
     * 
     * @param data             an instance of VehicleData containing the make,
     *                         model, year, and fuel type.
     * @param cargoCapacity    the cargo capacity of the truck in tons.
     * @param transmissionType the transmission type of the truck (Manual or
     *                         Automatic).
     * @param truckType        the specific type of truck (e.g., Pickup, Semi).
     */
    public Truck(VehicleData data, double cargoCapacity, TransmissionType transmissionType, TruckType truckType) {
        super(data);
        this.cargoCapacity = cargoCapacity;
        this.transmissionType = transmissionType;
        this.truckType = truckType;
    }

    /**
     * Retrieves the vehicle type as a VehicleType enum.
     * For this class, it always returns VehicleType.TRUCK.
     * 
     * @return the vehicle type, which is always VehicleType.TRUCK.
     */
    @Override
    public VehicleType getVehicleType() {
        return VehicleType.TRUCK;
    }

    /**
     * Retrieves the cargo capacity of the truck in tons.
     * 
     * @return the cargo capacity of the truck.
     */
    @Override
    public double getCargoCapacity() {
        return this.cargoCapacity;
    }

    /**
     * Retrieves the transmission type of the truck.
     * 
     * @return the transmission type (Manual or Automatic).
     */
    @Override
    public TransmissionType getTransmissionType() {
        return this.transmissionType;
    }

    /**
     * Retrieves the truck type as a TruckType enum.
     * 
     * @return the specific type of the truck (e.g., Pickup, Semi).
     */
    @Override
    public TruckType getTruckType() {
        return this.truckType;
    }

    /**
     * Sets the cargo capacity of the truck in tons.
     * 
     * @param capacity the cargo capacity to be set for the truck.
     */
    @Override
    public void setCargoCapacity(double capacity) {
        this.cargoCapacity = capacity;
    }

    /**
     * Sets the transmission type of the truck.
     * 
     * @param transmissionType the transmission type (Manual or Automatic) to be
     *                         set.
     */
    @Override
    public void setTransmissionType(TransmissionType transmissionType) {
        this.transmissionType = transmissionType;
    }

    /**
     * Sets the type of the truck.
     * 
     * @param truckType the specific type of truck (e.g., Pickup, Semi) to be set.
     */
    @Override
    public void setTruckType(TruckType truckType) {
        this.truckType = truckType;
    }

    /**
     * Returns a string representation of the truck, including details from the base
     * class (make, model, etc.)
     * and truck-specific details (cargo capacity, transmission type, truck type).
     * 
     * @return a String representation of the truck's details.
     */
    @Override
    public String toString() {
        return super.toString() +
                "Cargo Capacity: " + this.cargoCapacity + " tons\n" +
                "Transmission Type: " + this.transmissionType.getName() + "\n" +
                "Truck Type: " + this.truckType.getName() + "\n";
    }

}

