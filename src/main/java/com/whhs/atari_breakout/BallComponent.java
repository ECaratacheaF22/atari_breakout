package com.whhs.atari_breakout;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.input.UserAction;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import java.util.Map;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;

import static com.almasb.fxgl.dsl.FXGL.*;

public class BallComponent extends Component {


  public void Collisions() {
    Point2D velocity = App.ball.getObject("velocity");
        App.ball.translate(velocity);

    if (App.ball.getY() + App.BallSize  == App.player.getY()
          && App.ball.getX() < App.player.getX() + App.PWidth
          && App.ball.getX() > App.player.getX()) {
    	App.ball.setProperty("velocity", new Point2D(velocity.getX(), -velocity.getY()));
    } 

    else if (App.ball.getX() <= 0) {
    	App.ball.setX(0);
    	App.ball.setProperty("velocity", new Point2D(-velocity.getX(), velocity.getY()));
    } 

    else if (App.ball.getRightX() >= 800) {
    	App.ball.setX(794 - App.BallSize);
    	App.ball.setProperty("velocity", new Point2D(-velocity.getX(), velocity.getY()));
    } 

    else if (App.ball.getY() <= 0) {
    	App.ball.setY(0);
    	App.ball.setProperty("velocity", new Point2D(velocity.getX(), -velocity.getY()));
    } 

    else if (App.ball.getBottomY() >= App.Height) {
        resetBall();
    } else if (App.ball.getY() + App.BallSize < 0) {
      resetBall();
    }
  }

  private void resetBall() {
    App.ball.setPosition(App.Width/2 - App.BallSize/2, App.Height/2 - 50);
    App.ball.setProperty("velocity", new Point2D(App.BallSpeed, App.BallSpeed));
  }
}
