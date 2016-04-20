package com.vexoid.game.entity.bullets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.vexoid.game.TextureManager;
import com.vexoid.game.entity.Entity;

public class LaserBullet1 extends Entity{

	int internalClock = 0;
	boolean timeUp = false;
	static Texture texture;
	
	public LaserBullet1(Vector2 pos) {
		super(getTexture(), pos, new Vector2(0, 0));
	}
	public static Texture getTexture(){
		int text = MathUtils.random(1,5);
		if(text==1)
			return TextureManager.LASER_BULLET;
		else
		if(text==2)
			return TextureManager.LASER_BULLET1;
		else
		if(text==3)
			return TextureManager.LASER_BULLET2;
		else
		if(text==4)
			return TextureManager.LASER_BULLET3;
		else
		if(text==5)
			return TextureManager.LASER_BULLET4;
		else
			return TextureManager.LASER_BULLET;		
	}
	public void update() {
		internalClock ++;
		if (internalClock >=5)
			timeUp = true;
	}
	public boolean destroyTime() {
		return timeUp;
	}
	public Rectangle getBounds(){
		return new Rectangle(pos.x, pos.y, getTexture().getWidth()/1.25f, getTexture().getHeight()/1.25f);
	}
}
