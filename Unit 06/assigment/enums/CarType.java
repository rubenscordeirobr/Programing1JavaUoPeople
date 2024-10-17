package enums;
/**
 * Enum that represents different types of cars, each with a name and
 * description.
 * Implements the EnumDescription interface to provide detailed information
 * about each car type.
 */
public enum CarType implements EnumDescription {
    SEDAN("Sedan", "A passenger car in a three-box configuration"),
    HATCHBACK("Hatchback", "A car with a hatch-type rear door"),
    CONVERTIBLE("Convertible", "A car with a retractable roof"),
    COUPE("Coupe", "A two-door car with a sloping rear roofline"),
    WAGON("Wagon", "A car with a longer body for more cargo space"),
    SUV("SUV", "Sport Utility Vehicle"),
    TRUCK("Truck", "Larger vehicle for cargo transport"),
    VAN("Van", "A larger vehicle often used for transportation of goods or people");

    private final String name;
    private final String description;

    CarType(String name, String description) {
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