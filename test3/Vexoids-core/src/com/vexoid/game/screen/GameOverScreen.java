package com.vexoid.game.screen;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.vexoid.game.MainGame;
import com.vexoid.game.SoundManager;
import com.vexoid.game.camera.OrthoCamera;
import com.vexoid.game.level.IntroLevel;
import com.vexoid.game.level.LevelManager;

public class GameOverScreen extends Screen{
	private OrthoCamera camera;
	String gameDifficulty;
	
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
	}
	
	private int[] oneTime = {0};
	public void update() {
		camera.update();
		if(oneTime[0] == 0){
			LevelManager.setCurrentLevel(new IntroLevel());;
			LevelManager.setCurrentLevelStep(1);;
			oneTime[0] = 1;
		}
	}

	public void render(SpriteBatch sb) {
		sb.setProjectionMatrix(camera.combined);
		sb.begin();
		displayTitleFont.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		displayTitleFont.draw(sb, title, (MainGame.WIDTH / 2) - 50, ((MainGame.HEIGHT / 2)+ (MainGame.HEIGHT /4)));
		
		displayScoreFont.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		displayScoreFont.draw(sb, "Total Score: " + score, (MainGame.WIDTH / 2) - 40, ((MainGame.HEIGHT / 2)+ (MainGame.HEIGHT /4))-20);
		
		displayDistanceFont.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		displayDistanceFont.draw(sb, "Total Distance: " + distance, (MainGame.WIDTH / 2)-40, ((MainGame.HEIGHT / 2)+ (MainGame.HEIGHT /4))-40);
		
		
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