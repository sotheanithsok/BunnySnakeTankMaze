package com.agilewhisperers.bunnysnaketankmaze.systems;

import com.agilewhisperers.bunnysnaketankmaze.entities.GameObject;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.agilewhisperers.bunnysnaketankmaze.components.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.agilewhisperers.bunnysnaketankmaze.components.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Renderer {

   private final float ppm=32/1f;
   private static Renderer single_instance;
   private OrthographicCamera camera;
   private SpriteBatch batch;
   private Box2DDebugRenderer renderer;

   private Renderer(){
      camera=new OrthographicCamera();
      camera.setToOrtho(false,1980,1020);
      batch=new SpriteBatch();
      renderer=new Box2DDebugRenderer();
   }
   public static Renderer getObject(){
      if(single_instance==null){
         single_instance=new Renderer();
      }
      return single_instance;
   }

   public void render(){
      Gdx.gl.glClearColor(0.95f,0.95f,0.95f,0.95f);
      Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
      camera.update();
      batch.setProjectionMatrix(camera.combined);
      batch.begin();

      for(GameObject object:GameObjectManager.getObject().getAllGameObjects()){
         if(!object.isExcluded()){
            if(object.getBody()!=null &&object.getSprite()!=null){
               Body body=object.getBody();
               Sprite sprite=object.getSprite();
               batch.draw(sprite.getSprite(),body.getBody().getPosition().x*ppm,
                       body.getBody().getPosition().y*ppm,body.getWidth()*ppm,body.getHigh()*ppm);
            }
         }
      }

      batch.end();
   }
   public void renderHitBox(World world){
         renderer.render(world,camera.combined.cpy().scale(ppm,ppm,0));

   }
}
