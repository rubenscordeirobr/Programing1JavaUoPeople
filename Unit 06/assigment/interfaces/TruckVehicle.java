package interfaces;
import enums.TransmissionType;
import enums.TruckType;

/**
 * The TruckVehicle interface extends the Vehicle interface, adding specific
 * properties and behaviors for trucks.
 * This interface includes additional methods related to trucks, such as cargo
 * capacity, truck type, and transmission type.
 * Extending Vehicle demonstrates inheritance, allowing us to create specialized
 * vehicle types like trucks.
 */
public interface TruckVehicle extends Vehicle {
    /**
     * Retrieves the cargo capacity of the truck.
     * 
     * @return a double representing the cargo capacity in tons.
     */
    double getCargoCapacity();

    /**
     * Retrieves the transmission type of the truck.
     * 
     * @return a TransmissionType enum representing the type of transmission (e.g.,
     *         manual, automatic).
     */
    TransmissionType getTransmissionType();

    /**
     * Retrieves the type of truck.
     * 
     * @return a TruckType enum representing the specific type of the truck (e.g.,
     *         pickup, lorry).
     */
    TruckType getTruckType();

    /**
     * Sets the cargo capacity of the truck.
     * 
     * @param capacity a double representing the cargo capacity in tons.
     */
    void setCargoCapacity(double capacity);

    /**
     * Sets the transmission type of the truck.
     * 
     * @param transmissionType a TransmissionType enum representing the type of
     *                         transmission.
     */
    void setTransmissionType(TransmissionType transmissionType);

    /**
     * Sets the type of the truck.
     * 
     * @param truckType a TruckType enum representing the type of truck.
     */
    void setTruckType(TruckType truckType);
}
