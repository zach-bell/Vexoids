package com.vexoid.game.entity;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.vexoid.game.MainGame;
import com.vexoid.game.SoundManager;
import com.vexoid.game.TextureManager;
import com.vexoid.game.entity.bullets.bullet1;
import com.vexoid.game.screen.GameScreen;

public class AdvancedEnemy extends Entity {

	private String gameDifficulty;
	private long lastFire;
	private int firePower = 4;
	public int timeDelay;
	public int shotDelay;
	private int ranLimit;
	private int ran;
	float initSpeed = (gameDifficulty == "hard"? 0.75f : (gameDifficulty== "medium"? 0.3f : (gameDifficulty== "easy"? 0.25f : 0.25f)));

	public AdvancedEnemy(Vector2 pos, Vector2 direction, EntityManager entityManager, String difficulty) {
		super(TextureManager.ADVANCED_ENEMY, pos, direction);
		this.entityManager = entityManager;
		gameDifficulty = difficulty;
		
		if (gameDifficulty == "hard") {
			firePower = MathUtils.random(4,7);
			timeDelay = 1500;
			shotDelay = MathUtils.random(0,20);
			ranLimit = 3;
		}
		if (gameDifficulty == "medium") {
			firePower = MathUtils.random(3,5);
			timeDelay = 2000;
			shotDelay = MathUtils.random(10,30);
			ranLimit = 3;
		}
		if (gameDifficulty == "easy") {
			firePower = MathUtils.random(2,4);
			timeDelay = 3000;
			shotDelay = MathUtils.random(20,40);
			ranLimit = 2;
		}
	}

	//health stuff
	float healthPercent = 100;
	boolean entityDied = false;
	public void health(){
	if(healthPercent >= 100.000)
		healthPercent=100.0f;
	}
	public void increaseHealth(float ammount){
		healthPercent += ammount;
		if(healthPercent >= 100.0)
			healthPercent=100;
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
		
	private final EntityManager entityManager;
	int count = 0, time = 0, shooting = 0;
	float xMovement;
	float yMovement;
	float xSpeed = MathUtils.random(0.5f,2.0f);
	float ySpeed = 8 * initSpeed;
	private int xTarget = MathUtils.random(0, MainGame.WIDTH - TextureManager.BASIC_ENEMY.getWidth());
	private int yTarget = MathUtils.random(MainGame.HEIGHT/2, MainGame.HEIGHT - TextureManager.BASIC_ENEMY.getHeight());
	boolean x = true;
	
	public void update() {
		time = GameScreen.count;
		if (x){ran = MathUtils.random(1,ranLimit);}
		x=false;
	//Movement controlling what a bitch
		if (gameDifficulty == "hard"){
		// X movement
			if (pos.x > xTarget+5 || pos.x < xTarget-5){
				if (pos.x > xTarget) {
				xMovement = -xSpeed;
				} else {
				xMovement = xSpeed;
				}
			} else{
				xTarget = MathUtils.random(0, MainGame.WIDTH-TextureManager.BASIC_ENEMY.getWidth());
				xSpeed = MathUtils.random(1.0f,3.0f);
			}
		// Y movement
			if (pos.y > yTarget+5 || pos.y < yTarget-5){
				if (pos.y > yTarget) {
				yMovement = -ySpeed;
				} else {
				yMovement = ySpeed;
				}
			} else{
				yTarget = MathUtils.random(MainGame.HEIGHT/2, MainGame.HEIGHT - TextureManager.BASIC_ENEMY.getHeight());
				ySpeed = MathUtils.random(1.0f,2.0f);
			}
			pos.add(direction.set(xMovement, yMovement));
		}
		
		if (gameDifficulty == "medium"){
			if (pos.x > xTarget+5 || pos.x < xTarget-5){
				if (pos.x > xTarget) {
				xMovement = -xSpeed;
				} else {
				xMovement = xSpeed;
				}
			} else{
				xTarget = MathUtils.random(0, MainGame.WIDTH-TextureManager.BASIC_ENEMY.getWidth());
				xSpeed = MathUtils.random(1.0f,2.0f);
			}
		// Y movement
			if (pos.y > yTarget+5 || pos.y < yTarget-5){
				if (pos.y > yTarget) {
				yMovement = -ySpeed;
				} else {
				yMovement = ySpeed;
				}
			} else{
				yTarget = MathUtils.random(MainGame.HEIGHT/2, MainGame.HEIGHT - TextureManager.BASIC_ENEMY.getHeight());
				ySpeed = MathUtils.random(0.5f,1.5f);
			}
			pos.add(direction.set(xMovement, yMovement));
		}
		
		if (gameDifficulty == "easy"){
			if (pos.x > xTarget+5 || pos.x < xTarget-5){
				if (pos.x > xTarget) {
					xMovement = -xSpeed;
				} else {
					xMovement = xSpeed;
				}
			} else{
			xTarget = MathUtils.random(0, MainGame.WIDTH-TextureManager.BASIC_ENEMY.getWidth());
			xSpeed = MathUtils.random(0.5f,1.0f);
			}
		// Y movement
			if (pos.y > yTarget+5 || pos.y < yTarget-5){
				if (pos.y > yTarget) {
				yMovement = -ySpeed;
				} else {
				yMovement = ySpeed;
				}
			} else{
				yTarget = MathUtils.random(MainGame.HEIGHT/2, MainGame.HEIGHT - TextureManager.BASIC_ENEMY.getHeight());
				ySpeed = MathUtils.random(0.2f,0.5f);
			}
			pos.add(direction.set(xMovement, yMovement));
		}
		
// detects when the enemy is within bounds and controls shooting
		if (pos.y < MainGame.HEIGHT-TextureManager.BASIC_ENEMY.getHeight()) {
		if (System.currentTimeMillis() - lastFire >= MathUtils.random(timeDelay, timeDelay+1500)) {
			shooting ++;
				if(shooting >= shotDelay+5 && ran == 1) {
					count ++;
					bullet1.spread = 5;
					bullet1.speed = 5;
					entityManager.addEntity(new bullet1(pos.cpy().add(15, 30)));
					entityManager.addEntity(new bullet1(pos.cpy().add(15, 30)));
					entityManager.addEntity(new bullet1(pos.cpy().add(15, 30)));
					entityManager.addEntity(new bullet1(pos.cpy().add(15, 30)));
					entityManager.addEntity(new bullet1(pos.cpy().add(15, 30)));
					SoundManager.shot1.play(0.4f);
					if (count >= firePower/2) {
						count = 0;
						lastFire = System.currentTimeMillis()+1000;
					}
					shooting = 0;
				}
				if(ran == 2) {
					count ++;
					bullet1.spread = 4;
					bullet1.speed = 4;
					if (count >=2){
						entityManager.addEntity(new bullet1(pos.cpy().add(15, 30)));
						SoundManager.shot1.play(0.4f);
					}
					if (count >= firePower*5) {
						count = 0;
						lastFire = System.currentTimeMillis()+2000;
					}
					shooting = 0;
				}
				if(ran ==3) {
					count ++;
					bullet1.spread = 1;
					bullet1.speed = 3;
					entityManager.addEntity(new bullet1(pos.cpy().add(15, 30)));
					SoundManager.shot1.play(0.4f);
					if (count >= firePower*2) {
						count = 0;
						lastFire = System.currentTimeMillis()-1000;
					}
					shooting = 0;
				}
			}
		}
		
//detects if the enemy has fallen down off screen and resets it back up at the top with a random X
		if (pos.y <= -TextureManager.BASIC_ENEMY.getHeight()) {
			float x = MathUtils.random(0, MainGame.WIDTH - TextureManager.BASIC_ENEMY.getWidth());
			pos.set(x, MainGame.HEIGHT);
		}
	}
	
}
