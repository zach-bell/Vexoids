package com.vexoid.game.entity.effects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.vexoid.game.TextureManager;
import com.vexoid.game.entity.Entity;

public class Effect3_LaserWarning extends Entity{

	int internalClock = 0;
	boolean timeUp = false;
	public Effect3_LaserWarning(Vector2 pos, Vector2 direction) {
		super(getTexture(), pos, direction);
	}

	private static Texture getTexture() {
		int text = MathUtils.random(1,2);
		if(text==1)
			return TextureManager.LASER_WARNING1;
		else
		if(text==2)
			return TextureManager.LASER_WARNING2;
		else
			return TextureManager.LASER_WARNING1;
	}

	public void update() {
		internalClock ++;
		if (internalClock >=5)
			timeUp = true;
	}
	public boolean destroyTime() {
		return timeUp;
	}

}
