package com.mygdx.game.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.ModelData.PlayerData;
import com.mygdx.game.ModelData.Vector;
import com.mygdx.game.controller.CollisionDetection;

public class PlayableActor extends Actor {
    private final Body player;
    private final World world;
    private PlayerData data;
    private Weapon weapon;


    public PlayableActor(World world){
        data = new PlayerData(0,0,0,0);
        this.world = world;
        player = orld.createBody(generateBodyDef());
        PolygonShape bodyShape = new PolygonShape();
        bodyShape.set(getVectors(data.getVector()));
        player.createFixture(bodyShape, 4.0f);
        player.setSleepingAllowed(false);
        player.setUserData(this);
        weapon = new Weapon(world,this);
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

    private BodyDef generateBodyDef() {
        BodyDef bodyDef;
        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(data.posX,data.posY);
        bodyDef.fixedRotation = true;
        return bodyDef;
    }

    public Body getBody() {
        return player;
    }


    public void updatePlayer(){
        data.update();
        weapon.update();
        player.setTransform(data.posX,data.posY,0);
    }

    public PlayerData getData(){
        return data;
    }

    public Weapon getWeapon() {
        return weapon;
    }
}
