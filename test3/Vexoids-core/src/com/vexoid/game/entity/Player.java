package com.vexoid.game.entity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.vexoid.game.MainGame;
import com.vexoid.game.SoundManager;
import com.vexoid.game.TextureManager;
import com.vexoid.game.camera.OrthoCamera;
import com.vexoid.game.entity.bullets.Blue_Bullet2;
import com.vexoid.game.entity.bullets.Red_Bullet2;
import com.vexoid.game.entity.bullets.Yellow_Bullet2;

public class Player extends Entity{
	//controls
	private int upArrow = com.badlogic.gdx.Input.Keys.UP;
	private int leftArrow = com.badlogic.gdx.Input.Keys.LEFT;
	private int downArrow = com.badlogic.gdx.Input.Keys.DOWN;
	private int rightArrow = com.badlogic.gdx.Input.Keys.RIGHT;
	private int up = com.badlogic.gdx.Input.Keys.W;
	private int left = com.badlogic.gdx.Input.Keys.A;
	private int down = com.badlogic.gdx.Input.Keys.S;
	private int right = com.badlogic.gdx.Input.Keys.D;
	private int fire = com.badlogic.gdx.Input.Keys.SPACE;
	private int changeSpread = com.badlogic.gdx.Input.Keys.B;
	private int changeMode = com.badlogic.gdx.Input.Keys.F;
	private int shitf = com.badlogic.gdx.Input.Keys.SHIFT_LEFT;
	//url to int codes https://libgdx.badlogicgames.com/nightlies/docs/api/constant-values.html#com.badlogic.gdx.Input.Keys.SYM
	private static final String[] defaultControls = {"up=51", "left=29", "down=47", "right=32", "fire=62", "changeSpread=30", "changeMode=34", "slow=59"};
	
	private final EntityManager entityManager;
	private long lastFire;
	private final OrthoCamera camera;
	public static float PositionX;
	private int shootDelay = 20;
	private float slow = 1f;
	private float spread = 0.5f;
	static String shootingMode = "Narrow", bulletMode = "light";
	private String difficulty;
	private boolean allowedRegen = true;
		
	public Player(Vector2 pos, Vector2 direction, EntityManager entityManager, OrthoCamera camera, String difficulty) {
		super(TextureManager.PLAYER, pos, direction);
		this.entityManager = entityManager;
		this.camera = camera;
		this.difficulty = difficulty;
		PositionX = pos.x;
		//if vexoid difficulty, the player cannot regen health
		if(this.difficulty == "vexoid")
			allowedRegen = false;
		//call the method to validate the controls file then read its contents
		readControls(validateControls());
	}
	
	
// Health stuff
	float healthPercent = 100;
	int clock = 0;
	boolean playerDied = false, playerOutOfLives = false;
	//method for health regen
	public void health(){
		clock ++;
		if(healthPercent >= 100.000)
			healthPercent=100.0f;
		if(clock > 750 && allowedRegen){
			for(int i=(int) healthPercent; i< 100; i++){
				healthPercent += 0.01f;
			}
			if(healthPercent >= 100)
				clock=0;
		}
	}
	public void increaseHealth(float ammount){
		healthPercent += ammount;
		if(healthPercent >= 100.0)
			healthPercent=100;
	}
	public void decreaseHealth(float ammount){
		clock=0;
		healthPercent -= ammount;
		if(healthPercent <= 0)
			playerDied = true;
	}
	public float getHealth(){
		return healthPercent;
	}
	
	boolean tookLives = false;
	public void liveSystem(){
		if (playerDied && !tookLives){
			tookLives = true;
			healthPercent = 100.0f;
			EntityManager.lives --;
			playerDied = false;
		}
	}
	public boolean playerOutOfLives(){
		return playerOutOfLives;
	}

//	Player hitbox
	public Rectangle getBounds() {
		return new Rectangle((pos.x + ((texture.getWidth()/2)/1.25f)), (pos.y + ((texture.getHeight()/2)/1.25f)),
				(texture.getWidth()- (texture.getWidth()/1.25f)), (texture.getHeight()-(texture.getHeight()/1.25f)));
	}

	public String shootingMode(){
		return shootingMode;
	}
	public String bulletMode(){
		return bulletMode;
	}
	int Switch = 0, Toggle = 1, Switch2 = 0, Toggle2 = 1;
	public void update() {

	if (canPlayerMove){
		pos.add(direction);
		PositionX = pos.x;
		health();
		liveSystem();
		int dir = 0;
		if (Gdx.input.isTouched()) {
			Vector2 touch = camera.unprojectCoordinates(Gdx.input.getX(), Gdx.input.getY());
			if (touch.x < MainGame.WIDTH / 2 && pos.x > 0)
				dir = 1;
			else
				if(pos.x < (MainGame.WIDTH - TextureManager.PLAYER.getWidth()))
				dir = 2;
		}
		
/* * * * * * * *
 * Here is the *
 * controls of *
 * the player  *
 * * * * * * * */
		final int highSpeed = 400;
		final int lowSpeed = 300;
		if(Gdx.input.isKeyPressed(shitf)){
			slow = 0.6f;
			this.texture = TextureManager.PLAYER_GLOW;
		} else {
			slow = 1f;
			this.texture = TextureManager.PLAYER;
		}
		//left if block
		if ((Gdx.input.isKeyPressed(left)&&!Gdx.input.isKeyPressed(right))&&(pos.x > 0)||(dir == 1)||
				((Gdx.input.isKeyPressed(leftArrow)&&!Gdx.input.isKeyPressed(rightArrow))&&(pos.x > 0))) {
			//if up and not down
			if((Gdx.input.isKeyPressed(up)&&!Gdx.input.isKeyPressed(down))&&(pos.y < MainGame.HEIGHT - this.texture.getHeight())||
					(Gdx.input.isKeyPressed(upArrow)&&!Gdx.input.isKeyPressed(downArrow))&&(pos.y < MainGame.HEIGHT - this.texture.getHeight())){
				setDirection(-lowSpeed*slow, lowSpeed*slow);
			}//if down and not up
			else if((Gdx.input.isKeyPressed(down)&&!Gdx.input.isKeyPressed(up))&&(pos.y > 0)||
					(Gdx.input.isKeyPressed(downArrow)&&!Gdx.input.isKeyPressed(upArrow))&&(pos.y > 0)){
				setDirection(-lowSpeed*slow, -lowSpeed*slow);
			}//all else
			else{
				setDirection(-highSpeed*slow, 0);
			}
		} else {
			//right if block
			if ((Gdx.input.isKeyPressed(right)&&!Gdx.input.isKeyPressed(left))&&(pos.x < MainGame.WIDTH - this.texture.getWidth())||(dir ==2)
					||((Gdx.input.isKeyPressed(rightArrow)&&!Gdx.input.isKeyPressed(leftArrow))
							&&(pos.x < MainGame.WIDTH - this.texture.getWidth()))) {
				//if up and not down
				if((Gdx.input.isKeyPressed(up)&&!Gdx.input.isKeyPressed(down))&&(pos.y < MainGame.HEIGHT - this.texture.getHeight())||
						(Gdx.input.isKeyPressed(upArrow)&&!Gdx.input.isKeyPressed(downArrow))&&(pos.y < MainGame.HEIGHT - this.texture.getHeight())){
					setDirection(lowSpeed*slow, lowSpeed*slow);
				}//if down and not up
				else if((Gdx.input.isKeyPressed(down)&&!Gdx.input.isKeyPressed(up))&&(pos.y > 0)||
						(Gdx.input.isKeyPressed(downArrow)&&!Gdx.input.isKeyPressed(upArrow))&&(pos.y > 0)){
					setDirection(lowSpeed*slow, -lowSpeed*slow);
				}//all else
				else{
					setDirection(highSpeed*slow, 0);
				}
			} else {
				//up if block
				if ((Gdx.input.isKeyPressed(up)&&!Gdx.input.isKeyPressed(down))
						&&(pos.y < MainGame.HEIGHT - this.texture.getHeight())
						|| (Gdx.input.isKeyPressed(upArrow)&&!Gdx.input.isKeyPressed(downArrow))
						&&(pos.y < MainGame.HEIGHT - this.texture.getHeight())) {
					setDirection(0, highSpeed*slow);
				} else {
					//down if block
					if ((Gdx.input.isKeyPressed(down)&&!Gdx.input.isKeyPressed(up))
							&&(pos.y > 0)|| (Gdx.input.isKeyPressed(downArrow)&&!Gdx.input.isKeyPressed(upArrow))&&(pos.y > 0)) {
						setDirection(0, -highSpeed*slow);
					} else {
						setDirection(0,0);
						}
					}
				}
			}
		
	//	Shooting modes (spread)
		if (Gdx.input.isKeyPressed(changeSpread)&& Switch != 1 && Toggle == 1){
			shootingMode = "Wide";
			spread = 3.5f;
			SoundManager.sound1.play(1);
			Toggle = 2;
		} else
		if (Gdx.input.isKeyPressed(changeSpread)&& Switch != 1 && Toggle == 2){
			shootingMode = "Narrow";
			spread = 0.5f;
			SoundManager.sound1.play(1);
			Toggle = 1;
		}
		if (Gdx.input.isKeyPressed(changeSpread))
			Switch = 1;
		else
			Switch = 0;
		
//	Switch Bullets
		if (Gdx.input.isKeyPressed(changeMode)&& Switch2 != 1 && Toggle2 == 1){
			bulletMode = "energy";
			shootDelay = 10;
			SoundManager.sound1.play(1);
			Toggle2 = 2;
		} else
		if (Gdx.input.isKeyPressed(changeMode)&& Switch2 != 1 && Toggle2 == 2){
			bulletMode = "heavy";
			shootDelay = 50;
			SoundManager.sound1.play(1);
			Toggle2 = 3;
		} else
		if (Gdx.input.isKeyPressed(changeMode)&& Switch2 != 1 && Toggle2 == 3){
			bulletMode = "light";
			shootDelay = 17;
			SoundManager.sound1.play(1);
			Toggle2 = 1;
		}			
		if (Gdx.input.isKeyPressed(changeMode))
			Switch2 = 1;
		else
			Switch2 = 0;
		
//	Shoot bullets
		Vector2 point1 = new Vector2(pos.cpy().add(-14, TextureManager.PLAYER.getHeight()/2));
		Vector2 point2 = new Vector2(pos.cpy().add(TextureManager.PLAYER.getWidth()-17,TextureManager.PLAYER.getHeight()/2));
		float var = MathUtils.random(2,6);
		float var2 = 3;
		
		if (Gdx.input.isKeyPressed(fire) || dir ==1 || dir == 2){
			if(bulletMode == "light")
				if (System.currentTimeMillis() - lastFire >= shootDelay) {
					int r = MathUtils.random(0,1);
					if(r==0)
						entityManager.addEntity(new Blue_Bullet2(point1, new Vector2(MathUtils.random(-spread, spread+var2), 18)));
					if(r==1)
						entityManager.addEntity(new Blue_Bullet2(point2, new Vector2(MathUtils.random(-spread-var2, spread), 18)));
				
					SoundManager.shot2.play(0.2f);
					lastFire = System.currentTimeMillis();
				}
			if(bulletMode == "heavy")
				if (System.currentTimeMillis() - lastFire >= shootDelay) {
					int r = MathUtils.random(0,1);
					if(r==0)
						entityManager.addEntity(new Red_Bullet2(point1, new Vector2(MathUtils.random(-spread, spread+var2), 18)));
					if(r==1)
						entityManager.addEntity(new Red_Bullet2(point2, new Vector2(MathUtils.random(-spread-var2, spread), 18)));
					
					SoundManager.shot2.play(0.2f);
					lastFire = System.currentTimeMillis();
				}
			if(bulletMode == "energy")
				if (System.currentTimeMillis() - lastFire >= shootDelay) {
					int r = MathUtils.random(0,1);
					if(r==0)
						entityManager.addEntity(new Yellow_Bullet2(point1, new Vector2(MathUtils.random(-spread-var, spread), 10)));
					if(r==1)
						entityManager.addEntity(new Yellow_Bullet2(point2, new Vector2(MathUtils.random(-spread, spread+var), 10)));
					SoundManager.shot3.play(0.1f);
					lastFire = System.currentTimeMillis();
				}
		}
	}
}
	boolean canPlayerMove = true;
	public void playerCanMove(boolean moving) {
		canPlayerMove = moving;
	}
	
	//this method ensures that the %user%\Documents\Vexoids\controls.txt file exists and populates it with the controls if necessary
	private File validateControls(){
		//building the string for the path to C:\Vexoids\controls.txt
		String ctrlFileString = "C:\\\\Vexoids\\controls.txt";
		File ctrlFile = new File(ctrlFileString);
		//try block for File writing
		try{
		//invoked if the file/directory does NOT exist
		if(!ctrlFile.exists()){
			//invoked if the directory does not exist
			if(!ctrlFile.getParentFile().exists()){
				if (ctrlFile.getParentFile().mkdir()) {
				    ctrlFile.createNewFile();
				    System.out.println("File and directory created at " + ctrlFileString.toString());
				    generateDefaults(ctrlFile);
				} else {
				    throw new IOException("Failed to create directory " + ctrlFile.getParent());
				}
			}//invoked if just the file does not exist
			else{
				ctrlFile.createNewFile();
				System.out.println("File created at " + ctrlFileString.toString());
				generateDefaults(ctrlFile);
			}
		}//invoked if the file DOES exist
		else{
			System.out.println("controls file exists at " + ctrlFileString.toString());
		}
		}catch(java.io.IOException e){
			System.err.print(e);
			System.exit(1);
		}
		return ctrlFile;
	}
		
	//this method grabs the contents from the controls file and populates variables
	private void readControls(File ctrlFile){
		try(Scanner scanner = new Scanner(ctrlFile);){
			int i = 0;
			while (scanner.hasNextLine()){
		        String[] s = scanner.next().split("=");
		        switch(i){
		        case 0: up = Integer.parseInt(s[1]);
		        case 1: left = Integer.parseInt(s[1]);
		        case 2: down = Integer.parseInt(s[1]);
		        case 3: right = Integer.parseInt(s[1]);
		        case 4: fire = Integer.parseInt(s[1]);
		        case 5: changeSpread = Integer.parseInt(s[1]);
		        case 6: changeMode = Integer.parseInt(s[1]);
		        case 7: shitf = Integer.parseInt(s[1]);
		        }
		        i++;
		    }
			scanner.close();
		} catch (Exception e) {//catch all.. deletes the file then recreates with default controls
			if(ctrlFile.delete()){
				readControls(validateControls());
			}
			else{
				System.exit(1);
			}
		}
	}
		
	//this method takes a file object and populates that file with the controls specified
	private void generateDefaults(File f){
		//try block in case the file suddenly disappears
		try {
			PrintWriter w = new PrintWriter(f);
			for(int i = 0;i<defaultControls.length;i++){
				w.print(defaultControls[i]);
				if(i<defaultControls.length-1)
					w.print("\n");
			}
			w.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}
