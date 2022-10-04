package com.mygdx.game.controller;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.model.PlayableActor;
import com.mygdx.game.model.Weapon;
import com.mygdx.game.model.WorldArea;

public class InputHandler implements InputProcessor {
    private PlayableActor player;
    public InputHandler(PlayableActor inputPlayer){
        this.player = inputPlayer;
    }
    @Override
    public boolean keyDown(int keycode) {
        player.getData().inputKeyDown(keycode);
        player.getWeapon().inputKeyDown(keycode);
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        player.getData().inputKeyUp(keycode);
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
