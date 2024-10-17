package enums;
 /**
 * Enum that represents different types of transmission, each with a name and
 * description.
 * Implements the EnumDescription interface to provide detailed information
 * about each transmission type.
 */
public enum TransmissionType implements EnumDescription {
    MANUAL("Manual", "Driver shifts gears manually"),
    AUTOMATIC("Automatic", "Gear shifting is automated");

    private final String name;
    private final String description;

    TransmissionType(String name, String description) {
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
