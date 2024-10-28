package studmgmtsys.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * Utility class that provides methods to show different types of alerts 
 * in a JavaFX application, including confirmation, error, and success dialogs.
 */
public class AlertUtils {

    /**
     * Displays a confirmation dialog with a given message and title.
     * 
     * @param message the message to display in the dialog
     * @param title the title of the dialog window
     * @return true if the user confirms with "OK" or "YES", false otherwise
     */
    public static boolean confirmDialog(String message, String title) {
        // Create a confirmation alert
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null); // No header text
        alert.setContentText(message); // Set the content message
        
        // Show the alert and wait for the user's response, defaulting to CANCEL if no response
        ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);
        
        // Return true if the result is "OK" or "YES", otherwise false
        return result == ButtonType.OK || result == ButtonType.YES;
    }

    /**
     * Displays an error alert with a given error message.
     * 
     * @param message the error message to display
     */
    public static void showErrorAlert(String message) {
        // Create an error alert
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error"); // Set the title
        alert.setHeaderText(null); // No header text
        alert.setContentText(message); // Set the error message
        
        // Show the alert and wait for the user's acknowledgment
        alert.showAndWait();
    }

    /**
     * Displays a success alert with a given success message.
     * 
     * @param message the success message to display
     */
    public static void showSuccessAlert(String message) {
        // Create an informational alert for success
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success"); // Set the title
        alert.setHeaderText(null); // No header text
        alert.setContentText(message); // Set the success message
        
        // Show the alert and wait for the user's acknowledgment
        alert.showAndWait();
    }
}
