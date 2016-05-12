package com.vexoid.game.entity.bullets;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.vexoid.game.TextureManager;
import com.vexoid.game.entity.Entity;

public class bullet1 extends Entity {
	public bullet1(Vector2 pos, float spread, float speed) {
		super(TextureManager.BULLET1, pos, new Vector2(MathUtils.random(-spread,spread), -speed));
	}
	
	private int time=0, ran = MathUtils.random(1,4);
	
	public void update() {
		pos.add(direction);
		time ++;
		if(time >= 20){
			if(ran == 0){
				this.texture = TextureManager.BULLET1;
				ran = MathUtils.random(0,4);
			}
			if(ran == 1){
				this.texture = TextureManager.BULLET1_1;
				ran = MathUtils.random(0,4);
			}
			if(ran == 2){
				this.texture = TextureManager.BULLET1_2;
				ran = MathUtils.random(0,4);
			}
			if(ran == 3){
				this.texture = TextureManager.BULLET1_3;
				ran = MathUtils.random(0,4);
			}
			if(ran == 4){
				this.texture = TextureManager.BULLET1_4;
				ran = MathUtils.random(0,4);
			}
		}
	}
	public void render(SpriteBatch sb){
		sb.draw(texture, pos.x, pos.y);
		
	}
	public boolean checkEnd() {
		return pos.y <= 0 -this.texture.getHeight();
	}
}
