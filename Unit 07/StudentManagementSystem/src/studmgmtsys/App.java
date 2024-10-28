package studmgmtsys;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import studmgmtsys.data.InitialData;

/**
 * Main entry point for the Student Management System application.
 * This class extends {@link javafx.application.Application} and is responsible 
 * for setting up the primary stage and initializing the main window of the application.
 */
public class App extends Application {

    /**
     * The `start` method is the main entry point for JavaFX applications.
     * It initializes the primary stage, loads the FXML file for the main window, 
     * and sets the scene.
     *
     * @param primaryStage The primary stage for this application, onto which 
     *                     the application scene can be set.
     * @throws Exception if the FXML file cannot be loaded.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        // Populate initial data for the application
        InitialData.populate();
        
        // Load the FXML layout for the main window
        Parent root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
        
        // Set the title of the application window
        primaryStage.setTitle("Student Management System");
        
        // Set the scene dimensions and display the main window
        primaryStage.setScene(new Scene(root, 400, 300));
        primaryStage.show();
    }

    /**
     * The main method serves as the application entry point when launched 
     * from the command line or a standard Java environment.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        // Launch the JavaFX application
        launch(args);
    }
}
