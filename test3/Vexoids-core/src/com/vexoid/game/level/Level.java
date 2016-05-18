package com.vexoid.game.level;

import com.vexoid.game.entity.EntityManager;
import com.vexoid.game.screen.ScreenManager;

public abstract class Level {

	public abstract void create(ScreenManager screenManager, EntityManager entityManager,String difficulty);

	public abstract void update();
	
	public abstract boolean didPlayerFinish();
	
	public abstract int getLevel();
	
	public abstract void setStep(int step);
	
	public abstract int getCount();
	
	public abstract int getDistance();
	
	public abstract void addToLevelScore(int amount);
	
	public abstract int getLevelScore();

	public abstract void dispose();
	
	public abstract void pause();
	
	public abstract void resume();
}
