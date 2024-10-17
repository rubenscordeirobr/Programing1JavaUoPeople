package models;
import enums.CarType;
import enums.FuelType;
import enums.TransmissionType;
import enums.VehicleType;
import interfaces.CarVehicle;

/**
 * Represents a specific type of Vehicle: Car.
 * The Car class extends VehicleBase and implements CarVehicle.
 * It includes additional properties such as the number of doors, car type, and
 * transmission type.
 */
public class Car extends VehicleBase implements CarVehicle {

    private int numberOfDoors; // Number of doors in the car
    private CarType carType; // Type of the car (e.g., Sedan, SUV)
    private TransmissionType transmissionType; // Transmission type of the car (Manual or Automatic)

    /**
     * Constructs a new Car with the specified make, model, year, fuel type, number
     * of doors, car type, and transmission type.
     * 
     * @param make             the make (manufacturer) of the car.
     * @param model            the model of the car.
     * @param year             the manufacturing year of the car.
     * @param fuelType         the type of fuel the car uses.
     * @param numberOfDoors    the number of doors the car has.
     * @param carType          the specific type of car (e.g., Sedan, SUV).
     * @param transmissionType the transmission type of the car (Manual or
     *                         Automatic).
     */
    public Car(String make, String model, int year, FuelType fuelType, int numberOfDoors, CarType carType,
            TransmissionType transmissionType) {
        super(make, model, year, fuelType);
        this.numberOfDoors = numberOfDoors;
        this.carType = carType;
        this.transmissionType = transmissionType;
    }

    /**
     * Constructs a new Car using an instance of VehicleData along with additional
     * car-specific properties.
     * 
     * @param data             an instance of VehicleData containing the make,
     *                         model, year, and fuel type.
     * @param numberOfDoors    the number of doors the car has.
     * @param carType          the specific type of car (e.g., Sedan, SUV).
     * @param transmissionType the transmission type of the car (Manual or
     *                         Automatic).
     */
    public Car(VehicleData data, int numberOfDoors, CarType carType, TransmissionType transmissionType) {
        super(data);
        this.numberOfDoors = numberOfDoors;
        this.carType = carType;
        this.transmissionType = transmissionType;
    }

    /**
     * Retrieves the vehicle type as a VehicleType enum.
     * For this class, it always returns VehicleType.CAR.
     * 
     * @return the vehicle type, which is always VehicleType.CAR.
     */
    @Override
    public VehicleType getVehicleType() {
        return VehicleType.CAR;
    }

    /**
     * Retrieves the number of doors in the car.
     * 
     * @return the number of doors in the car.
     */
    @Override
    public int getNumberOfDoors() {
        return this.numberOfDoors;
    }

    /**
     * Retrieves the car type as a CarType enum.
     * 
     * @return the specific type of the car (e.g., Sedan, SUV).
     */
    @Override
    public CarType getCarType() {
        return this.carType;
    }

    /**
     * Retrieves the transmission type of the car.
     * 
     * @return the transmission type (Manual or Automatic).
     */
    @Override
    public TransmissionType getTransmissionType() {
        return this.transmissionType;
    }

    /**
     * Sets the number of doors for the car.
     * 
     * @param doors the number of doors to be set for the car.
     */
    @Override
    public void setNumberOfDoors(int doors) {
        this.numberOfDoors = doors;
    }

    /**
     * Sets the car type.
     * 
     * @param carType the specific type of car (e.g., Sedan, SUV) to be set.
     */
    @Override
    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    /**
     * Sets the transmission type of the car.
     * 
     * @param transmissionType the transmission type (Manual or Automatic) to be
     *                         set.
     */
    @Override
    public void setTransmissionType(TransmissionType transmissionType) {
        this.transmissionType = transmissionType;
    }

    /**
     * Returns a string representation of the car, including details from the base
     * class (make, model, etc.)
     * and car-specific details (number of doors, car type, transmission type).
     * 
     * @return a String representation of the car's details.
     */
    @Override
    public String toString() {
        return super.toString() +
                "Number of Doors: " + this.numberOfDoors + "\n" +
                "Car Type: " + this.carType.getName() + "\n" +
                "Transmission Type: " + this.transmissionType.getName() + "\n";
    }
}