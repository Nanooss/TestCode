package com.mygdx.game.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.ModelData.ProjectileData;
import com.mygdx.game.ModelData.Vector;

public class Weapon {
    private Body projectile;
    private final World world;
    PlayableActor player;
    ProjectileData data;

    private int projectileIndex;

    public Weapon(World world, PlayableActor player) {
        this.world = world;
        this.player = player;
    }

    private float getDirection() {
        return player.getData().getDirectionMult();
    }

    public void useWeapon() {
        data = new ProjectileData(player.getData().posX, player.getData().posY, 0.4f * getDirection(), 10);
        projectile = world.createBody(generateBodyDef(data));
        PolygonShape bodyShape = new PolygonShape();
        bodyShape.set(getVectors(data.getVector()));
        projectile.createFixture(bodyShape,1.0f);
        projectile.setUserData(this);
        data.start();
    }

    private BodyDef generateBodyDef(ProjectileData projectileData) {
        BodyDef bodyDef;
        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(projectileData.posX, projectileData.posY);
        bodyDef.fixedRotation = true;
        return bodyDef;
    }

    private Vector2[] getVectors(Vector[] vector) {
        Vector2[] bodyVectors = new Vector2[]{
                new Vector2(vector[0].x,vector[0].y),
                new Vector2(vector[1].x,vector[1].y),
                new Vector2(vector[2].x,vector[2].y),
                new Vector2(vector[3].x,vector[3].y),
        };
        return bodyVectors;
    }

    public void update() {
        if(data != null) data.update();
            if(projectile != null){
            if (!(data.isActive())) {world.destroyBody(projectile);
            data = null; projectile = null;}
            if(projectile != null) projectile.setTransform(data.posX,data.posY,0);
    }}

    public void inputKeyDown(int keycode) {
        if (keycode == 62) {
            if(projectile == null) useWeapon();
        }
    }

    public void hit() {
        data.stop();
        System.out.println("stop");
    }
}