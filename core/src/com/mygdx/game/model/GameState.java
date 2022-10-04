package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.controller.CollisionDetection;
import com.mygdx.game.view.Render;

public class GameState {
    private OrthographicCamera cam;
    private WorldArea world;
    private Render rc;
    private PlayableActor player;
    private EnemyActor enemy;

    private float w = Gdx.graphics.getWidth();
    private float h = Gdx.graphics.getHeight();
    private int pixelsPerMeter = 32;



    public GameState(Render rc){
        cam = new OrthographicCamera();
        world = new WorldArea();
        player = new PlayableActor(world.getWorld());
        enemy = new EnemyActor(world.getWorld());
        world.getWorld().setContactListener(new CollisionDetection(player,player.getWeapon(),enemy));
        cam.setToOrtho(false, w, h);
        this.rc = rc;
    }

    public void generateNewEnemy(){
        enemy = new EnemyActor(world.getWorld());
        world.getWorld().setContactListener(new CollisionDetection(player,player.getWeapon(),enemy));
    }

    public void cameraUpdate() {
        Vector3 position = cam.position;
        position.x = player.getBody().getPosition().x * pixelsPerMeter;
        position.y = player.getBody().getPosition().y * pixelsPerMeter;
        cam.position.set(position);
        cam.update();
    }

    public void update(float deltaTime) {
        world.getWorld().step(1/60f, 6,2);
        player.updatePlayer();
        enemy.updatePlayer();
        if(!enemy.getData().getHealth()) generateNewEnemy();
        cameraUpdate();
    }

    public void render() {
        Gdx.gl.glClearColor(0f, 0f, 0f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        rc.setProjectionMatrix(cam.combined);
        rc.debugRender(world.getWorld(),cam,pixelsPerMeter);
    }

    public PlayableActor getPlayer(){
        return player;
    }

    public WorldArea getWorldArea() {
        return world;
    }
}
