package com.vexoid.game.entity.bullets;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.vexoid.game.MainGame;
import com.vexoid.game.TextureManager;
import com.vexoid.game.entity.Entity;
import com.vexoid.game.entity.Player;

public class Red_Bullet2 extends Entity {

	private Player player;
	private boolean lockMovement = false;
	
	private float xMovement, yMovement;
	private float xTarget, yTarget;
	private int state = 0;
	private float xSpeed = 5.5f;
	private float ySpeed = 5.5f;
	private int point, clock = 0;
	
	public Red_Bullet2(Vector2 pos, Vector2 direction, Player player) {
		super(TextureManager.RED_BULLET2, pos, direction);
		this.player = player;
		if((player.getPosition().x + player.getTexture().getWidth()/2) < pos.x){
			point = 2;
			xTarget = player.getPointOfShooting(0).x + MathUtils.random(75, 100);
		}else{
			point = 1;
			xTarget = player.getPointOfShooting(0).x + MathUtils.random(-100, -75);
		}
		yTarget = player.getPointOfShooting(0).y + MathUtils.random(50, 100);
	}
	
	public void update() {
		if(player.getFireRedBullet())
			lockMovement = true;
		if(lockMovement){
			clock ++;
			if(clock < 10)
				if(point == 2)
					pos.add(MathUtils.random(-3.5f, -1.5f), MathUtils.random(0.5f,1));
				else
					pos.add(MathUtils.random(1.5f, 3.5f), MathUtils.random(0.5f,1));
			else
				if(point == 2)
					pos.add((direction.x*3) - 5, direction.y += 2);
				else
					pos.add((direction.x*3) + 5, direction.y += 2);
		} else {
			if(state == 0){
			if (pos.x > xTarget+3 || pos.x < xTarget-3){
				if (pos.x > xTarget) {
				xMovement = -xSpeed;
				} else {
				xMovement = xSpeed;
				}
			} else{
				xMovement = 0;
				state = 1;
			}
		// Y movement
			if (pos.y > yTarget+3 || pos.y < yTarget-3){
				if (pos.y > yTarget) {
				yMovement = -ySpeed;
				} else {
				yMovement = ySpeed;
				}
			} else{
				yMovement = 0;
				state = 1;
			}
			pos.add(new Vector2(xMovement, yMovement));
			} else 
			if(state == 1){
				if(point == 2)
					pos.add(new Vector2(MathUtils.random(1.5f, 2.5f), MathUtils.random(-1.5f, -0.5f)));
				else
					pos.add(new Vector2(MathUtils.random(-2.5f, -1.5f), MathUtils.random(-1.5f, -0.5f)));
			}
		}
	}
	
	public boolean checkEnd() {
		return pos.y >= MainGame.HEIGHT;
	}
	
}
