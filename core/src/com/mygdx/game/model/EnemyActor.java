package com.mygdx.game.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.ModelData.EnemyData;
import com.mygdx.game.ModelData.PlayerData;
import com.mygdx.game.ModelData.Vector;

public class EnemyActor extends Actor {
    private Body enemy;
    private final World world;
    private EnemyData data;
    private Weapon weapon;


    public EnemyActor(World world){
        data = new EnemyData(0,0,0,0);
        this.world = world;
        enemy = world.createBody(generateBodyDef());
        PolygonShape bodyShape = new PolygonShape();
        bodyShape.set(getVectors(data.getVector()));
        enemy.createFixture(bodyShape, 4.0f);
        enemy.setSleepingAllowed(false);
        enemy.setUserData(this);
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
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(data.posX,data.posY);
        bodyDef.fixedRotation = true;
        return bodyDef;
    }

    public Body getBody() {
        return enemy;
    }


    public void updatePlayer(){
        if(data.getHealth()) {
            data.update();
            enemy.setTransform(data.posX, data.posY, 0);
        }
        if (!data.getHealth() && enemy != null){
            world.destroyBody(enemy);
            enemy=null;
        }
    }

    public EnemyData getData(){
        return data;
    }

    public Weapon getWeapon() {
        return weapon;
    }
}
