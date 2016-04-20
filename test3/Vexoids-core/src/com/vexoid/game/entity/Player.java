package com.vexoid.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.vexoid.game.MainGame;
import com.vexoid.game.SoundManager;
import com.vexoid.game.TextureManager;
import com.vexoid.game.camera.OrthoCamera;
import com.vexoid.game.entity.bullets.Blue_Bullet2;
import com.vexoid.game.entity.bullets.Red_Bullet2;
import com.vexoid.game.entity.bullets.Yellow_Bullet2;

public class Player extends Entity{

	private final EntityManager entityManager;
	private long lastFire;
	private final OrthoCamera camera;
	public static float PositionX;
	private int shootDelay = 20;
	private float spread = 0.5f;
	static String shootingMode = "narrow", bulletMode = "light";
	String difficulty;
		
	public Player(Vector2 pos, Vector2 direction, EntityManager entityManager, OrthoCamera camera) {
		super(TextureManager.PLAYER, pos, direction);
		this.entityManager = entityManager;
		this.camera = camera;
		PositionX = pos.x;
	}
	
// Health stuff
	float healthPercent = 100;
	int clock = 0;
	boolean playerDied = false, playerOutOfLives = false;
	public void health(){
		clock ++;
		if(healthPercent >= 100.000)
			healthPercent=100.0f;
		if(clock > 750){
			for(int i=(int) healthPercent; i< 100; i++){
				healthPercent += 0.01f;
			}
			if(healthPercent >= 100)
				clock=0;
		}
	}
	public void increaseHealth(float ammount){
		healthPercent += ammount;
		if(healthPercent >= 100.0)
			healthPercent=100;
	}
	public void decreaseHealth(float ammount){
		clock=0;
		healthPercent -= ammount;
		if(healthPercent <= 0)
			playerDied = true;
	}
	public float getHealth(){
		return healthPercent;
	}
	
	boolean tookLives = false;
	public void liveSystem(){
		if (playerDied && !tookLives){
			tookLives = true;
			healthPercent = 100.0f;
			EntityManager.lives --;
			playerDied = false;
		}
	}
	public boolean playerOutOfLives(){
		return playerOutOfLives;
	}

//	Player hitbox
	public Rectangle getBounds() {
		return new Rectangle((pos.x + ((texture.getWidth()/2)/1.25f)), (pos.y + ((texture.getHeight()/2)/1.25f)),
				(texture.getWidth()- (texture.getWidth()/1.25f)), (texture.getHeight()-(texture.getHeight()/1.25f)));
	}

	public String shootingMode(){
		return shootingMode;
	}
	public String bulletMode(){
		return bulletMode;
	}
	int Switch = 0, Toggle = 1, Switch2 = 0, Toggle2 = 1;
	public void update() {

	if (canPlayerMove){
		pos.add(direction);
		PositionX = pos.x;
		health();
		liveSystem();
		int dir = 0;
		if (Gdx.input.isTouched()) {
			Vector2 touch = camera.unprojectCoordinates(Gdx.input.getX(), Gdx.input.getY());
			if (touch.x < MainGame.WIDTH / 2 && pos.x > 0)
				dir = 1;
			else
				if(pos.x < (MainGame.WIDTH - TextureManager.PLAYER.getWidth()))
				dir = 2;
		}
		
/* * * * * * * *
 * Here is the *
 * controls of *
 * the player  *
 * * * * * * * */
		if ((Gdx.input.isKeyPressed(Keys.A)&&!Gdx.input.isKeyPressed(Keys.D))&&(pos.x > 0)||(dir == 1)||
				((Gdx.input.isKeyPressed(Keys.LEFT)&&!Gdx.input.isKeyPressed(Keys.RIGHT))&&(pos.x > 0))) {
				setDirection(-400, 0);
		} else {
			if ((Gdx.input.isKeyPressed(Keys.D)&&!Gdx.input.isKeyPressed(Keys.A))
					&&(pos.x < MainGame.WIDTH - this.texture.getWidth())||(dir ==2)
					||((Gdx.input.isKeyPressed(Keys.RIGHT)&&!Gdx.input.isKeyPressed(Keys.LEFT))
							&&(pos.x < MainGame.WIDTH - this.texture.getWidth()))) {
			setDirection(400, 0);
			} else {
				if ((Gdx.input.isKeyPressed(Keys.W)&&!Gdx.input.isKeyPressed(Keys.S))
						&&(pos.y < MainGame.HEIGHT - this.texture.getHeight())
						|| (Gdx.input.isKeyPressed(Keys.UP)&&!Gdx.input.isKeyPressed(Keys.DOWN))
						&&(pos.y < MainGame.HEIGHT - this.texture.getHeight())) {
					setDirection(0, 400);
				} else {
					if ((Gdx.input.isKeyPressed(Keys.S)&&!Gdx.input.isKeyPressed(Keys.W))
							&&(pos.y > 0)|| (Gdx.input.isKeyPressed(Keys.DOWN)&&!Gdx.input.isKeyPressed(Keys.UP))&&(pos.y > 0)) {
						setDirection(0, -400);
					} else {
						setDirection(0,0);
						}
					}
				}
			}
		
	//	Shooting modes
		if (Gdx.input.isKeyPressed(Keys.B)&& Switch != 1 && Toggle == 1){
			shootingMode = "Wide";
			spread = 3.5f;
			SoundManager.sound1.play(1);
			Toggle = 2;
		} else
		if (Gdx.input.isKeyPressed(Keys.B)&& Switch != 1 && Toggle == 2){
			shootingMode = "Narrow";
			spread = 0.5f;
			SoundManager.sound1.play(1);
			Toggle = 1;
		}
		if (Gdx.input.isKeyPressed(Keys.B))
			Switch = 1;
		else
			Switch = 0;
		
//	Switch Bullets
		if (Gdx.input.isKeyPressed(Keys.F)&& Switch2 != 1 && Toggle2 == 1){
			bulletMode = "energy";
			shootDelay = 10;
			SoundManager.sound1.play(1);
			Toggle2 = 2;
		} else
		if (Gdx.input.isKeyPressed(Keys.F)&& Switch2 != 1 && Toggle2 == 2){
			bulletMode = "heavy";
			shootDelay = 50;
			SoundManager.sound1.play(1);
			Toggle2 = 3;
		} else
		if (Gdx.input.isKeyPressed(Keys.F)&& Switch2 != 1 && Toggle2 == 3){
			bulletMode = "light";
			shootDelay = 17;
			SoundManager.sound1.play(1);
			Toggle2 = 1;
		}			
		if (Gdx.input.isKeyPressed(Keys.F))
			Switch2 = 1;
		else
			Switch2 = 0;
		
//	Shoot bullets
		Vector2 point1 = new Vector2(pos.cpy().add(-14, TextureManager.PLAYER.getHeight()/2));
		Vector2 point2 = new Vector2(pos.cpy().add(TextureManager.PLAYER.getWidth()-17,TextureManager.PLAYER.getHeight()/2));
		float var = MathUtils.random(2,6);
		float var2 = 3;
		
		if (Gdx.input.isKeyPressed(Keys.SPACE) || dir ==1 || dir == 2){
			if(bulletMode == "light")
				if (System.currentTimeMillis() - lastFire >= shootDelay) {
					int r = MathUtils.random(0,1);
					if(r==0)
						entityManager.addEntity(new Blue_Bullet2(point1, new Vector2(MathUtils.random(-spread, spread+var2), 18)));
					if(r==1)
						entityManager.addEntity(new Blue_Bullet2(point2, new Vector2(MathUtils.random(-spread-var2, spread), 18)));
				
					SoundManager.shot2.play(0.2f);
					lastFire = System.currentTimeMillis();
				}
			if(bulletMode == "heavy")
				if (System.currentTimeMillis() - lastFire >= shootDelay) {
					int r = MathUtils.random(0,1);
					if(r==0)
						entityManager.addEntity(new Red_Bullet2(point1, new Vector2(MathUtils.random(-spread, spread+var2), 18)));
					if(r==1)
						entityManager.addEntity(new Red_Bullet2(point2, new Vector2(MathUtils.random(-spread-var2, spread), 18)));
					
					SoundManager.shot2.play(0.2f);
					lastFire = System.currentTimeMillis();
				}
			if(bulletMode == "energy")
				if (System.currentTimeMillis() - lastFire >= shootDelay) {
					int r = MathUtils.random(0,1);
					if(r==0)
						entityManager.addEntity(new Yellow_Bullet2(point1, new Vector2(MathUtils.random(-spread-var, spread), 10)));
					if(r==1)
						entityManager.addEntity(new Yellow_Bullet2(point2, new Vector2(MathUtils.random(-spread, spread+var), 10)));
					SoundManager.shot3.play(0.1f);
					lastFire = System.currentTimeMillis();
				}
		}
	}
}
	boolean canPlayerMove = true;
	public void playerCanMove(boolean moving) {
		canPlayerMove = moving;
	}
}
