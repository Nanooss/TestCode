package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class Render {
    private SpriteBatch sb;
    private Box2DDebugRenderer debugRenderer;


    public Render(){
        sb = new SpriteBatch();
        debugRenderer = new Box2DDebugRenderer();
    }

    public void render(Texture texture, int x, int y){
        sb.begin();
        sb.draw(texture,x,y);
        sb.end();
    }

    public void render(Texture texture,int x,int y,int screenWith,int screenHeight){
        sb.begin();
        sb.draw(texture,x,y,screenWith,screenHeight);
        sb.end();
    }

    public void render(Texture texture,float x,float y,float screenWith,float screenHeight){
        sb.begin();
        sb.draw(texture,x,y,screenWith,screenHeight);
        sb.end();
    }


    public void updateCamera(Camera cam, Vector3 position){
        cam.position.set(0,0,0);
        cam.update();
    }

    public void setProjectionMatrix(Matrix4 combined) {
        sb.setProjectionMatrix(combined);
    }

    public void updateScore(int score){
        sb.begin();
        sb.end();
    }

    public void renderBirdMusic(){
        Music music = Gdx.audio.newMusic(Gdx.files.internal("Angry-Birds-Theme-Song.mp3"));
        music.setLooping(true);
        music.setVolume(0.1f);
        music.play();

    }
    public void dispose() {
        sb.dispose();
    }

    public void debugRender(World world, OrthographicCamera cam, int pixelsPerMeter) {
        debugRenderer.render(world,cam.combined.scl(pixelsPerMeter));
    }
}
