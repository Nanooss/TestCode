package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.controller.InputHandler;
import com.mygdx.game.model.GameState;
import com.mygdx.game.view.Render;

public class main extends ApplicationAdapter {
	Render rc;
	GameState game;
	
	@Override
	public void create () {
		rc = new Render();
		game = new GameState(rc);
		Gdx.input.setInputProcessor(new InputHandler(game.getPlayer()));
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.update(Gdx.graphics.getDeltaTime());
		game.render();
	}
}
