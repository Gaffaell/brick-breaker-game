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
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameController extends AnimationTimer implements Initializable { // Implements Initializable, loads when the scene is created

    @FXML
    private Button menuButtonGameOver;
    @FXML
    private Label youLose;
    @FXML
    private Label pressMenu;
    @FXML
    private Rectangle mainBrick;
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
    @FXML
    private Label alertEnter;
    @FXML
    private Label alertEscape;
    @FXML
    private AnchorPane gamePane;
    private TranslateTransition circleAnimation = new TranslateTransition();
    private CollisionDetection collisionDetection = new CollisionDetection();
    private static final double BALL_Y_MOVE = -600;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Create and start the animation when the scene loads
        circleAnimation.setNode(ball);
        circleAnimation.setByY(BALL_Y_MOVE);
        circleAnimation.setByX(100);
        circleAnimation.setDuration(Duration.millis(2000));
        circleAnimation.setCycleCount(TranslateTransition.INDEFINITE);
        circleAnimation.play();
        
        // Start the animation timer
        this.start();
    }

    public void switchToMenuScene(ActionEvent event) throws IOException {
        
        this.stop();
        root = FXMLLoader.load(getClass().getResource("/fxml/Menu.fxml")); // Loads the Game FXML file
        stage = (Stage)((Node)event.getSource()).getScene().getWindow(); // Gets the current stage
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/style/Menu.css").toExternalForm()); // Adds the CSS stylesheet to the scene
        stage.setScene(scene); // Sets the new scene on the stage
        stage.show();

    }

    // ------------- method from AnimationTimer -------------
    @Override
    public void handle(long now) {
        
        doHandle();
        
    }

    private void doHandle() {
        // Game loop logic goes here

        if (collisionDetection.isColliding(ball, mainBrick) == "TOP" || 
                collisionDetection.isColliding(ball, mainBrick) == "BOTTOM") {
            // verification is the collision is being detected

            // Handle collision response
            
            System.out.println("Collision detected!");
            brick.setFill(Color.RED);
            ball.setFill(Color.GREEN);
            circleAnimation.stop();
            circleAnimation.setByY(BALL_Y_MOVE);
            circleAnimation.setByX(circleAnimation.getByX() * -1);
            circleAnimation.play();
            
            
        } 
        
        // ------------- collision detection between ball and brick -------------
        if (collisionDetection.isColliding(ball, brick) == "BOTTOM") {
            // verification is the collision is being detected

            // Handle collision response
            
            System.out.println("Bottom Collision detected!");
            brick.setFill(Color.RED);
            ball.setFill(Color.GREEN);
            circleAnimation.stop();
            circleAnimation.setByY(BALL_Y_MOVE * -1);
            circleAnimation.setByX(circleAnimation.getByX() * -1);
            circleAnimation.play();
            
            
        } else if (collisionDetection.isColliding(ball, brick) == "TOP"){ // if the collision is between the ball bottom and brick top
             
            System.out.println("Top Collision detected!");
            brick.setFill(Color.RED);
            ball.setFill(Color.GREEN);
            circleAnimation.stop();
            circleAnimation.setByY(BALL_Y_MOVE);
            circleAnimation.setByX(circleAnimation.getByX() * -1);
            circleAnimation.play();
            
            
        }

        // ------------- collision detection between ball and brick -------------
        switch (collisionDetection.isColliding(gamePane, ball)) {
            case "LEFT":
                System.out.println("Collision with left panel detected!");
                circleAnimation.stop();
                circleAnimation.setByY(circleAnimation.getByY());
                circleAnimation.setByX((circleAnimation.getByX() * -1) + 500);
                circleAnimation.play();
                break;
            case "RIGHT":
                System.out.println("Collision with right panel detected!");
                circleAnimation.stop();
                circleAnimation.setByY(circleAnimation.getByY());
                circleAnimation.setByX((circleAnimation.getByX() * -1) - 500);
                circleAnimation.play();
                break;
            case "TOP":
                System.out.println("Collision with top panel detected!");
                circleAnimation.stop();
                circleAnimation.setByY(BALL_Y_MOVE * -1);
                circleAnimation.setByX(circleAnimation.getByX() * -1);
                circleAnimation.play();
                break;
            case "BOTTOM":
                System.out.println("Collision with bottom panel detected!");
                circleAnimation.stop();
                this.loseAlert();
                break;
            default:
                break;
        }

    }

    // ------------- method to when ball hit the bottom of the pane -------------
    public void loseAlert() {
        
        menuButtonGameOver.setVisible(true);
        youLose.setVisible(true);
        pressMenu.setVisible(true);

    }

    // ------------- method to handle key preess -------------
    public void moveMainBrick(KeyEvent event) {

        System.out.println("Key pressed: " + event.getCode().toString());
        
        switch (event.getCode()) {
            case LEFT:
                if (collisionDetection.isColliding(gamePane, mainBrick) != "LEFT") {
                    mainBrick.setTranslateX(mainBrick.getTranslateX() - 10);
                }
                break;

            case RIGHT:
                if (collisionDetection.isColliding(gamePane, mainBrick) != "RIGHT") {
                    mainBrick.setTranslateX(mainBrick.getTranslateX() + 10);
                }
                break;
        
            case ESCAPE:
                menuButton.setVisible(true);
                alertEnter.setVisible(true);
                alertEscape.setVisible(false);
                break;

            case ENTER:
                menuButton.setVisible(false);
                alertEnter.setVisible(false);
                alertEscape.setVisible(true);
                break;

            default:
                break;
        }
    }
}

