package com.vexoid.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
	String gameDifficulty, Start = "Press Enter";
	String changeGameDifficulty = "Use Left or Right arrow Key\n to change difficulty";
	
	BitmapFont displayGameDifficultyFont;
	BitmapFont displayChangeGameDifficultyFont;
	BitmapFont displayStartFont;
	
	Texture title = TextureManager.TITLE_IMAGE;
	
	public void create(ScreenManager screenManager, String difficulty) {
		gameDifficulty = difficulty;
		camera = new OrthoCamera();
		camera.resize();
		
		SoundManager.setMusic(SoundManager.menuMusic, 0.8f, true);
	
		displayGameDifficultyFont = new BitmapFont();
		displayChangeGameDifficultyFont = new BitmapFont();
		displayStartFont = new BitmapFont();
	}

	int secondIncrease = 30;
	int starcount = 0;

	static boolean clearedEntities = false;
	private int limit=101,starLimit=limit,starToggle=0;
	private final Array<Stars_Class> stars = new Array<Stars_Class>();
	
	private int[] oneTime = {0};
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
//	Difficulty Switch
		if ((Gdx.input.isKeyPressed(Keys.RIGHT)&& Switch2 != 1 && Toggle2 == 1) ||
				(Gdx.input.isKeyPressed(Keys.LEFT)&& Switch2 != 1 && Toggle2 == 3)){
			gameDifficulty = "hard";
			SoundManager.sound1.play(1);
			if(Gdx.input.isKeyPressed(Keys.LEFT))
				Toggle2 = 2;
			else
			Toggle2 = 2;
		} else
		if ((Gdx.input.isKeyPressed(Keys.RIGHT)&& Switch2 != 1 && Toggle2 == 2) ||
				(Gdx.input.isKeyPressed(Keys.LEFT)&& Switch2 != 1 && Toggle2 == 1)){
			gameDifficulty = "easy";
			SoundManager.sound1.play(1);
			if(Gdx.input.isKeyPressed(Keys.LEFT))
				Toggle2 = 3;
			else
			Toggle2 = 3;
		} else
		if ((Gdx.input.isKeyPressed(Keys.RIGHT)&& Switch2 != 1 && Toggle2 == 3) ||
				(Gdx.input.isKeyPressed(Keys.LEFT)&& Switch2 != 1 && Toggle2 == 2)){
			gameDifficulty = "medium";
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

	public void render(SpriteBatch sb) {
		sb.setProjectionMatrix(camera.combined);
		sb.begin();
		for (Stars_Class s : stars)
			s.render(sb);
		sb.draw(title, ((MainGame.WIDTH/2) - (TextureManager.TITLE_IMAGE.getWidth()/2)), (MainGame.HEIGHT / 2));

		displayStartFont.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		displayStartFont.draw(sb, Start, (MainGame.WIDTH / 2) / 1.2f, ((MainGame.HEIGHT / 2)));
		
		displayChangeGameDifficultyFont.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		displayChangeGameDifficultyFont.draw(sb, changeGameDifficulty, (MainGame.WIDTH / 2) / 1.35f, ((MainGame.HEIGHT / 2)/1.1f));

		displayGameDifficultyFont.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		displayGameDifficultyFont.draw(sb, gameDifficulty, (MainGame.WIDTH / 2) / 1.15f, ((MainGame.HEIGHT / 2)/1.3f));
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
