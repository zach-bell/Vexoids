package com.vexoid.game.level;

import com.vexoid.game.MainGame;
import com.vexoid.game.SoundManager;
import com.vexoid.game.TextureManager;
import com.vexoid.game.entity.EntityManager;
import com.vexoid.game.screen.ScreenManager;

public class IntroLevel extends Level {

	private EntityManager entityManager;
	
	private final int Level = 0;
	public void create(ScreenManager screenManager, EntityManager entityManager, String difficulty) {
		this.entityManager = entityManager;
	}

	private int internalCounter = 0;
	private int COUNTER = 0;
	private int[] oneTimeFires = {0,0,0,0,0};
	
	public void update() {
		internalCounter ++;

		final int internalCounterLimit = 70;	// This is the internal counter in seconds
		
		if (internalCounter >= internalCounterLimit) {
			internalCounter = 0;
			COUNTER ++;
		//System.out.println("Count: " + COUNTER);
		}
	// Start levels
		if(oneTimeFires[0] == 0){
			entityManager.movePlayer(-100, -100, false);
			SoundManager.startSound.play();
			oneTimeFires[0] = 1;
		}
		if(COUNTER >= 10) {
			entityManager.movePlayer(((MainGame.WIDTH/2) - (TextureManager.PLAYER.getWidth()/2)), 100, true);
			SoundManager.setMusic(SoundManager.gameMusic, 0.8f, true);
			COUNTER = 0;
			LevelManager.setCurrentLevel(new Level1());
		}
	}

	public int getLevel() {
		return Level;
	}

	public int getCount() {
		return COUNTER;
	}

	public int getDistance() {
		return 0;
	}
	public void setStep(int step){
		
	}
	public void dispose() {}
	
	public void pause() {}
	
	public void resume() {}

	public boolean didPlayerFinish() {
		return false;
	}

	@Override
	public void addToLevelScore(int amount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getLevelScore() {
		// TODO Auto-generated method stub
		return 0;
	}
}
