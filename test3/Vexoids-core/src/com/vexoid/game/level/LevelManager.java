package com.vexoid.game.level;

import com.vexoid.game.level.Level;
import com.vexoid.game.SoundManager;
import com.vexoid.game.entity.EntityManager;
import com.vexoid.game.screen.GameScreen;
import com.vexoid.game.screen.ScreenManager;

public class LevelManager {

	private static Level currentLevel;
	protected static ScreenManager screenManager;
	protected static EntityManager entityManager;
	private static String difficulty;
	public int Level = 0;
	public static int TotalDistance = 0;
	
	public LevelManager(ScreenManager screenManager, EntityManager entityManager,String difficulty){
		LevelManager.screenManager = screenManager;
		LevelManager.entityManager = entityManager;
		LevelManager.difficulty = difficulty;
	}
	int oneTimeFire1 = 0;
	public void update(){
		if (currentLevel !=null){
			currentLevel.update();
			Level = currentLevel.getLevel();
		}
		if(GameScreen.isGameOver()){
			if(oneTimeFire1 == 0){
				SoundManager.stopMusic();
				oneTimeFire1 = 1;
			}
		}
	}
	public static void setCurrentLevel(Level level){
		if (currentLevel !=null) currentLevel.dispose();
		currentLevel = level;
		currentLevel.create(screenManager, entityManager, difficulty);
	}
	public static Level getCurrentLevel(){
		return currentLevel;
	}
	public static void addToTotalDistance(int amount){
		TotalDistance += amount;
	}
	public static int getTotalDistance(){
		return TotalDistance;
	}
	public int getLevel(){
		return Level;
	}
	public static void setCurrentLevelStep(int step) {
		currentLevel.setStep(step);
	}
}
