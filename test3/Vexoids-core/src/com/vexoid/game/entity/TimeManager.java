package com.vexoid.game.entity;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.vexoid.game.MainGame;
import com.vexoid.game.SoundManager;
import com.vexoid.game.TextureManager;
import com.vexoid.game.screen.GameOverScreen;
import com.vexoid.game.screen.GameScreen;
import com.vexoid.game.screen.ScreenManager;

public class TimeManager{

	private final ScreenManager screenManager;
	private final EntityManager entityManager;
	private int internalCounter = 0;
	private int COUNTER = 0, temp = 0, temp2 = 4;
	private int distance = 0;
	private int counterScore = 0;
	public static int level = 0,step = 1;
	
	private String difficulty;
	int modifier = 0,basicEnemiesCount = 3,AdvancedEnemiesCount = -2,LaserEnemiesCount=-1,
				secondIncrease = 30,ran = MathUtils.random(0,3);
	
	public TimeManager(ScreenManager screenManager, EntityManager entityManager,String difficulty) {
		this.screenManager = screenManager;
		this.entityManager = entityManager;
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
	//							  0 1 2 3 4 
	private int[] oneTimeFires = {0,0,0,0,0};	//	Have up to 4
	private int [] bossOneTimeFires = {0,0};	// Have 1
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
				screenManager.setScreen(new GameOverScreen(), difficulty);
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
	/*
	 * 	Level 1
	 */
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
				}
			if(step ==3){
				if(noEnemies()){
					addBasicLaserEnemy();
					addBasicEnemy();
				}
				if(COUNTER >= 15){
					step = 4;
				}
			}
			if(step ==4){
				if(noEnemies()){
					addBasicLaserEnemy();
					addAdvancedEnemy();
				}
				if(COUNTER >= 30)
					step = 5;
			}
			if(step ==5){
				if(noEnemies()){
					for (int i = 0; i < 2 + modifier; i++) {
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
					if(COUNTER >= 15){
						addBasicLaserEnemy();
					}
					if(COUNTER >= 40){
						addBasicLaserEnemy();
					}
					if(COUNTER >= 50){
						step = 9;
					}
				}
			}
			if(step == 9){
				if(noEnemies()){
					if(bossOneTimeFires[1] == 0){
						COUNTER = 0;
						SoundManager.stopMusic();
						SoundManager.cry1.play();
						bossOneTimeFires[1] = 1;
					}
					if(COUNTER >= 3)
					if(bossOneTimeFires[0] == 0){
						if(distance < 10)
							SoundManager.setMusic(SoundManager.extraMusic2, 0.5f, true);
						else
							SoundManager.setMusic(SoundManager.boss1Music, 0.6f, true);
						addBoss(1);
						bossOneTimeFires[0] = 1;
					}
					if(COUNTER >= 20){
					COUNTER = 0;
					step = 10;
					}
				}
			}
			if(step == 10){
				if(noEnemies()){
					if(oneTimeFires[3]==0){
						SoundManager.stopMusic();
						oneTimeFires[3] = 1;
					}
					temp ++;
				if(temp >= temp2 && COUNTER < 3){
					SoundManager.hit4.play();
					entityManager.doExplosion(new Vector2(MathUtils.random(0, MainGame.WIDTH),
							MathUtils.random(0, MainGame.HEIGHT)), 150, TextureManager.BOSS_1, 20, "red");
					temp = 0;
				}
				if(COUNTER >= 6){
				COUNTER = 0;
				step = 10;
				level = 9;
				}
				}
			}
		}
		if(level == 9)
		if(noEnemies()){
			if(oneTimeFires[4]==0){
				if(distance <= 25)
					SoundManager.setMusic(SoundManager.extraMusic, 0.8f, true);
				else
					if(distance <= 30)
						SoundManager.setMusic(SoundManager.extraMusic3, 0.6f, true);
					else
						SoundManager.setMusic(SoundManager.gameMusic, 0.8f, true);
				oneTimeFires[4] = 1;
			}
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
	public void addBoss(int bossNumber){
		GameScreen.addBoss(bossNumber);
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
