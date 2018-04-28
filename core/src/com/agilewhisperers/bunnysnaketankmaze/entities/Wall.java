package com.agilewhisperers.bunnysnaketankmaze.entities;

import com.agilewhisperers.bunnysnaketankmaze.components.Body;
import com.agilewhisperers.bunnysnaketankmaze.components.Sprite;
import com.agilewhisperers.bunnysnaketankmaze.systems.*;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;

public class Wall extends GameObject implements Collider,Script {
    public Wall(float posX, float posY) {
        super(new Sprite(
                "gameObjects/Environment.atlas", "Wall", 1
        ), new Body(Physic.getObject().getWorld(), posX, posY, 1, 0, "Wall"));
        this.getBody().getBody().setType(BodyDef.BodyType.KinematicBody);
        getStats().setID("Wall");
        getStats().setExist(true);
        this.getBody().getBody().setUserData(getStats());
        this.getBody().getFixtureList().get(0).setUserData(getStats().getID());


        CollisionManager.getObject().addCollider(this);

        this.getBody().updateFilter(Physic.CATEGORY_ENVIRONMENT, (short) -1);
        ScriptManager.getObject().addScriptListener(this);

    }

    @Override
    public void startCollision(Contact contact) {

    }

    @Override
    public void endCollision(Contact contact) {

    }

    @Override
    public com.badlogic.gdx.physics.box2d.Body getBodyForCollisionTesting() {
        return getBody().getBody();
    }


    /**
     * This method will be call every game loop.
     *
     * @param deltaTime
     */
    @Override
    public void runObjectScript(float deltaTime) {

    }
}
