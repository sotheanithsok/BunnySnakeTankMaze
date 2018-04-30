package TestingAshley.Entities;

import TestingAshley.Components.BodyComponent;
import TestingAshley.Components.SpriteComponent;
import TestingAshley.Components.TagComponent;
import TestingAshley.Components.TimeComponent;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.World;

public class Wall extends Entity {
   public Wall(World world, float posX, float posY){
      add(new SpriteComponent("gameObjects/Environment.atlas", "Wall", 1));
      add(new TagComponent());
      add(new BodyComponent(world,posX,posY,1,"Wall"));
      add(new TimeComponent());
   }
}
