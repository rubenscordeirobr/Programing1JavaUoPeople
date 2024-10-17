package enums;
/**
 * The EnumDescription interface defines a blueprint for enumerations that
 * provide a name and description.
 * This interface can be used by enums to ensure they provide meaningful
 * information about each type.
 */
public interface EnumDescription {
    /**
     * Retrieves the name of the enum type.
     * 
     * @return a String representing the name.
     */
    String getName();

    /**
     * Retrieves a description of the enum type.
     * 
     * @return a String representing the description.
     */
    String getDescription();

    /**
     * Retrieves a full description of the enum type, which combines name and
     * description.
     * 
     * @return a String representing the full description.
     */
    String getFullDescription();
}
