import java.util.HashMap;
import java.util.Map;

import enums.CarType;
import enums.EnumDescription;
import enums.FuelType;
import enums.MotorcycleType;
import enums.TransmissionType;
import enums.TruckType;
import enums.VehicleType;
import interfaces.Vehicle;
import models.Car;
import models.Motorcycle;
import models.Truck;
import models.VehicleBase;
import models.VehicleData;
import utils.InputUtils;
import utils.PrintUtils;



/**
 * The Main class manages a fleet of vehicles, allowing users to view, add,
 * update, and delete vehicles
 * such as cars, motorcycles, and trucks in a rental system.
 */
public class Main {

    // Static map to hold the vehicles, keyed by their ID
    static Map<Integer, Vehicle> vehicles = new HashMap<Integer, Vehicle>();

    /**
     * The main method runs the vehicle rental system, continuously displaying the
     * menu to the user.
     * 
     * @param args command-line arguments (not used).
     */
    public static void main(String[] args) {

        populateVehicles(); // Initializes the system with predefined vehicles

        // Continuously displays the menu until the user exits
        while (true) {
            showMenu(); // Shows the main menu
        }
    }

    /**
     * Displays the main menu and handles the user's selection by calling the
     * appropriate methods.
     * The user can perform actions such as viewing, adding, and updating vehicles.
     */
    public static void showMenu() {

        System.out.println("Welcome to Vehicle Rental System");
        System.out.println("Please select an option  or type q or quit to any time.");

        // Menu options
        System.out.println("1. Show all vehicles");
        System.out.println("2. Show all cars");
        System.out.println("3. Show all motorcycles");
        System.out.println("4. Show all trucks");
        System.out.println("5. Add a new vehicle");
        System.out.println("6. Update a vehicle");
        System.out.println("7. Delete a vehicle");
        System.out.println("0. Exit");

        // Get user input for menu selection
        int option = InputUtils.getInt("Enter your choice: ", 0, 7);

        // Handle the user's selection
        switch (option) {
            case 0:
                System.exit(0);
                break;
            case 1:
                showAllVehicles();
                break;
            case 2:
                showAllCars();
                break;
            case 3:
                showAllMotorcycles();
                break;
            case 4:
                showAllTrucks();
                break;
            case 5:
                addNewVehicle();
                break;
            case 6:
                updateVehicle();
                break;
            case 7:
                // Delete vehicle
                updateVehicle();
                break;
            default:
                System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                break;
        }
    }

    /**
     * Shows all vehicles in the system.
     */
    public static void showAllVehicles() {
        System.out.println("All vehicles:");
        showAllVehicles(null); // Displays all vehicles, no filtering by type
    }

    /**
     * Shows all cars in the system.
     */
    public static void showAllCars() {
        System.out.println("All cars:");
        showAllVehicles(VehicleType.CAR); // Displays only cars
    }

    /**
     * Shows all motorcycles in the system.
     */
    public static void showAllMotorcycles() {
        System.out.println("All motorcycles:");
        showAllVehicles(VehicleType.MOTORCYCLE); // Displays only motorcycles
    }

    /**
     * Shows all trucks in the system.
     */
    public static void showAllTrucks() {
        System.out.println("All trucks:");
        showAllVehicles(VehicleType.TRUCK); // Displays only trucks
    }

    /**
     * Shows all vehicles in the system, optionally filtering by a specific vehicle
     * type.
     * 
     * @param vehicleType the type of vehicle to filter by (optional), or null to
     *                    show all vehicles.
     */
    public static void showAllVehicles(VehicleType vehicleType) {
       
        // Loop through all vehicles in the map
        for (Map.Entry<Integer, Vehicle> entry : vehicles.entrySet()) {
            Vehicle vehicle = entry.getValue();
            // Show vehicle if type matches or if no filter is applied
            if (vehicleType == null || vehicle.getVehicleType() == vehicleType) {
                System.out.println(vehicle.toString());
            }
        }
    }

    /**
     * Prompts the user to add a new vehicle, and directs the user to the
     * appropriate method
     * depending on the type of vehicle selected (car, motorcycle, or truck).
     */
    public static void addNewVehicle() {
        // Ask the user for the type of vehicle to add
        VehicleType vehicleType = getUserSelection(VehicleType.class, "Select the type of vehicle to add: ");

        // Call the appropriate method based on the vehicle type
        switch (vehicleType) {
            case CAR:
                addNewCar();
                break;
            case MOTORCYCLE:
                addNewMotorcycle();
                break;
            case TRUCK:
                addNewTruck();
                break;
        }
    }

    /**
     * Adds a new car by collecting car-specific data from the user.
     */
    static void addNewCar() {
        
        VehicleData data = getVehicleData(); // Get basic vehicle data
        int numberOfDoors = InputUtils.getInt("Enter the number of doors: ", 2, 6);
        CarType carType = getUserSelection(CarType.class, "Select the car type: ");
        TransmissionType transmissionType = getUserSelection(TransmissionType.class, "Select the transmission type: ");

        // Create a new Car object and add it to the system
        Car car = new Car(data, numberOfDoors, carType, transmissionType);
        addVehicle(car);

        PrintUtils.printSuccess(car.getDescription() + " added successfully.");
    }

    /**
     * Adds a new motorcycle by collecting motorcycle-specific data from the user.
     */
    static void addNewMotorcycle() {
        
        VehicleData data = getVehicleData(); // Get basic vehicle data
        int numberOfWheels = InputUtils.getInt("Enter the number of wheels: ", 2, 3);
        MotorcycleType motorcycleType = getUserSelection(MotorcycleType.class, "Select the motorcycle type: ");

        // Create a new Motorcycle object and add it to the system
        Motorcycle motorcycle = new Motorcycle(data, numberOfWheels, motorcycleType);
        addVehicle(motorcycle);

        PrintUtils.printSuccess(motorcycle.getDescription() + " added successfully.");
    }

    /**
     * Adds a new truck by collecting truck-specific data from the user.
     */
    static void addNewTruck() {
       
        VehicleData data = getVehicleData(); // Get basic vehicle data
        double cargoCapacity = InputUtils.getDouble("Enter the cargo capacity: ", 0, 10000);
        TransmissionType transmissionType = getUserSelection(TransmissionType.class, "Select the transmission type: ");
        TruckType truckType = getUserSelection(TruckType.class, "Select the truck type: ");

        // Create a new Truck object and add it to the system
        Truck truck = new Truck(data, cargoCapacity, transmissionType, truckType);
        addVehicle(truck);

        PrintUtils.printSuccess(truck.getDescription() + " added successfully.");
    }

    /**
     * Collects basic vehicle data from the user, such as make, model, year, and
     * fuel type.
     * 
     * @return a VehicleData object containing the collected data.
     */
    static VehicleData getVehicleData() {
       
        VehicleData data = new VehicleData();
        data.make = InputUtils.getString("Enter the make of the vehicle: ", true);
        data.model = InputUtils.getString("Enter the model of the vehicle: ", true);
        data.year = InputUtils.getInt("Enter the year of the vehicle: ", 1900, 2024);
        data.fuelType = getUserSelection(FuelType.class, "Select the fuel type of the vehicle: ");
        return data;
    }

    /**
     * Updates an existing vehicle by allowing the user to modify its details.
     */
    static void updateVehicle() {
        
        // Show all vehicles to choose from
        showAllVehicles(); 

        int maxId = VehicleBase.getNextId() - 1;
        int id = InputUtils.getInt("Enter the ID of the vehicle you want to update: ", 1, maxId);

        // Fetch the vehicle from the map
        Vehicle vehicle = vehicles.get(id);
        if (vehicle == null) {
            PrintUtils.printFail("Vehicle with ID " + id + " not found.");
            return;
        }

        // Update vehicle based on its type
        VehicleType vehicleType = vehicle.getVehicleType();
        switch (vehicleType) {
            case CAR:
                updateCar((Car) vehicle);
                break;
            case MOTORCYCLE:
                updateMotorcycle((Motorcycle) vehicle);
                break;
            case TRUCK:
                updateTruck((Truck) vehicle);
                break;
        }
    }

    /**
     * Updates the details of a car by collecting new data from the user.
     * 
     * @param car the Car object to be updated.
     */
    static void updateCar(Car car) {
        
        VehicleData data = getVehicleData();
        int numberOfDoors = InputUtils.getInt("Enter the number of doors: ", 2, 6);
        CarType carType = getUserSelection(CarType.class, "Select the car type: ");
        TransmissionType transmissionType = getUserSelection(TransmissionType.class, "Select the transmission type: ");

        // Update the car object with new data
        car.setMake(data.make);
        car.setModel(data.model);
        car.setYear(data.year);
        car.setFuelType(data.fuelType);
        car.setNumberOfDoors(numberOfDoors);
        car.setCarType(carType);
        car.setTransmissionType(transmissionType);
    }

    /**
     * Updates the details of a motorcycle by collecting new data from the user.
     * 
     * @param motorcycle the Motorcycle object to be updated.
     */
    static void updateMotorcycle(Motorcycle motorcycle) {
        
        VehicleData data = getVehicleData();
        int numberOfWheels = InputUtils.getInt("Enter the number of wheels: ", 2, 3);
        MotorcycleType motorcycleType = getUserSelection(MotorcycleType.class, "Select the motorcycle type: ");

        // Update the motorcycle object with new data
        motorcycle.setMake(data.make);
        motorcycle.setModel(data.model);
        motorcycle.setYear(data.year);
        motorcycle.setFuelType(data.fuelType);
        motorcycle.setNumberOfWheels(numberOfWheels);
        motorcycle.setMotorcycleType(motorcycleType);
    }

    /**
     * Updates the details of a truck by collecting new data from the user.
     * 
     * @param truck the Truck object to be updated.
     */
    static void updateTruck(Truck truck) {
       
        VehicleData data = getVehicleData();
        double cargoCapacity = InputUtils.getDouble("Enter the cargo capacity: ", 0, 10000);
        TransmissionType transmissionType = getUserSelection(TransmissionType.class, "Select the transmission type: ");
        TruckType truckType = getUserSelection(TruckType.class, "Select the truck type: ");

        // Update the truck object with new data
        truck.setMake(data.make);
        truck.setModel(data.model);
        truck.setYear(data.year);
        truck.setFuelType(data.fuelType);
        truck.setCargoCapacity(cargoCapacity);
        truck.setTransmissionType(transmissionType);
        truck.setTruckType(truckType);
    }

    /**
     * Deletes a vehicle from the system.
     */
    static void deleteVehicle(){

        // Show all vehicles to choose from
        showAllVehicles(); 

        int maxId = VehicleBase.getNextId() - 1;
        int id = InputUtils.getInt("Enter the ID of the vehicle you want to delete: ", 1, maxId);

        // Fetch the vehicle from the map
        Vehicle vehicle = vehicles.get(id);
        if (vehicle == null) {
            PrintUtils.printFail("Vehicle with ID " + id + " not found.");
            return;
        }

        // Delete the vehicle
        vehicles.remove(id);
        PrintUtils.printSuccess(vehicle.getDescription() + " deleted successfully.");
    }

    /**
     * Populates the system with some predefined vehicles.
     */
    static void populateVehicles() {
       
        // Add sample cars
        Car corolla = new Car("Toyota", "Corolla", 2019, FuelType.PETROL, 4, CarType.SEDAN, TransmissionType.AUTOMATIC);
        Car mustang = new Car("Ford", "Mustang", 2019, FuelType.PETROL, 2, CarType.COUPE, TransmissionType.MANUAL);
        Car modelS = new Car("Tesla", "Model S", 2019, FuelType.ELECTRIC, 4, CarType.SEDAN, TransmissionType.AUTOMATIC);

        // Add sample motorcycles
        Motorcycle harley = new Motorcycle("Harley-Davidson", "Iron 883", 2019, FuelType.PETROL, 2,
                MotorcycleType.CRUISER);
        Motorcycle ninja = new Motorcycle("Kawasaki", "Ninja 300", 2019, FuelType.PETROL, 2, MotorcycleType.SPORT);

        // Add sample trucks
        Truck f150 = new Truck("Ford", "F-150", 2019, FuelType.PETROL, 1000, TransmissionType.AUTOMATIC,
                TruckType.PICKUP);

        // Add vehicles to the system
        addVehicle(corolla);
        addVehicle(mustang);
        addVehicle(modelS);
        addVehicle(harley);
        addVehicle(ninja);
        addVehicle(f150);
    }

    /**
     * Adds a new vehicle to the system's vehicle map.
     * 
     * @param vehicle the Vehicle object to add.
     */
    static void addVehicle(Vehicle vehicle) {
        vehicles.put(vehicle.getId(), vehicle); // Adds the vehicle to the map, using its ID as the key
    }

    /**
     * Generic method to get a user selection for any enum type with descriptions.
     * 
     * @param <T>      the enum type that implements EnumDescription.
     * @param enumType the class of the enum to display.
     * @param message  a prompt message to display to the user.
     * @return the selected enum value.
     */
    static <T extends Enum<T> & EnumDescription> T getUserSelection(Class<T> enumType, String message) {
      
        // Print the prompt message
        PrintUtils.printPrompt(message);
        //
        System.out.println();

        // Get all enum constants
        T[] enumConstants = enumType.getEnumConstants();

        // Display all options with descriptions
        for (int i = 0; i < enumConstants.length; i++) {

            // Print the option number and the full description
            System.out.println((i + 1) + ". " + enumConstants[i].getFullDescription());
        }

        // Get the user's selection
        int option = InputUtils.getInt("Enter the number of your choice: ", 1, enumConstants.length);
        return enumConstants[option - 1]; // Return the selected enum
    }

}
