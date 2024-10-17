package enums;
/**
 * Enum that represents different types of fuel, each with a name and
 * description.
 * Implements the EnumDescription interface to provide detailed information
 * about each fuel type.
 */
public enum FuelType implements EnumDescription {
    PETROL("Petrol", "Gasoline-powered engine"),
    DIESEL("Diesel", "Diesel-powered engine"),
    ELECTRIC("Electric", "Battery-powered motor"),
    HYBRID("Hybrid", "Combination of electric and gasoline/diesel engine");

    private final String name;
    private final String description;

    FuelType(String name, String description) {
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