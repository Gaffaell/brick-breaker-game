package application;

import javafx.geometry.Bounds;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class CollisionDetection {

  public String isColliding(Circle ball, Rectangle brick) {
    Bounds ballBounds = ball.getBoundsInParent();
    Bounds brickBounds = brick.getBoundsInParent();

    // If they don't intersect, no collision
    if (!ballBounds.intersects(brickBounds)) {
      return "NONE";
    }

    // Compute penetration (overlap) distances from each side
    double overlapLeft = ballBounds.getMaxX() - brickBounds.getMinX();
    double overlapRight = brickBounds.getMaxX() - ballBounds.getMinX();
    double overlapTop = ballBounds.getMaxY() - brickBounds.getMinY();
    double overlapBottom = brickBounds.getMaxY() - ballBounds.getMinY();

    // Choose the smallest overlap -> the side of impact
    if (overlapTop <= overlapBottom && overlapTop <= overlapLeft && overlapTop <= overlapRight) {
      return "TOP";
    } else if (overlapBottom <= overlapTop && overlapBottom <= overlapLeft && overlapBottom <= overlapRight) {
      return "BOTTOM";
    } else if (overlapLeft <= overlapRight && overlapLeft <= overlapTop && overlapLeft <= overlapBottom) {
      return "LEFT";
    } else {
      return "RIGHT";
    }
  }

  public String isColliding(AnchorPane gamePane, Circle ball) {

    Bounds ballPosition = ball.getBoundsInParent();
    double ballLeft = ballPosition.getMinX();
    double ballRight = ballPosition.getMaxX();
    double ballTop = ballPosition.getMinY();
    double ballBottom = ballPosition.getMaxY();

    boolean collidesWithLeft = ballLeft <= 0;
    boolean collidesWithRight = ballRight >= gamePane.getWidth();
    boolean collidesWithTop = ballTop <= 0;
    boolean collidesWithBottom = ballBottom >= gamePane.getHeight();

    if (collidesWithLeft) {
      return "LEFT";
    } else if (collidesWithRight) {
      return "RIGHT";
    } else if (collidesWithTop) {
      return "TOP";
    } else if (collidesWithBottom) {
      return "BOTTOM";
    }

    return "NONE";

  }

  public String isColliding(AnchorPane gamePane, Rectangle mainBrick) {

    Bounds brickBounds = mainBrick.getBoundsInParent();
    double brickLeft = brickBounds.getMinX() + 10;
    double brickRight = brickBounds.getMaxX() + 10;

    boolean collidesWithLeft = brickLeft <= 15;
    boolean collidesWithRight = brickRight >= gamePane.getWidth();

    if (collidesWithLeft) {
      return "LEFT";
    } else if (collidesWithRight) {
      return "RIGHT";
    }

    return "NONE";

  }

}
