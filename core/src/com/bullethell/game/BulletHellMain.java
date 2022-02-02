package com.bullethell.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.bullethell.game.state.GameStateHandler;
import com.bullethell.game.state.MainMenuState;

public class BulletHellMain extends ApplicationAdapter {
	SpriteBatch batch;
	
	
	private GameStateHandler gameStateHandler;
	public static final int width = 800;
	public static final int height = 800;
	
	@Override
	public void create () {
		gameStateHandler = new GameStateHandler();
		gameStateHandler.add(new MainMenuState(gameStateHandler));
		batch = new SpriteBatch();
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		gameStateHandler.update();		
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
