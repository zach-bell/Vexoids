package com.vexoid.game.entity.bullets;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.vexoid.game.MainGame;
import com.vexoid.game.TextureManager;
import com.vexoid.game.entity.Entity;

public class Yellow_Bullet2 extends Entity{

	public Yellow_Bullet2(Vector2 pos, Vector2 direction) {
		super(TextureManager.YELLOW_BULLET2, pos, direction);
	}
	
	private int count = 0;
	public void update() {
		count++;
		if(count < 15)
			pos.add(direction.x, direction.y - MathUtils.random(5,9));
		if(count > 15)
			pos.add(-(direction.x), direction.y += 0.2);
	}
	
	public boolean checkEnd() {
		return pos.y >= MainGame.HEIGHT;
	}
}
