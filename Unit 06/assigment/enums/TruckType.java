package enums;
/**
 * Enum that represents different types of trucks, each with a name and
 * description.
 * Implements the EnumDescription interface to provide detailed information
 * about each truck type.
 */
public enum TruckType implements EnumDescription {
    PICKUP("Pickup", "Light-duty truck with an open cargo area"),
    SEMI("Semi", "Large truck designed to haul freight"),
    DUMP("Dump", "Truck with a hydraulic lift for unloading cargo"),
    TANKER("Tanker", "Truck designed to carry liquids or gases"),
    BOX("Box", "Truck with an enclosed cargo area"),
    FLATBED("Flatbed", "Truck with a flat cargo area");

    private final String name;
    private final String description;

    TruckType(String name, String description) {
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

