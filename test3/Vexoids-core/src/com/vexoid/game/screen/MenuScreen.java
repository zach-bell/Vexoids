package com.vexoid.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.vexoid.game.MainGame;
import com.vexoid.game.SoundManager;
import com.vexoid.game.TextureManager;
import com.vexoid.game.camera.OrthoCamera;
import com.vexoid.game.entity.TimeManager;
import com.vexoid.game.entity.stars.Stars_Class;

public class MenuScreen extends Screen{

	private OrthoCamera camera;
	String gameDifficulty;
	
	Texture title = TextureManager.TITLE_IMAGE;
	Texture menuTitle = TextureManager.MENU_IMAGE;
	Texture difficultyImage = TextureManager.DIFFICULTY_MEDIUM;
	
	public void create(ScreenManager screenManager, String difficulty) {
		gameDifficulty = difficulty;
		camera = new OrthoCamera();
		camera.resize();
		
		SoundManager.setMusic(SoundManager.menuMusic, 0.8f, true);
	}

	int secondIncrease = 30;
	int starcount = 0;

	static boolean clearedEntities = false;
	private int limit=104,starLimit=limit,starToggle=0;
	private final Array<Stars_Class> stars = new Array<Stars_Class>();
	
	private boolean cheatActive = false;
	private int[] oneTime = {0,0};
	int Switch = 0, Toggle = 1, Switch2 = 0, Toggle2 = 1;
	
	private void addStars(Stars_Class entity) {
		stars.add(entity);
	}
	public void update() {
		camera.update();
		MainGame.setDifficulty(gameDifficulty);
		starcount ++;
		if(starcount == 4) {		//	Controls spawing ammount
			if(starToggle>=1){
				starLimit=99;
				starToggle --;
			} else					//	This controls aditional planets and such
				starLimit=limit;
			int text = MathUtils.random(0,starLimit);
			if(text>=100)
				starToggle=100;						//	This sets the wait for a planet to spawn
			
	//	Sets the position of the stars and speed
			float y = MathUtils.random(0, MainGame.HEIGHT - TextureManager.STAR1.getHeight());
			float x = -(MainGame.WIDTH / 2) - TextureManager.STAR1.getWidth();
			float speedx = MathUtils.random(2.5f,4.5f);
			if(text>=100)
				speedx -= 2;
			addStars(new Stars_Class(new Vector2(x,y), new Vector2(speedx, 0), text));
			starcount = 0;
		}
		for (Stars_Class s : stars)
			s.update();
		
// hidden switches
		if(Gdx.input.isKeyPressed(Keys.NUM_1)){
			if(Gdx.input.isKeyPressed(Keys.NUM_2)){
				if(Gdx.input.isKeyPressed(Keys.NUM_3)){
					if(Gdx.input.isKeyPressed(Keys.NUM_4) && oneTime[0] == 0){
						TimeManager.level = 1;
						TimeManager.step = 9;
						SoundManager.sound2.play(0.7f);
						oneTime[0] = 1;
					}
				}
			}
		}
		if(Gdx.input.isKeyPressed(Keys.NUM_1)){
			if(Gdx.input.isKeyPressed(Keys.NUM_2)){
				if(Gdx.input.isKeyPressed(Keys.NUM_4)){
					if(Gdx.input.isKeyPressed(Keys.NUM_5) && oneTime[1] == 0){
						SoundManager.sound2.play(0.7f);
						cheatActive = true;
						oneTime[1] = 1;
					}
				}
			}
		}
//	Difficulty Switch
		if(cheatActive){

//			Difficulty Switch
			if ((Gdx.input.isKeyPressed(Keys.RIGHT)&& Switch2 != 1 && Toggle2 == 4) ||
					(Gdx.input.isKeyPressed(Keys.LEFT)&& Switch2 != 1 && Toggle2 == 3)){
				gameDifficulty = "vexoid";
				difficultyImage = TextureManager.DIFFICULTY_VEXOID;
				SoundManager.sound1.play(1);
				if(Gdx.input.isKeyPressed(Keys.LEFT))
					Toggle2 = 3;
				else
					Toggle2 = 2;
			} else
			if ((Gdx.input.isKeyPressed(Keys.RIGHT)&& Switch2 != 1 && Toggle2 == 1) ||
					(Gdx.input.isKeyPressed(Keys.LEFT)&& Switch2 != 1 && Toggle2 == 3)){
				gameDifficulty = "hard";
				difficultyImage = TextureManager.DIFFICULTY_HARD;
				SoundManager.sound1.play(1);
				if(Gdx.input.isKeyPressed(Keys.LEFT))
					Toggle2 = 3;
				else
					Toggle2 = 4;
			} else
			if ((Gdx.input.isKeyPressed(Keys.RIGHT)&& Switch2 != 1 && Toggle2 == 2) ||
					(Gdx.input.isKeyPressed(Keys.LEFT)&& Switch2 != 1 && Toggle2 == 4)){
				gameDifficulty = "easy";
				difficultyImage = TextureManager.DIFFICULTY_EASY;
				SoundManager.sound1.play(1);
				if(Gdx.input.isKeyPressed(Keys.LEFT))
					Toggle2 = 4;
				else
					Toggle2 = 3;
			} else
			if ((Gdx.input.isKeyPressed(Keys.RIGHT)&& Switch2 != 1 && Toggle2 == 3) ||
					(Gdx.input.isKeyPressed(Keys.LEFT)&& Switch2 != 1 && Toggle2 == 2)){
				gameDifficulty = "medium";
				difficultyImage = TextureManager.DIFFICULTY_MEDIUM;
				SoundManager.sound1.play(1);
				if(Gdx.input.isKeyPressed(Keys.LEFT))
					Toggle2 = 4;
				else
					Toggle2 = 1;
			}			
			if (Gdx.input.isKeyPressed(Keys.RIGHT) || Gdx.input.isKeyPressed(Keys.LEFT))
				Switch2 = 1;
			else
				Switch2 = 0;
			
		} else {	//	This is the else for checking if the cheat active or not
			
		// without cheat
		if ((Gdx.input.isKeyPressed(Keys.RIGHT)&& Switch2 != 1 && Toggle2 == 1) ||
				(Gdx.input.isKeyPressed(Keys.LEFT)&& Switch2 != 1 && Toggle2 == 3)){
			gameDifficulty = "hard";
			difficultyImage = TextureManager.DIFFICULTY_HARD;
			SoundManager.sound1.play(1);
			if(Gdx.input.isKeyPressed(Keys.LEFT))
				Toggle2 = 2;
			else
				Toggle2 = 2;
		} else
		if ((Gdx.input.isKeyPressed(Keys.RIGHT)&& Switch2 != 1 && Toggle2 == 2) ||
				(Gdx.input.isKeyPressed(Keys.LEFT)&& Switch2 != 1 && Toggle2 == 1)){
			gameDifficulty = "easy";
			difficultyImage = TextureManager.DIFFICULTY_EASY;
			SoundManager.sound1.play(1);
			if(Gdx.input.isKeyPressed(Keys.LEFT))
				Toggle2 = 3;
			else
				Toggle2 = 3;
		} else
		if ((Gdx.input.isKeyPressed(Keys.RIGHT)&& Switch2 != 1 && Toggle2 == 3) ||
				(Gdx.input.isKeyPressed(Keys.LEFT)&& Switch2 != 1 && Toggle2 == 2)){
			gameDifficulty = "medium";
			difficultyImage = TextureManager.DIFFICULTY_MEDIUM;
			SoundManager.sound1.play(1);
			if(Gdx.input.isKeyPressed(Keys.LEFT))
				Toggle2 = 1;
			else
				Toggle2 = 1;
		}			
		if (Gdx.input.isKeyPressed(Keys.RIGHT) || Gdx.input.isKeyPressed(Keys.LEFT))
			Switch2 = 1;
		else
			Switch2 = 0;
		}
	}

	public void render(SpriteBatch sb) {
		sb.setProjectionMatrix(camera.combined);
		sb.begin();
		for (Stars_Class s : stars)
			s.render(sb);
		sb.draw(title, ((MainGame.WIDTH/2) - (TextureManager.TITLE_IMAGE.getWidth()/2)), (MainGame.HEIGHT / 2));
		sb.draw(menuTitle, ((MainGame.WIDTH/2) - (TextureManager.MENU_IMAGE.getWidth()/2)),
				(MainGame.HEIGHT / 2) - (TextureManager.TITLE_IMAGE.getHeight()/2));
		sb.draw(difficultyImage, ((MainGame.WIDTH/2) - (TextureManager.DIFFICULTY_MEDIUM.getWidth()/2)),
				(MainGame.HEIGHT / 2) - (TextureManager.MENU_IMAGE.getHeight() - 7));
		sb.end();
	}

	public void resize(int width, int height) {
		camera.resize();
	}

	public void dispose() {}

	public void pause() {}
	
	public void resume() {}

	public String whatScreen() {
		return "MenuScreen";
	}

}
