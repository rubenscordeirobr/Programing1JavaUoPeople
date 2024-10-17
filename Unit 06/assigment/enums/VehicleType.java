package enums;
/**
 * Enum that represents different types of vehicles, each with a name and
 * description.
 * Implements the EnumDescription interface to provide detailed information
 * about each vehicle type.
 */
public enum VehicleType implements EnumDescription {
    CAR("Car", "Four-wheeled motor vehicle"),
    MOTORCYCLE("Motorcycle", "Two or three-wheeled motor vehicle"),
    TRUCK("Truck", "Large motor vehicle used for transporting goods");

    private final String name;
    private final String description;

    VehicleType(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public String getFullDescription() {
        return this.name + " - " + this.description;
    }
}
