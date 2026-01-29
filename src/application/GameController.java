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
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class GameController extends AnimationTimer implements Initializable { // Implements Initializable, loads when the scene is created

    @FXML
    private Button menuButton;
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Parent root;
    @FXML
    private Circle ball;
    @FXML
    private Rectangle brick;
    private TranslateTransition circleAnimation = new TranslateTransition();
    private CollisionDetection collisionDetection = new CollisionDetection();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Create and start the animation when the scene loads
        circleAnimation.setNode(ball);
        circleAnimation.setByY(-300);
        circleAnimation.setDuration(javafx.util.Duration.millis(1000));
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
        
        if (collisionDetection.isColliding(ball, brick) == true) {

            // Handle collision response
            System.out.println("Collision detected!");
            brick.setFill(Color.RED);
            ball.setFill(Color.GREEN);
            
        } else {
            
            brick.setFill(Color.BLUE);
            ball.setFill(Color.BLUE);
            System.out.println("No collision detected!");

            
        }
        
    }


}

