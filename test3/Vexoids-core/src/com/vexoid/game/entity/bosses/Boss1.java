package com.vexoid.game.entity.bosses;

import com.badlogic.gdx.math.Vector2;
import com.vexoid.game.TextureManager;
import com.vexoid.game.entity.Entity;
import com.vexoid.game.entity.EntityManager;

public class Boss1 extends Entity{

	private final EntityManager entityManager;
	
	public Boss1(Vector2 pos, Vector2 direction, EntityManager entityManager,String difficulty) {
		super(TextureManager.BOSS_1, pos, direction);
		this.entityManager = entityManager;
	}

	//health stuff
	float healthPercent = 100;
	boolean entityDied = false;
	public void health(){
	if(healthPercent >= 750.0)
		healthPercent=750.0f;
	}
	public void increaseHealth(float ammount){
		healthPercent += ammount;
		if(healthPercent >= 750.0)
			healthPercent=750;
	}
	public void decreaseHealth(float ammount){
		healthPercent -= ammount;
		if(healthPercent <= 0)
			entityDied = true;
	}
	public float getHealth(){
		return healthPercent;
	}
	public boolean entityDied(){
		return entityDied;
	}
		
	public void update() {
		health();
	}
}
