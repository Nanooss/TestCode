package com.mygdx.game.ModelData;

public class PlayerData {

    public float posX;
    public float posY;

    public float forceX;
    public float forceY;

    public float velocityX;
    public float velocityY;

    public Vector[] shape;

    public Vector[] getVector() {
        return shape;
    }

    boolean onGround = true;

    public float getDirectionMult() {
        if(playerDirection == Directions.left) return -1;
        else return 1;
    }

    enum States{idle,walking,jumping, falling, running}
    enum Directions{left,right}

    States playerState = States.idle;
    Directions playerDirection = Directions.right;

    public PlayerData(float spawnPosX, float spawnPosY, float spawnForceX, float spawnForceY){
        this.posX = spawnPosX;
        this.posY = spawnPosY;
        this.forceX = spawnForceX;
        this.forceY = spawnForceY;
        createShape();
    }

    private void createShape() {
        shape = new Vector[]{new Vector(0, 0),new Vector(1,0),new Vector(1,1),new Vector(0,1)};
    }

    public void update() {
        if (posY<1)collisionGroundBegin();
        if(posY>1)collisionGroundEnd();
        positionUpdate();
        animationUpdate();
    }

    public void collisionGroundBegin() {
        forceY = 0;
        posY = 1;
        onGround = true;
        playerState = States.idle;
        velocityY = 0;
    }

    public void collisionGroundEnd() {
        onGround = false;
    }

    private void animationUpdate() {
    }

    private void positionUpdate() {
        if (playerState == States.jumping || playerState == States.falling) jump();
        else{
            velocityY=0;
        posX += forceX;
        posY += velocityY;
            }
    }

    private void jump() {
        posX += forceX;
        if (velocityY < 0.3 && playerState == States.jumping){
            velocityY += 0.07;
        }else{
            playerState = States.falling;
            velocityY -=0.0098;
        }
        posY += velocityY;
    }

    public void inputKeyDown(int key){
        switch (key){
            case 51: // w
                if (onGround){
                playerState = States.jumping;}
                break;
            case 47: // s
                break;
            case 29: // ad
                if (onGround){
                        playerDirection = Directions.left;}
                forceX = -0.1f;
                break;
            case 32: //d
                if (onGround){
                    playerDirection = Directions.right;}
                forceX = 0.1f;
                break;
        }
    }

    public void inputKeyUp(int key){
        switch (key){
            case 51:
                forceY = 0;
                break;// w
            case 47: // s
                forceY = 0;
                break;
            case 29: // a
            case 32: //d
                if (onGround){
                    playerState = States.idle;}
                forceX = 0;
                break;
        }
    }

}
