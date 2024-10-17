package enums;
/**
 * Enum that represents different types of motorcycles, each with a name and
 * description.
 * Implements the EnumDescription interface to provide detailed information
 * about each motorcycle type.
 */
public enum MotorcycleType implements EnumDescription {
    SPORT("Sport", "High-performance motorcycle"),
    CRUISER("Cruiser", "Motorcycle designed for cruising on highways"),
    TOURING("Touring", "Motorcycle designed for long-distance travel"),
    STANDARD("Standard", "Versatile motorcycle for general-purpose riding"),
    DIRT_BIKE("Dirt Bike", "Off-road motorcycle"),
    SCOOTER("Scooter", "Small motorcycle with a step-through frame");

    private final String name;
    private final String description;

    MotorcycleType(String name, String description) {
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
