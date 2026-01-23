package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameController implements Initializable{ // Implements Initializable for game setup

    @FXML
    private Button menuButton;
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToMenuScene(ActionEvent event) throws IOException {
        
        root = FXMLLoader.load(getClass().getResource("/fxml/Menu.fxml")); // Loads the Game FXML file
        stage = (Stage)((Node)event.getSource()).getScene().getWindow(); // Gets the current stage
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/style/Menu.css").toExternalForm()); // Adds the CSS stylesheet to the scene
        stage.setScene(scene); // Sets the new scene on the stage
        stage.show();

    }

    @FXML
    private Circle ball; // Example of a game element

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        TranslateTransition translate = new TranslateTransition();
        translate.setNode(ball);
        translate.setDuration(Duration.millis(1000));
        translate.setCycleCount(TranslateTransition.INDEFINITE);
        translate.setByY(-300);
        translate.setAutoReverse(true); // if the ball hits something
        translate.play();

        
    }



}