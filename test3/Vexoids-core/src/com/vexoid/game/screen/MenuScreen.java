package com.vexoid.game.screen;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.vexoid.game.MainGame;
import com.vexoid.game.SoundManager;
import com.vexoid.game.camera.OrthoCamera;

public class MenuScreen extends Screen{

	private OrthoCamera camera;
	String gameDifficulty;
	
	BitmapFont displayTitleFont;
	BitmapFont displayGameDifficultyFont;
	
	String title = "Vexoids";
	
	TextButton StartButton, OptionsButton, ExitButton;
	TextButtonStyle textButtonStyle;
	Skin buttonSkin;
	
	public void create(String difficulty) {
		gameDifficulty = difficulty;
		camera = new OrthoCamera();
		camera.resize();
		
		SoundManager.setMusic(SoundManager.menuMusic, 0.8f, true);
		
		displayTitleFont = new BitmapFont();
		displayGameDifficultyFont = new BitmapFont();

		textButtonStyle = new TextButtonStyle();
		textButtonStyle.font = displayGameDifficultyFont;
		
		StartButton = new TextButton("Press Enter", textButtonStyle);
		StartButton.setPosition((MainGame.WIDTH / 2) - 50, (MainGame.HEIGHT / 2) + 25);
		StartButton.setSize(100, 100);
		StartButton.addListener(new ClickListener() {
			@Override
			public void touchUp(InputEvent e, float x, float y, int point, int button){
				startButtonClicked();
			}
		});
	}
		public void startButtonClicked(){
			ScreenManager.setScreen(new GameScreen(), gameDifficulty);
		}
	public void update() {
		camera.update();
	}

	public void render(SpriteBatch sb) {
		sb.setProjectionMatrix(camera.combined);
		sb.begin();
		displayTitleFont.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		displayTitleFont.draw(sb, title, (MainGame.WIDTH / 2) - 27, ((MainGame.HEIGHT / 2)+ (MainGame.HEIGHT /4)));
		
		StartButton.draw(sb, 1.0f);
		sb.end();
	}

	public void resize(int width, int height) {
		camera.resize();
	}

	public void dispose() {
		
	}

	public void pause() {
		
	}

	public void resume() {
		
	}

	public void handleMusic(Music music, float vol, boolean loop) {
		SoundManager.stopMusic();
		SoundManager.setMusic(music, vol, loop);
		SoundManager.playMusic();
	}

	public String whatScreen() {
		return "MenuScreen";
	}

}
