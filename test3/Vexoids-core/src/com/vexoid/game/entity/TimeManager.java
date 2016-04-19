package com.vexoid.game.entity;

import com.badlogic.gdx.math.MathUtils;
import com.vexoid.game.MainGame;
import com.vexoid.game.SoundManager;
import com.vexoid.game.TextureManager;
import com.vexoid.game.screen.GameOverScreen;
import com.vexoid.game.screen.GameScreen;
import com.vexoid.game.screen.ScreenManager;

public class TimeManager{

	private int internalCounter = 0;
	private int COUNTER = 0;
	private int distance = 0;
	private int counterScore = 0;
	private int level = 0;
	private String difficulty;
	int step = 1,modifier = 0,basicEnemiesCount = 3,AdvancedEnemiesCount = -2,LaserEnemiesCount=-1,
				secondIncrease = 30,ran = MathUtils.random(0,3);
//	EntityManager entityManager;
	
	public TimeManager(String difficulty) {
		this.difficulty = difficulty;
		if(difficulty=="hard"){
			modifier = 1;
		}
		if(difficulty=="medium"){
			modifier = 0;
		}
		if(difficulty=="easy"){
			modifier = 0;
		}
	}
	int[] oneTimeFires = {0,0,0};	//	Have 2
	int [] bossOneTimeFires = {0,0};	// Have 0
	public void update(){
		internalCounter ++;

		final int internalCounterLimit = 70;	// This is the internal counter in seconds
		final int counterScoreLimit = 2;		// This is the seconds to increase the distance
		
		if (internalCounter >= internalCounterLimit) {
			internalCounter = 0;
			COUNTER ++;
			counterScore ++;
		//System.out.println("Count: " + COUNTER);
		}
		if (counterScore >= counterScoreLimit && level > 0) {
			counterScore = 0;
			distance ++;
		}
		if(GameScreen.isGameOver()){
			if(oneTimeFires[1] == 0){
				COUNTER = 0;
				SoundManager.stopMusic();
				oneTimeFires[1] = 1;
			}
			level=-1;
		}
		// conditions for certain levels
		if(level == -1){
			if(COUNTER >= 5){
				ScreenManager.setScreen(new GameOverScreen(), difficulty);
			}
		}
		// Start levels
		if(level == 0){
			if(oneTimeFires[0] == 0){
				EntityManager.movePlayer(-100, -100, false);
				SoundManager.startSound.play();
				oneTimeFires[0] = 1;
			}
			if(COUNTER >= 10) {
				EntityManager.movePlayer(((MainGame.WIDTH/2) - (TextureManager.PLAYER.getWidth()/2)), 100, true);
				SoundManager.setMusic(SoundManager.gameMusic, 0.8f, true);
				level = 1;
				COUNTER = 0;
			}
		}
		if(level == 1){
			if(step == 1)
				if(noEnemies()){
					for (int i = 0; i <2 + modifier; i++) {
						addBasicEnemy();
					}
					if(COUNTER >= 15){
						step = 2;
						COUNTER = 15;
					}
					System.out.println("Is on step 1");
				}
			if(step ==2)
				if(noEnemies()){
					for (int i = 0; i <3 + modifier; i++) {
						addBasicEnemy();
					}
					if(COUNTER >= 30){
						step = 3;
						COUNTER=0;
					}
					System.out.println("Is on step 2");
				}
			if(step ==3){
				if(noEnemies()){
					addBasicLaserEnemy();
					addBasicEnemy();
					System.out.println("Is on step 3");
				}
				if(COUNTER >= 15){
					step = 4;
				}
			}
			if(step ==4){
				if(noEnemies()){
					addBasicLaserEnemy();
					addBasicEnemy();
					System.out.println("Is on step 4");
				}
				if(COUNTER >= 30)
					step = 5;
			}
			if(step ==5){
				if(noEnemies()){
					for (int i = 0; i <3 + modifier; i++) {
						addBasicEnemy();
					}
					addAdvancedEnemy();
					if(COUNTER >= 70){
						step = 6;
						COUNTER = 0;
					}
				}
			}
			if(step ==6){
				if(noEnemies()){
					for (int i = 0; i <3 + modifier; i++) {
						addBasicEnemy();
					}
					if(COUNTER >= 10){
						step = 7;
					}
				}
			}
			if(step ==7){
				if(noEnemies()){
					for (int i = 0; i <3 + modifier; i++) {
						addBasicEnemy();
					}
					addBasicLaserEnemy();
					if(COUNTER >= 20){
						step = 8;
					}
				}
			}
			if(step ==8){
				if(noEnemies()){
					for (int i = 0; i <4 + modifier; i++) {
						addBasicEnemy();
					}
					if(COUNTER >= 30){
						addBasicLaserEnemy();
					}
					if(COUNTER >= 40){
						addBasicLaserEnemy();
					}
					if(COUNTER >= 100){
						step = 9;
					}
				}
			}
			if(step ==9){
				if(noEnemies()){
					level = 9;
					step = 0;
				}
			}
		}
		if(level == 9)
		if(noEnemies()){
			ran = MathUtils.random(0,3);
			if (COUNTER >= secondIncrease){
				basicEnemiesCount += 1;
				secondIncrease += 45;
			}
			for (int i = 0; i <basicEnemiesCount; i++) {
				addBasicEnemy();
			}
			for (int i = 0; i <AdvancedEnemiesCount; i++) {
				addAdvancedEnemy();
			}
			if (ran >= 1)
				addBasicLaserEnemy();
			if (ran >= 2)
				AdvancedEnemiesCount += 1;
		}
	}
	public void addBasicEnemy(){
		GameScreen.addEnemies(1);
	}
	public void addAdvancedEnemy(){
		GameScreen.addEnemies(2);
	}
	public void addBasicLaserEnemy(){
		GameScreen.addEnemies(3);
	}
	
	public boolean noEnemies(){
		if(GameScreen.getEnemies() <= 0)
			return true;
		else
			return false;
	}
	public int getDistance(){
		return distance;
	}
	public int getCount(){
		return COUNTER;
	}
	public int getLevel(){
		return level;
	}
}
