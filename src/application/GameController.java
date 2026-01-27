package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.AnimationTimer;
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

public class GameController extends AnimationTimer implements Initializable { // Implements Initializable, loads when the scene is created

    @FXML
    private Button menuButton;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Circle ball;
    private TranslateTransition circleAnimation = new TranslateTransition();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Create and start the animation when the scene loads
        circleAnimation.setNode(ball);
        circleAnimation.setByY(-300);
        circleAnimation.setCycleCount(TranslateTransition.INDEFINITE);
        circleAnimation.setAutoReverse(true);
        circleAnimation.play();
        
        // Start the animation timer
        this.start();
    }

    public void switchToMenuScene(ActionEvent event) throws IOException {
        
        root = FXMLLoader.load(getClass().getResource("/fxml/Menu.fxml")); // Loads the Game FXML file
        stage = (Stage)((Node)event.getSource()).getScene().getWindow(); // Gets the current stage
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/style/Menu.css").toExternalForm()); // Adds the CSS stylesheet to the scene
        stage.setScene(scene); // Sets the new scene on the stage
        stage.show();

    }

    @Override
    public void handle(long now) {
        
        doHandle();
        
    }

    private void doHandle() {
        // Game loop logic goes here

        
    }


}

