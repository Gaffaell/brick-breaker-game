package brickbreakergame;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ScoreController {

  @FXML
  private Button menuButton;
  private Stage stage;
  private Scene scene;
  private Parent root;

  public void switchToMenuScene(ActionEvent event) throws IOException {

    root = FXMLLoader.load(getClass().getResource("/fxml/Menu.fxml")); // Loads the Game FXML file
    stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // Gets the current stage
    scene = new Scene(root);
    scene.getStylesheets().add(getClass().getResource("/style/Menu.css").toExternalForm()); // Adds the
                                                                                            // CSS
                                                                                            // stylesheet
    // to the scene
    stage.setScene(scene); // Sets the new scene on the stage
    stage.show();

  }

}
