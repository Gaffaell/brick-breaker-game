package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class MenuController {
    
    @FXML
    private AnchorPane menuPane;
    @FXML
    private Button startGame;
    @FXML
    private Button exitGame;
    @FXML
    private Button scoreGame;
    
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToGameScene(ActionEvent event) throws IOException {
        
        root = FXMLLoader.load(getClass().getResource("/fxml/Game.fxml")); // Loads the Game FXML file
        stage = (Stage)((Node)event.getSource()).getScene().getWindow(); // Gets the current stage
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/style/Game.css").toExternalForm()); // Adds the CSS stylesheet to the scene
        stage.setScene(scene); // Sets the new scene on the stage
        stage.show();

    }
    public void switchToScoreScene(ActionEvent event) throws IOException {
        
        root = FXMLLoader.load(getClass().getResource("/fxml/Score.fxml")); // Loads the Game FXML file
        stage = (Stage)((Node)event.getSource()).getScene().getWindow(); // Gets the current stage
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/style/Score.css").toExternalForm()); // Adds the CSS stylesheet to the scene
        stage.setScene(scene); // Sets the new scene on the stage
        stage.show();

    }
    public void exitGame(ActionEvent event) throws IOException {
        
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Exit Game");
        alert.setHeaderText("You are about to exit the game.");
        alert.setContentText("Are you sure you want to exit?");

        if (alert.showAndWait().get() == ButtonType.OK) {

            stage = (Stage) menuPane.getScene().getWindow(); // Gets the current stage
            stage.close(); // Closes the stage

        }

    }

}