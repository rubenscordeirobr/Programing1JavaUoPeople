package interfaces;
import enums.CarType;
import enums.TransmissionType;

/**
 * The CarVehicle interface extends the Vehicle interface, adding specific
 * properties and behaviors for cars.
 * This interface includes additional methods related to cars, such as the
 * number of doors, car type, and transmission type.
 * Extending Vehicle demonstrates inheritance, a key concept of OOP that allows
 * us to create specialized vehicle types.
 */
public interface CarVehicle extends Vehicle {
    /**
     * Retrieves the number of doors the car has.
     * 
     * @return an integer representing the number of doors on the car.
     */
    int getNumberOfDoors();

    /**
     * Retrieves the type of car.
     * 
     * @return a CarType enum representing the specific type of the car (e.g.,
     *         sedan, SUV).
     */
    CarType getCarType();

    /**
     * Retrieves the transmission type of the car.
     * 
     * @return a TransmissionType enum representing the type of transmission (e.g.,
     *         manual, automatic).
     */
    TransmissionType getTransmissionType();

    /**
     * Sets the number of doors for the car.
     * 
     * @param doors an integer representing the number of doors to be set.
     */
    void setNumberOfDoors(int doors);

    /**
     * Sets the type of the car.
     * 
     * @param carType a CarType enum representing the type of car.
     */
    void setCarType(CarType carType);

    /**
     * Sets the transmission type of the car.
     * 
     * @param transmissionType a TransmissionType enum representing the transmission
     *                         type of the car.
     */
    void setTransmissionType(TransmissionType transmissionType);
}
