package com.vexoid.game.screen;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.vexoid.game.MainGame;
import com.vexoid.game.SoundManager;
import com.vexoid.game.camera.OrthoCamera;
import com.vexoid.game.entity.EntityManager;
import com.vexoid.game.entity.TimeManager;

public class GameScreen extends Screen{
	private OrthoCamera camera;
	private static EntityManager entityManager;
	private static TimeManager timeManager;
	public static int basicEnemiesCount = 4;
	public String gameDifficulty;
	int distance;
	public static int count = 0;

	String displayDistance;
	String displayScore;
	String displayPlayerHealth;
	String displayPlayerShootingMode;
	String displayPlayerLives;
	String displayPlayerBulletMode;
	String displayDifficulty;
	String displayLevel;
	String displayIntroText;
	String infinity = "";

	BitmapFont displayDistanceFont;
	BitmapFont displayScoreFont;
	BitmapFont displayPlayerHealthFont;
	BitmapFont displayPlayerLivesFont;
	BitmapFont displayPlayerShootingModeFont;
	BitmapFont displayPlayerBulletModeFont;
	BitmapFont displayDifficultyFont;
	BitmapFont displayLevelFont;
	BitmapFont displayIntroTextFont;
	
	public void create(ScreenManager screenManager, String difficulty) {
		gameDifficulty = difficulty;
		camera = new OrthoCamera();
		camera.resize();
		
		entityManager = new EntityManager(camera,screenManager ,gameDifficulty);
		timeManager = new TimeManager(screenManager, entityManager,gameDifficulty);
		
	    displayDistanceFont = new BitmapFont();
	    displayScoreFont = new BitmapFont();
	    displayPlayerHealthFont = new BitmapFont();
	    displayPlayerShootingModeFont = new BitmapFont();
	    displayPlayerLivesFont = new BitmapFont();
	    displayPlayerBulletModeFont = new BitmapFont();
	    displayDifficultyFont = new BitmapFont();
	    displayLevelFont = new BitmapFont();
	    displayIntroTextFont = new BitmapFont();
	}
	
	public void update() {
		camera.update();
		entityManager.update();
		timeManager.update();
		count = timeManager.getCount();
		if(timeManager.getLevel() == 0){
			if(timeManager.getCount() <= 5)
				displayIntroText = "Light Years from home, you fly your ship...";
			if(timeManager.getCount() > 5)
				displayIntroText = "Get ready to fight...";
		}
		if(timeManager.getLevel() > 0){
			entityManager.listenForKeys();
		}
		if(timeManager.getLevel() == 9)
			infinity = "Infinity";
		else
			infinity = "" + timeManager.getLevel();
		
		displayScore = "Score : " + EntityManager.enemyKillScore();
		displayPlayerHealth = "Health : " + (int) EntityManager.checkPlayerHealth() + "%";
		displayPlayerShootingMode = "Spread : " + EntityManager.getPlayerShootingMode();
		displayPlayerLives = "Lives : " + EntityManager.getPlayerLives();
		displayPlayerBulletMode = "Mode : " + EntityManager.getPlayerBulletMode();
		displayDifficulty = "Difficulty : " + gameDifficulty;
		displayDistance = "Distance : " + timeManager.getDistance() + " Km";
		displayLevel = "Level : " + infinity;
		
		if(entityManager.isGameOver()){
			SoundManager.setMusic(SoundManager.endMusic, 0.8f, true);
		}
	}

	public void render(SpriteBatch sb) {
		sb.setProjectionMatrix(camera.combined);
		sb.begin();
		entityManager.render(sb);
	if(timeManager.getLevel() == 0){
		displayIntroTextFont.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		displayIntroTextFont.draw(sb, displayIntroText, (MainGame.WIDTH/2)-100, (MainGame.HEIGHT/2));
	}
	if(timeManager.getLevel() > 0){
		displayDistanceFont.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		displayDistanceFont.draw(sb, displayDistance, 25, (MainGame.HEIGHT)-20);
		
		displayScoreFont.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		displayScoreFont.draw(sb, displayScore, (MainGame.WIDTH/2)-50, (MainGame.HEIGHT)-20);
		
		displayPlayerHealthFont.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		displayPlayerHealthFont.draw(sb, displayPlayerHealth, 25, 35);
		
		displayPlayerShootingModeFont.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		displayPlayerShootingModeFont.draw(sb, displayPlayerShootingMode, (MainGame.WIDTH)-125, 35);
		
		displayPlayerBulletModeFont.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		displayPlayerBulletModeFont.draw(sb, displayPlayerBulletMode, (MainGame.WIDTH)-125, 50);
		
		displayPlayerLivesFont.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		displayPlayerLivesFont.draw(sb, displayPlayerLives, 25, 50);
		
		displayDifficultyFont.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		displayDifficultyFont.draw(sb, displayDifficulty, (MainGame.WIDTH)-125, (MainGame.HEIGHT)-20);
		
		displayLevelFont.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		displayLevelFont.draw(sb, displayLevel, (MainGame.WIDTH)-225, (MainGame.HEIGHT)-20);
	}
		sb.end();
	}
	public static boolean isGameOver(){
		return entityManager.isGameOver();
	}
	public static int getEnemies(){
		return entityManager.getEntities();
	}
	public static void addEnemies(int i){
		if(i == 1)
		entityManager.addBasicEnemy();
		if(i == 2)
		entityManager.addAdvancedEnemy();
		if(i == 3)
		entityManager.addBasicLaserEnemy();	
	}
	public static void addBoss(int i){
		if(i == 1)
		entityManager.addBoss1();
	}
	public static int getDistance(){
		return timeManager.getDistance();
	}
	public static int getScore(){
		return entityManager.getScore();
	}
	
	public void resize(int width, int height) {
		camera.resize();
	}

	public void dispose() {
		
	}

	public void pause() {
		
	}

	public void resume() {
		
	}
	public String whatScreen() {
		return "GameScreen";
	}
}
