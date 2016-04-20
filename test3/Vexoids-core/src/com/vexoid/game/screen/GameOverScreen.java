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

public class GameOverScreen extends Screen{
	private OrthoCamera camera;
	String gameDifficulty;
	private ScreenManager screenManager;
	
	BitmapFont displayTitleFont;
	BitmapFont displayGameDifficultyFont;
	BitmapFont displayScoreFont;
	BitmapFont displayDistanceFont;
	
	String title = "GameOver!";
	int score, distance;
	
	TextButton StartButton, OptionsButton, ExitButton;
	TextButtonStyle textButtonStyle;
	Skin buttonSkin;
	
	public void create(ScreenManager screenManager, String difficulty) {
		this.screenManager = screenManager;
		gameDifficulty = difficulty;
		camera = new OrthoCamera();
		camera.resize();
		
		displayTitleFont = new BitmapFont();
		displayGameDifficultyFont = new BitmapFont();
		displayScoreFont = new BitmapFont();
		displayDistanceFont = new BitmapFont();
		
		distance = GameScreen.getDistance();
		score = GameScreen.getScore();

		textButtonStyle = new TextButtonStyle();
		textButtonStyle.font = displayGameDifficultyFont;
		
		StartButton = new TextButton("Press Enter To Start Again", textButtonStyle);
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
			screenManager.setScreen(new MenuScreen(), gameDifficulty);
		}
	public void update() {
		camera.update();
	}

	public void render(SpriteBatch sb) {
		sb.setProjectionMatrix(camera.combined);
		sb.begin();
		displayTitleFont.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		displayTitleFont.draw(sb, title, (MainGame.WIDTH / 2) - 50, ((MainGame.HEIGHT / 2)+ (MainGame.HEIGHT /4)));
		
		displayScoreFont.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		displayScoreFont.draw(sb, "Score: " + score, (MainGame.WIDTH / 2) - 40, ((MainGame.HEIGHT / 2)+ (MainGame.HEIGHT /4))-20);
		
		displayDistanceFont.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		displayDistanceFont.draw(sb, "Distance: " + distance, (MainGame.WIDTH / 2)-40, ((MainGame.HEIGHT / 2)+ (MainGame.HEIGHT /4))-40);
		
		
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
		return "GameOverScreen";
	}
}