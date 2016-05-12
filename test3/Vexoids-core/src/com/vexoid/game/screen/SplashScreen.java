package com.vexoid.game.screen;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.vexoid.game.MainGame;
import com.vexoid.game.SoundManager;
import com.vexoid.game.TextureManager;
import com.vexoid.game.camera.OrthoCamera;


public class SplashScreen extends Screen{

	private OrthoCamera camera;
	String difficulty;
	String welcomeMessage = "Welcome to Vexoids! Press Enter to continue...\nIf you dare...";
	
	Texture title = TextureManager.TITLE_IMAGE;
	Texture menuTitle = TextureManager.MENU_IMAGE;
	Texture difficultyImage = TextureManager.DIFFICULTY_MEDIUM;
	
	BitmapFont displayOptions;
	
	public void create(ScreenManager screenManager, String difficulty) {
		this.difficulty = difficulty;
		camera = new OrthoCamera();
		camera.resize();
		
		displayOptions = new BitmapFont();
		SoundManager.setMusic(SoundManager.menuMusic, 0.8f, true);
	}

	int secondIncrease = 30;
	int starcount = 0;

	static boolean clearedEntities = false;
	
	int Switch = 0, Toggle = 1, Switch2 = 0, Toggle2 = 1;
	
	public void update(){
	}

	public void render(SpriteBatch sb) {
		sb.setProjectionMatrix(camera.combined);
		sb.begin();
		
		displayOptions.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		displayOptions.draw(sb, welcomeMessage, (MainGame.WIDTH/3), 150);
		
		sb.draw(title, ((MainGame.WIDTH/2) - (TextureManager.TITLE_IMAGE.getWidth()/2)), (MainGame.HEIGHT / 2));
		sb.end();
	}

	public void resize(int width, int height) {
		camera.resize();
	}

	public void dispose() {}

	public void pause() {}
	
	public void resume() {}

	public String whatScreen() {
		return "SplashScreen";
	}

}
