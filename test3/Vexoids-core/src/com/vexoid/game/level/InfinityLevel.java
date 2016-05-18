package com.vexoid.game.level;

import com.badlogic.gdx.math.MathUtils;
import com.vexoid.game.SoundManager;
import com.vexoid.game.entity.EntityManager;
import com.vexoid.game.screen.GameOverScreen;
import com.vexoid.game.screen.GameScreen;
import com.vexoid.game.screen.ScreenManager;

public class InfinityLevel extends Level{

	private EntityManager entityManager;
	private ScreenManager screenManager;
	private String difficulty;
	
	private final int level = 9;
	
	int levelScore = 0;
	
	public void create(ScreenManager screenManager, EntityManager entityManager, String difficulty) {
		this.entityManager = entityManager;
		this.difficulty = difficulty;
		this.screenManager = screenManager;
	}
	
	private int internalCounter = 0;
	private int COUNTER = 0;
	private int distance = 0;
	private int counterScore = 0;
	private int[] oneTimeFires = {0,0};
	private int step = 1;

	int modifier = 0,basicEnemiesCount = 3,AdvancedEnemiesCount = -2,LaserEnemiesCount=-1,
				secondIncrease = 30,ran = MathUtils.random(0,3);
	
	public void update() {
		internalCounter ++;

		final int internalCounterLimit = 70;	// This is the internal counter in seconds
		final int counterScoreLimit = 2;		// This is the seconds to increase the distance
		
		if (internalCounter >= internalCounterLimit) {
			internalCounter = 0;
			COUNTER ++;
			counterScore ++;
		//System.out.println("Count: " + COUNTER);
		}
		if (counterScore >= counterScoreLimit && !entityManager.isGameOver()) {
			counterScore = 0;
			distance ++;
		}
		if(GameScreen.isGameOver()){
			if(oneTimeFires[0] == 0){
				COUNTER = 0;
				SoundManager.stopMusic();
				oneTimeFires[0] = 1;
				step = 0;
			}
		}
		if(step == 0){
			if(COUNTER > 5){
				screenManager.setScreen(new GameOverScreen(), difficulty);
			}
		}
		if(step == 1)
		if(entityManager.noEnemies()){
			if(oneTimeFires[1]==0){
				if(distance <= 25)
					SoundManager.setMusic(SoundManager.extraMusic, 0.8f, true);
				else
					if(distance <= 30)
						SoundManager.setMusic(SoundManager.extraMusic3, 0.6f, true);
					else
						SoundManager.setMusic(SoundManager.gameMusic, 0.8f, true);
				oneTimeFires[1] = 1;
			}
			ran = MathUtils.random(0,3);
			if (COUNTER >= secondIncrease){
				basicEnemiesCount += 1;
				secondIncrease += 45;
			}
			for (int i = 0; i <EntityManager.basicEnemiesCount; i++) {
				entityManager.addBasicEnemy();
			}
			for (int i = 0; i <EntityManager.AdvancedEnemiesCount; i++) {
				entityManager.addAdvancedEnemy();
			}
			if (ran >= 1)
				entityManager.addBasicLaserEnemy();
			if (ran >= 2)
				AdvancedEnemiesCount += 1;
		}
	}

	public int getLevel() {
		return level;
	}

	public void setStep(int step) {
	}

	public int getCount() {
		return COUNTER;
	}

	public int getDistance() {
		return distance;
	}

	public void dispose() {}

	public void pause() {}

	public void resume() {}

	public boolean didPlayerFinish() {
		return false;
	}

	public void addToLevelScore(int amount) {
		levelScore += amount;
	}

	public int getLevelScore() {
		return 0;
	}
}
