package com.agilewhisperers.bunnysnaketankmaze.entities;

import com.agilewhisperers.bunnysnaketankmaze.components.Sprite;
import com.agilewhisperers.bunnysnaketankmaze.systems.GameObjectManager;
import com.agilewhisperers.bunnysnaketankmaze.systems.Physic;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.MathUtils;

public class Player2 extends Player {
   public Player2(float posX, float posY) {

      super(posX, posY);
      this.getBody().updateFilter(Physic.CATEGORY_PLAYER2, (short) -1);
      setSprite(new Sprite("gameObjects/Player.atlas", "Player", 2));

   }

   @Override
   public void movement() {
      if (Gdx.input.isKeyPressed(Input.Keys.A)) {
         this.getBody().setAngularVelocity(getStats().getRotatingSpeed());
      } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
         this.getBody().setAngularVelocity(-getStats().getRotatingSpeed());
      } else {
         this.getBody().setAngularVelocity(0);
      }

      if (Gdx.input.isKeyPressed(Input.Keys.W)) {
         this.getBody().getBody().setLinearVelocity(MathUtils.cosDeg(this.getBody().getAngle()) * getStats().getMovingSpeed(), MathUtils.sinDeg(this.getBody().getAngle()) * getStats().getMovingSpeed());

      } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
         this.getBody().getBody().setLinearVelocity(MathUtils.cosDeg(this.getBody().getAngle()) * -getStats().getMovingSpeed(), MathUtils.sinDeg(this.getBody().getAngle()) * -getStats().getMovingSpeed());
      } else {
         this.getBody().getBody().setLinearVelocity(0, 0);
      }
   }

   @Override
   public void fire() {
      rateTimer += getDeltaTime();
      if ((getStats().getCapacityCounter() <= getStats().getCapacity()) && rateTimer > 1 / getStats().getRPS() && Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
         GameObjectManager.getObject().getBullet(false).update(this.getBody().getPreviousPosition().x,
                 this.getBody().getPreviousPosition().y,
                 this.getBody().getPreviousAngle() / MathUtils.degRad, getStats().getBulletSpeed());
         rateTimer = 0;
         getStats().setCapacityCounter(getStats().getCapacityCounter() + 1);
      }

      //Reload
      if (getStats().getCapacityCounter() > getStats().getCapacity()) {
         reloadTimer += getDeltaTime();
         if (reloadTimer >= getStats().getReloadTime()) {
            getStats().setCapacityCounter(0);
            reloadTimer = 0;
         }
      }
   }
}
