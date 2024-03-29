package com.agilewhisperers.bunnysnaketankmaze.entities;

import com.agilewhisperers.bunnysnaketankmaze.components.Body;
import com.agilewhisperers.bunnysnaketankmaze.components.Sprite;
import com.agilewhisperers.bunnysnaketankmaze.systems.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;

public class Player extends GameObject implements Script, Collider {

   float rateTimer = 0;
   float reloadTimer = 0;
   float iFrameTimer = 0f;

   private float startRustTimer = 0;
   private float rustTime = 0;
   private float deltaTime;

   public Player(float posX, float posY) {
      super();
      super.setBody(new Body(Physic.getObject().getWorld(), posX, posY, 1f, 0.8f, "Player"));
      super.setSprite(new Sprite("gameObjects/Player.atlas", "Player", 1));
      getStats().setID("Player");
      getStats().setExist(true);
      this.getBody().getBody().setType(BodyDef.BodyType.DynamicBody);
      //Add to scriptManager
      ScriptManager.getObject().addScriptListener(this);

      //Set tag for the object
      this.getBody().getBody().setUserData(this);
      this.getBody().getFixtureList().get(0).setUserData(getStats().getID());
      this.getBody().updateFilter(Physic.CATEGORY_PLAYER1, (short) -1);

   }


   /**
    * This method will be call every game loop.
    *
    * @param deltaTime
    */
   @Override
   public void runObjectScript(float deltaTime) {
      this.deltaTime = deltaTime;
      iFrameTimer -= deltaTime;
      movement();
      fire();
      rust();
      if (getSteerableComponent() != null) {
         getSteerableComponent().update(deltaTime);
      }
   }

   public void movement() {
      if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
         this.getBody().setAngularVelocity(getStats().getRotatingSpeed());
      } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
         this.getBody().setAngularVelocity(-getStats().getRotatingSpeed());
      } else {
         this.getBody().setAngularVelocity(0);

      }

      if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
         this.getBody().getBody().setLinearVelocity(MathUtils.cosDeg(this.getBody().getAngle()) * getStats().getMovingSpeed(), MathUtils.sinDeg(this.getBody().getAngle()) * getStats().getMovingSpeed());

      } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
         this.getBody().getBody().setLinearVelocity(MathUtils.cosDeg(this.getBody().getAngle()) * -getStats().getMovingSpeed(), MathUtils.sinDeg(this.getBody().getAngle()) * -getStats().getMovingSpeed());
      } else {
         this.getBody().getBody().setLinearVelocity(0, 0);
      }
   }

   public void fire() {
      //Normal Fire
      rateTimer += deltaTime;
      if ((getStats().getCapacityCounter() <= getStats().getCapacity()) && rateTimer > 1 / getStats().getRPS() && Gdx.input.isKeyPressed(Input.Keys.M)) {
         GameObjectManager.getObject().getBullet(true).update(this.getBody().getPreviousPosition().x,
                 this.getBody().getPreviousPosition().y,
                 this.getBody().getPreviousAngle() / MathUtils.degRad, getStats().getBulletSpeed());

         rateTimer = 0;
         getStats().setCapacityCounter(getStats().getCapacityCounter() + 1);
      }

      //Reload
      if (getStats().getCapacityCounter() > getStats().getCapacity()) {
         reloadTimer += deltaTime;
         if (reloadTimer >= getStats().getReloadTime()) {
            getStats().setCapacityCounter(0);
            reloadTimer = 0;
         }
      }
   }


   @Override
   public void startCollision(Contact contact) {

   }

   @Override
   public void endCollision(Contact contact) {

   }


   public void rust() {
      if (this.getBody().getBody().getAngularVelocity() == 0 && this.getBody().getBody().getLinearVelocity().x == 0 && this.getBody().getBody().getLinearVelocity().y == 0) {
         startRustTimer += deltaTime;
      } else {
         startRustTimer = 0;
      }
      if (startRustTimer > 10) {
         rustTime += deltaTime;
         if (rustTime > 1) {
            getStats().setCurrentHP(getStats().getCurrentHP() - (getStats().getMaxHP() * 5f / 100f));
            rustTime = 0;
         }
      }

   }


   public float getDeltaTime() {
      return deltaTime;
   }

   public void startIFrame() {
      iFrameTimer = 0.05f;
   }

   public float getiFrameTimer() {
      return iFrameTimer;
   }
}
