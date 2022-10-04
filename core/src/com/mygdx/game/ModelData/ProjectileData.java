package com.mygdx.game.ModelData;

public class ProjectileData {

    public float spawnPointX;
    public float spawnPointY;

    public float posX;
    public float posY;

    public float velocityX;

    private float range;
    private float rangeTraveled = 0;

    public Vector[] shape;

    public void stop() {
        projectileState = state.inactive;
    }

    public enum Directions{left,right}

    Directions projectileDirection;

    public boolean isActive() {
        return projectileState == state.active;
    }

    enum state{active, inactive}

    state projectileState = state.inactive;

    public ProjectileData(float spawnPosX, float spawnPosY, float spawnVelocityX, float spawnRange){
        this.spawnPointX =spawnPosX+0.5f;
        this.spawnPointY = spawnPosY+0.5f;

        this.posX = spawnPosX+0.5f;
        this.posY = spawnPosY+0.5f;

        this.velocityX=spawnVelocityX;

        if(spawnVelocityX<0)projectileDirection=Directions.left;
        else projectileDirection = Directions.right;

        this.range = spawnRange;
        createShape();
    }

    public void start(){
        projectileState = state.active;
    }

    public Vector[] getVector() {
        return shape;
    }

    public void update(){
        if(isActive()){
        if((projectileDirection == Directions.right && rangeTraveled > range) || (projectileDirection == Directions.left && rangeTraveled < -range)){
           projectileState = state.inactive;
        }
        rangeTraveled += velocityX;
        posX+=velocityX;
        }
    }

    private void createShape() {
        shape = new Vector[]{new Vector(0, 0),new Vector(0.2f,0),new Vector(0.2f,0.2f),new Vector(0,0.2f)};
    }
}
