package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.ModelData.Vector;

public class WorldArea {
    private final World world;

    private final float w = Gdx.graphics.getWidth();
    private final float h = Gdx.graphics.getHeight();

    public WorldArea(){
        world = new World(new Vector2(0, -9.8f), true);
        Body ground = world.createBody(createGroundDef(0,0));
        ground.createFixture(createGroundShape(100,20),1.0f);
        ground.setActive(false);
    }

    private PolygonShape createGroundShape(int hx,int hy) {
        PolygonShape shape = new PolygonShape();
        float pixelsPerMeter = 32;

        shape.set(createShape());
        return shape;
    }

    private Vector2[] createShape() {
        Vector[] vector = new Vector[]{new Vector(-10, 0), new Vector(-10, 1), new Vector(10, 1), new Vector(10, 0)};
        Vector2[] bodyVectors = new Vector2[]{
                new Vector2(vector[0].x,vector[0].y),
                new Vector2(vector[1].x,vector[1].y),
                new Vector2(vector[2].x,vector[2].y),
                new Vector2(vector[3].x,vector[3].y),
        };
        return bodyVectors;
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

    public BodyDef createGroundDef(float posX, float posY) {
        BodyDef definition = new BodyDef();
        definition.type = BodyDef.BodyType.DynamicBody;
        definition.position.set(posX,posY);
        definition.fixedRotation = true;
        return definition;
    }

    public World getWorld() {
        return world;
    }
}


