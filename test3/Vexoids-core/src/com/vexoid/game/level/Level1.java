package com.vexoid.game.level;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.vexoid.game.MainGame;
import com.vexoid.game.SoundManager;
import com.vexoid.game.TextureManager;
import com.vexoid.game.entity.EntityManager;
import com.vexoid.game.screen.GameOverScreen;
import com.vexoid.game.screen.GameScreen;
import com.vexoid.game.screen.ScreenManager;

public class Level1 extends Level{

	private EntityManager entityManager;
	private ScreenManager screenManager;
	private String difficulty;
	
	private final int level = 1;
	
	private int levelScore = 0;
	
	private boolean didPlayerFinish = false;
	
	private int internalCounter = 0;
	private int COUNTER = 0, temp = 0, temp2 = 4;
	private int distance = 0;
	private int counterScore = 0;
	private int step = 1;
	
	int modifier = 0,basicEnemiesCount = 3,AdvancedEnemiesCount = -2,LaserEnemiesCount=-1,
				secondIncrease = 30,ran = MathUtils.random(0,3);

	public void create(ScreenManager screenManager, EntityManager entityManager,String difficulty) {
		this.entityManager = entityManager;
		this.screenManager = screenManager;
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
		if (counterScore >= counterScoreLimit && level > 0 && !entityManager.isGameOver() && !didPlayerFinish) {
			counterScore = 0;
			distance ++;
		}
		if(GameScreen.isGameOver()){
			if(oneTimeFires[1] == 0){
				COUNTER = 0;
				SoundManager.stopMusic();
				oneTimeFires[1] = 1;
				step = 0;
			}
		}
		if(step == 0){
			if(COUNTER > 5){
				screenManager.setScreen(new GameOverScreen(), difficulty);
			}
		}
		
		if(step == 1)
			if(noEnemies()){
				for (int i = 0; i <2 + modifier; i++) {
					entityManager.addBasicEnemy();
				}
				if(COUNTER >= 15){
					step = 2;
					COUNTER = 15;
				}
			}
		if(step ==2)
			if(noEnemies()){
				for (int i = 0; i <3 + modifier; i++) {
					entityManager.addBasicEnemy();
				}
				if(COUNTER >= 30){
					step = 3;
					COUNTER=0;
				}
			}
		if(step ==3){
			if(noEnemies()){
				entityManager.addBasicLaserEnemy();
				entityManager.addBasicEnemy();
			}
			if(COUNTER >= 15){
				step = 4;
			}
		}
		if(step ==4){
			if(noEnemies()){
				entityManager.addBasicLaserEnemy();
				entityManager.addAdvancedEnemy();
			}
			if(COUNTER >= 30)
				step = 5;
		}
		if(step ==5){
			if(noEnemies()){
				for (int i = 0; i < 2 + modifier; i++) {
					entityManager.addBasicEnemy();
				}
				entityManager.addAdvancedEnemy();
				if(COUNTER >= 70){
					step = 6;
					COUNTER = 0;
				}
			}
		}
		if(step ==6){
			if(noEnemies()){
				for (int i = 0; i <3 + modifier; i++) {
					entityManager.addBasicEnemy();
				}
				if(COUNTER >= 10){
					step = 7;
				}
			}
		}
		if(step ==7){
			if(noEnemies()){
				for (int i = 0; i <3 + modifier; i++) {
					entityManager.addBasicEnemy();
				}
				entityManager.addBasicLaserEnemy();
				if(COUNTER >= 20){
					step = 8;
				}
			}
		}
		if(step ==8){
			if(noEnemies()){
				for (int i = 0; i <4 + modifier; i++) {
					entityManager.addBasicEnemy();
				}
				if(COUNTER >= 15){
					entityManager.addBasicLaserEnemy();
				}
				if(COUNTER >= 40){
					entityManager.addBasicLaserEnemy();
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

					entityManager.addBoss1();
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
			if(COUNTER >= 3 && COUNTER < 6){
				didPlayerFinish = true;
			}
			if(COUNTER >= 6){
					COUNTER = 0;
					step = 10;
					LevelManager.addToTotalDistance(distance);
					LevelManager.setCurrentLevel(new InfinityLevel());
				}
			}
		}
	}
	
	public boolean noEnemies(){
		if(entityManager.getEntities() <= 0)
			return true;
		else
			return false;
	}
	
	public boolean didPlayerFinish(){
		return didPlayerFinish;
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
	
	public void setStep(int step){
		this.step = step;
	}
	
	public void dispose() {}
	
	public void pause() {}
	
	public void resume() {}

	public void addToLevelScore(int amount) {
		levelScore += amount;
	}

	public int getLevelScore() {
		return levelScore;
	}
}
