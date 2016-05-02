package com.vexoid.game.entity.stars;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.vexoid.game.MainGame;
import com.vexoid.game.TextureManager;

public class Stars_Class{
	int internalClock = 0;
	boolean timeUp = false;
	protected Texture texture;
	protected Vector2 pos;
	protected Vector2 direction;
	
	public Stars_Class(Vector2 pos, Vector2 direction, int text) {
		this.texture = getTexture(text);
		this.direction = direction;
		this.pos = pos;
	}
	
	public static Texture getTexture(int text){
		int planet = MathUtils.random(1,4);
		if(text<=10)
			return TextureManager.STAR1;
		else
		if(text<=20 && text>10)
			return TextureManager.STAR2;
		else
		if(text<=30 && text>20)
			return TextureManager.STAR3;
		else
		if(text<=40 && text>30)
			return TextureManager.STAR4;
		else
		if(text<=50 && text>40)
			return TextureManager.STAR5;
		else
		if(text<=60 && text>50)
			return TextureManager.STAR6;
		else
		if(text<=70 && text>60)
			return TextureManager.STAR7;
		else
		if(text<=80 && text>70)
			return TextureManager.STAR8;
		else
		if(text<=90 && text>80)
			return TextureManager.STAR9;
		else
		if(text<=100 && text>90)
			return TextureManager.STAR10;
		else
					
		if(text==101){
			if(planet>=3){
				return TextureManager.PLANET1;
			}else{return TextureManager.PLANET1_1;}
		}else
		if(text==102){
			if(planet>=3){
				return TextureManager.PLANET2;
			}else{return TextureManager.PLANET2_1;}
		}else
		if(text==103){
			if(planet>=3){
				return TextureManager.PLANET3;
			}else{return TextureManager.PLANET3_1;}
		}else
		if(text==104){
			if(planet>=3){
				return TextureManager.PLANET4;
			}else{return TextureManager.PLANET4_1;}
		}else
			return TextureManager.STARS;
	}
	public void update() {
		pos.add(direction);
	}
	public void render(SpriteBatch sb) {
		sb.draw(texture, pos.x, pos.y);
	}
	public void setDirection(float x, float y) {
		direction.set(x, y);
		direction.scl(Gdx.graphics.getDeltaTime());
	}
	public boolean checkEnd() {
		return (pos.y <= 0-texture.getHeight() || pos.x > MainGame.WIDTH);
	}
}
