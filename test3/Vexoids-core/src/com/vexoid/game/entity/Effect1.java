package com.vexoid.game.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.vexoid.game.TextureManager;

public class Effect1 extends Entity{
	int internalClock = 0;
	boolean timeUp = false;
	
	public Effect1(Vector2 pos, Vector2 direction) {
		super(getTexture(), pos, direction);
	}
	
	float xMovement;
	float yMovement;
	float xSpeed = 0.9f;
	float ySpeed = 0.9f;
	
	private int xTarget = (int) direction.x;
	private int yTarget = (int) direction.y;
	
	public static Texture getTexture(){
		int text = MathUtils.random(1,5);
		if(text==1)
			return TextureManager.RED_EFFECT1;
		else
		if(text==2)
			return TextureManager.RED_EFFECT2;
		else
		if(text==3)
			return TextureManager.RED_EFFECT3;
		else
		if(text==4)
			return TextureManager.RED_EFFECT4;
		else
		if(text==5)
			return TextureManager.RED_EFFECT5;
		else
			return TextureManager.RED_EFFECT1;
	}
	
	public void update() {
		if (pos.x > xTarget-2 || pos.x < xTarget+2){
			if (pos.x > xTarget) {
			xMovement = -xSpeed;
			} else {
			xMovement = xSpeed;
			}
		} 
	// Y movement
		if (pos.y > yTarget-2 || pos.y < yTarget+2){
			if (pos.y > yTarget) {
			yMovement = -ySpeed;
			} else {
			yMovement = ySpeed;
			}
		}
		pos.add(direction.set(xMovement, yMovement));
		
		internalClock ++;
		if (internalClock >=25)
			timeUp = true;
	}
	public boolean destroyTime() {
		return timeUp;
	}
}
