package com.vexoid.game.entity.bosses;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.vexoid.game.MainGame;
import com.vexoid.game.SoundManager;
import com.vexoid.game.TextureManager;
import com.vexoid.game.entity.Entity;
import com.vexoid.game.entity.EntityManager;
import com.vexoid.game.entity.bullets.bullet1;

public class Boss1 extends Entity{

	private final EntityManager entityManager;
	private int count = 0, shooting = 0;
	private float spread, speed;
	private long lastFire;
	int timeDelay=0, stageDelay = 600;
	
	private int ran = 1, firePower = 24, shotDelay = 0;
	
	String difficulty;
	
	public Boss1(Vector2 pos, Vector2 direction, EntityManager entityManager,String difficulty) {
		super(TextureManager.BOSS_1, pos, direction);
		this.entityManager = entityManager;
		this.difficulty = difficulty;
		stageDelay = MathUtils.random(450,600);
		if (this.difficulty == "hard") {
			timeDelay = 250;
			firePower = 24;
			additionalHealth = 500;
		}
		if (this.difficulty == "medium") {
			timeDelay = 500;
			firePower = 14;
			additionalHealth = 0;
		}
		if (this.difficulty == "easy") {
			timeDelay = 800;
			firePower = 8;
			additionalHealth = 0;
		}
		healthPercent = 1750 + additionalHealth;
	}

	//health stuff
	float additionalHealth = 0;
	float healthPercent = 1750 +(additionalHealth);
	boolean entityDied = false;
	private int[] oneTime = {0,0,0,0,0,0};
	public void health(){
		if(healthPercent >= 1750.0 +(additionalHealth))
			healthPercent = 1750.0f + (additionalHealth);
		if(healthPercent < 1500 && oneTime[0] == 0){
			cry();
			oneTime[0] = 1;
		}
		if(healthPercent < 1250 && oneTime[1] == 0){
			cry();
			oneTime[1] = 1;
		}
		if(healthPercent < 1000 && oneTime[2] == 0){
			cry();
			oneTime[2] = 1;
		}
		if(healthPercent < 750 && oneTime[3] == 0){
			cry();
			oneTime[3] = 1;
		}
		if(healthPercent < 500 && oneTime[4] == 0){
			cry();
			oneTime[4] = 1;
		}
		if(healthPercent < 250 && oneTime[5] == 0){
			cry();
			oneTime[5] = 1;
		}
	}
	public void cry(){
		int ran = MathUtils.random(1,2);
		if(ran == 1)
			SoundManager.cry2.play();
		if(ran == 2)
			SoundManager.cry3.play();
	}
	public void increaseHealth(float ammount){
		healthPercent += ammount;
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
	public Rectangle getBounds() {
		return new Rectangle(pos.x, (pos.y + ((texture.getHeight()/2)/1.25f)),
				texture.getWidth(), (texture.getHeight()-(texture.getHeight()/1.25f)));
	}

	float xMovement;
	float yMovement;
	float xSpeed = 2;
	float ySpeed = 2;
	private int xTarget = MathUtils.random(0, MainGame.WIDTH - TextureManager.BOSS_1.getWidth());
	private int yTarget = MathUtils.random(MainGame.HEIGHT - TextureManager.BOSS_1.getHeight(), MainGame.HEIGHT - 200);
	Vector2 midWay = new Vector2(((TextureManager.BOSS_1.getWidth()/2 )- 20), (TextureManager.BOSS_1.getHeight()/2));
	boolean sound = true;
	
	public void update() {
		health();
		
// X movement
		if (pos.x > xTarget+2 || pos.x < xTarget-2){
			if (pos.x > xTarget){
				xMovement = -xSpeed;
				} else {
				xMovement = xSpeed;
				}
		} else {
			xSpeed = MathUtils.random(0.3f, 0.5f);
			xTarget = MathUtils.random(0, MainGame.WIDTH - TextureManager.BOSS_1.getWidth());
		}

// Y movement
			if (pos.y > yTarget+5 || pos.y < yTarget-5){
				if (pos.y > yTarget) {
				yMovement = -ySpeed;
				} else {
				yMovement = ySpeed;
				}
			} else{
				yTarget = MathUtils.random(MainGame.HEIGHT - TextureManager.BOSS_1.getHeight(), MainGame.HEIGHT - 200);
				ySpeed = 0.1f;
			}
			pos.add(direction.set(xMovement, yMovement));

//	Shooting and stuff
			
		if (pos.y < MainGame.HEIGHT - (TextureManager.BOSS_1.getHeight()/2)){
			if (System.currentTimeMillis() - lastFire >= MathUtils.random(timeDelay, timeDelay+1500)) {
				shooting ++;
				if(shooting >= shotDelay+2 && ran <= 1) {
					count ++;
					spread = 4;
					speed = MathUtils.random(2.2f, 2.8f);
					entityManager.addEntity(new bullet1(pos.cpy().add(midWay), spread, speed));
					SoundManager.shot1.play(0.4f);
					if (count >= firePower*2) {
						count = 0;
						lastFire = System.currentTimeMillis();
						ran = MathUtils.random(0,4);
					}
					shooting = 0;
				}
					if(shooting >= shotDelay && ran <= 3 && ran >= 2) {
						count ++;
						spread = 5;
						speed = 7;
						entityManager.addEntity(new bullet1(pos.cpy().add(midWay), spread, speed));
						SoundManager.shot1.play(0.4f);
						if (count >= firePower) {
							count = 0;
							lastFire = System.currentTimeMillis();
							ran = MathUtils.random(0,4);
						}
						shooting = 0;
					}
					if(shooting >= shotDelay+8 && ran == 4) {
						count ++;
						spread = 7;
						speed = 5;
						entityManager.addEntity(new bullet1(pos.cpy().add(midWay), spread, speed));
						entityManager.addEntity(new bullet1(pos.cpy().add(midWay), spread, speed));
						entityManager.addEntity(new bullet1(pos.cpy().add(midWay), spread, speed));
						entityManager.addEntity(new bullet1(pos.cpy().add(midWay), spread, speed));
						entityManager.addEntity(new bullet1(pos.cpy().add(midWay), spread, speed));
						SoundManager.shot1.play(0.4f);
						if (count >= firePower) {
							count = 0;
							lastFire = System.currentTimeMillis()+1000;
							ran = MathUtils.random(0,4);
						}
						shooting = 0;
					}
				}
		}
	}
}
