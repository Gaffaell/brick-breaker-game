package application;

import javafx.geometry.Bounds;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;


public class CollisionDetection {

    public boolean isColliding(Circle ball, Rectangle brick) {
        
        // get the center of the ball
        Bounds ballPosition = ball.getBoundsInParent();
        // get the center of the brick
        Bounds brickPosition = brick.getBoundsInParent();;

        return ballPosition.intersects(brickPosition);

    }
    
}
