package com.vexoid.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.vexoid.game.SoundManager;

public class ScreenManager {
	
	private static Screen currentScreen;
	private static String Difficulty;
	
	public static void setScreen(Screen screen, String difficulty) {
		Difficulty = difficulty;
		if (currentScreen !=null) currentScreen.dispose();
		currentScreen = screen;
		currentScreen.create(difficulty);
	}
	
	public static Screen getCurrentScreen() {
		return currentScreen;
	}
	public static void screenManagement(){
		if (Gdx.input.isKeyPressed(Keys.ENTER)&& (ScreenManager.getCurrentScreen().whatScreen() == "MenuScreen")){
			ScreenManager.getCurrentScreen().dispose();
			SoundManager.stopMusic();
			ScreenManager.setScreen(new GameScreen(), Difficulty);
		}
		if (Gdx.input.isKeyPressed(Keys.ENTER)&& (ScreenManager.getCurrentScreen().whatScreen() == "GameOverScreen")){
			ScreenManager.getCurrentScreen().dispose();
			SoundManager.stopMusic();
			ScreenManager.setScreen(new MenuScreen(), Difficulty);
		}
		
	}
}
