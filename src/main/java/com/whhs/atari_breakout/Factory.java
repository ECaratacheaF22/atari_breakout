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

public class Factory implements EntityFactory {

  BallComponent BC = new BallComponent();

  @Spawns("player")
    public Entity spawnPlayer(SpawnData data) {
      return entityBuilder(data) 
        .viewWithBBox(new Rectangle(App.PWidth, App.PHeight, App.PColor))
        .build(); 
    }

  @Spawns("ball")
    public Entity spawnBall(SpawnData data) {
      return entityBuilder(data) 
        .type(EntityType.BALL)
        .viewWithBBox(new Circle(App.BallSize, App.BColor))
        .collidable()
        .with("velocity", new Point2D(App.BallSpeed,App.BallSpeed))
        .build();
    }

  @Spawns("brick")
    public Entity spawnBrick(SpawnData data) {
      return entityBuilder(data) 
        .type(EntityType.BRICK)
        .viewWithBBox(new Rectangle(App.BrWidth, App.BrHeight, App.BrColor))
        .collidable()
        .build();
    }
}