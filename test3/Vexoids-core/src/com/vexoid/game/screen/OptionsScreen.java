package com.vexoid.game.screen;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.vexoid.game.MainGame;
import com.vexoid.game.camera.OrthoCamera;

public class OptionsScreen extends Screen{
	
	private OrthoCamera camera;
	String difficulty, Options="Options PRESS ESC TO EXIT NIGGA";
	ScreenManager screenManager;
	
	BitmapFont displayOptionsTitle;
	
	public void create(ScreenManager screenManager, String difficulty) {
		this.difficulty = difficulty;
		this.screenManager = screenManager;
		camera = new OrthoCamera();
		camera.resize();
		
		displayOptionsTitle = new BitmapFont();
	}

	public void update() {
		camera.update();
	}

	public void render(SpriteBatch sb) {
		sb.setProjectionMatrix(camera.combined);
		sb.begin();
		displayOptionsTitle.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		displayOptionsTitle.draw(sb, Options, MainGame.WIDTH / 2 - 20, MainGame.HEIGHT - 100);
		sb.end();
	}
	public void resize(int width, int height) {
		camera.resize();
	}
	public void dispose() {}
	
	public void pause() {}
	
	public void resume() {}
	
	public String whatScreen() {
		return "OptionsScreen";
	}
}
