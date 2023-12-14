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

public class App extends GameApplication {

  BallComponent BC = new BallComponent();

  public static final int Width = 800;
  public static final int Height = 900;
  public static final int PWidth = 200;
  public static final int PHeight = 25;
  public static final int PlayerSpeed = 7;
  public static final int BallSize = 10;
  public static final int BallSpeed = -5;
  public static final int BrWidth= 60;
  public static final int BrHeight= 20;
  public static final Color PColor = Color.rgb(50, 50, 50);
  public static final Color BColor = Color.rgb(0, 0, 0);
  public static final Color BrColor = Color.rgb(0,125,125);

  public static Entity player;
  public static Entity ball;
  public static Entity brick;
  
  @Override
  protected void initSettings(GameSettings settings) {
    settings.setWidth(Width);
    settings.setHeight(Height);
    settings.setTitle("Atari");
    settings.setVersion("1");
  }
  
  @Override
  protected void initInput() {
    onKey(KeyCode.D, () -> {
    player.translateX(PlayerSpeed); });
    onKey(KeyCode.A, () -> {
    player.translateX(-1 * (PlayerSpeed)); });
    }

  @Override
  protected void initGame() {
    getGameWorld().addEntityFactory(new Factory());
    
    getGameScene().setBackgroundColor(Color.rgb(0,50,50));
    
    player = spawn("player",Width/2 - PWidth/2, Height/1.125 /* *2 */ - PHeight);
    ball = spawn("ball", Width/2 - BallSize/2, Height/2 - 50);

    for (int i = 0; i < 11; i++) {
      spawn("brick", 5 + i*(BrWidth + 12.5), 60);
      spawn("brick", 5 + i*(BrWidth + 12.5), 120);
      spawn("brick", 5 + i*(BrWidth + 12.5), 180);
      spawn("brick", 5 + i*(BrWidth + 12.5), 240);
      spawn("brick", 5 + i*(BrWidth + 12.5), 300);

    }
  }

  @Override
  protected void initPhysics() {
      onCollisionCollectible(EntityType.BALL, EntityType.BRICK, (brick) -> {
          Point2D velocity = ball.getObject("velocity");

          if (FXGLMath.randomBoolean()) {
              ball.setProperty("velocity", new Point2D(-velocity.getX(), velocity.getY()));
          } else {
              ball.setProperty("velocity", new Point2D(velocity.getX(), -velocity.getY()));
          }
      });
  }
  
  @Override
  protected void onUpdate(double tpf) {
    BC.Collisions();
  }

  // *1
  
  public static void main(String[] args) {
    launch(args);
  }
}