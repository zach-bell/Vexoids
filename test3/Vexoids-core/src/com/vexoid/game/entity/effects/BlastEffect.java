package com.vexoid.game.entity.effects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.vexoid.game.TextureManager;
import com.vexoid.game.entity.Entity;

public class BlastEffect extends Entity{
	private int internalClock = 0;
	private boolean timeUp = false;
	private float speed;
	static String Color;
	public BlastEffect(Vector2 pos, Vector2 direction, float Speed, String color) {
		super(getTexture(), pos, direction);
		speed = Speed;
		Color = color;
		xMovement = MathUtils.random(-speed, speed);
		yMovement = MathUtils.random(-speed, speed);
	}
	
	public static Texture getTexture(){
		int text = MathUtils.random(1,5);
		if(Color == "red"){
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
		} else
		if(Color == "blue"){
			if(text==1)
				return TextureManager.BLUE_EFFECT1;
			else
			if(text==2)
				return TextureManager.BLUE_EFFECT2;
			else
			if(text==3)
				return TextureManager.BLUE_EFFECT3;
			else
			if(text==4)
				return TextureManager.BLUE_EFFECT4;
			else
			if(text==5)
				return TextureManager.BLUE_EFFECT5;
			else
				return TextureManager.BLUE_EFFECT1;
		} else
			if(Color == "yellow"){
				if(text==1)
					return TextureManager.YELLOW_EFFECT1;
				else
				if(text==2)
					return TextureManager.YELLOW_EFFECT2;
				else
				if(text==3)
					return TextureManager.YELLOW_EFFECT3;
				else
				if(text==4)
					return TextureManager.YELLOW_EFFECT4;
				else
				if(text==5)
					return TextureManager.YELLOW_EFFECT5;
				else
					return TextureManager.YELLOW_EFFECT1;
			} else
			return TextureManager.RED_EFFECT1;
	}
	private float xMovement = MathUtils.random(-speed, speed), yMovement = MathUtils.random(-speed, speed);
	public void update() {
		pos.add(direction.set(xMovement, yMovement));
		internalClock ++;
		if (internalClock >=17)
			timeUp = true;
	}
	public boolean destroyTime() {
		return timeUp;
	}
}
