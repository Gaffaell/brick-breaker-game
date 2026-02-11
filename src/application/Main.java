package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class Main extends Application {
  public static void main(String[] args) throws Exception {
    launch(args); // Launches the JavaFX application
  }

  @Override
  public void start(Stage stage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("/fxml/Menu.fxml")); // Loads the FXML file
    Scene scene = new Scene(root); // Creates a new scene with the loaded FXML
    scene.getStylesheets().add(getClass().getResource("/style/Menu.css").toExternalForm()); // Adds the CSS stylesheet
                                                                                            // to the scene

    stage.setOnCloseRequest(event -> {
      event.consume();
      exitGame(stage);
    });

    stage.setScene(scene); // Sets the scene on the stage
    stage.setTitle("Brick Breaker Game");
    stage.show(); // Displays the stage

  }

  public void exitGame(Stage stage) {

    Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setTitle("Exit Game");
    alert.setHeaderText("You are about to exit the game.");
    alert.setContentText("Are you sure you want to exit?");

    if (alert.showAndWait().get() == ButtonType.OK) {

      stage = (Stage) stage.getScene().getWindow(); // Gets the current stage
      stage.close(); // Closes the stage

    }

  }
}
