package studmgmtsys;

import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 * Abstract base controller class providing common functionality 
 * for controllers in the Student Management System application.
 * This class includes methods for opening and closing windows, 
 * which can be utilized by subclasses.
 */
public abstract class BaseController {

    /**
     * Opens a new window using the specified FXML file, with an optional modal setting.
     * 
     * @param fxmlFile   The path to the FXML file to load.
     * @param windowTitle The title of the new window.
     * @param isModal    Whether the new window should block interaction with the parent window (modal).
     */
    protected void openWindow(String fxmlFile, String windowTitle, boolean isModal) {
        try {
            // Load the FXML layout for the new window
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle(windowTitle);
            stage.setScene(new Scene(root));

            // Set the window modality if it's meant to be modal
            if (isModal) {
                stage.initModality(Modality.APPLICATION_MODAL); // Set the modality to block parent window
                stage.showAndWait(); // Wait for the window to close before returning
            } else {
                stage.show(); // Show non-modal window
            }

        } catch (Exception e) {
            // Handle potential exceptions by printing the stack trace
            e.printStackTrace();
        }
    }

    /**
     * Closes the specified window (stage).
     * 
     * @param stage The window (stage) to be closed.
     */
    protected void closeWindow(Stage stage) {
        if (stage != null) {
            stage.close();
        }
    }

}
