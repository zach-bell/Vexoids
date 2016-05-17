package com.vexoid.game.screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.vexoid.game.MainGame;
import com.vexoid.game.SoundManager;
import com.vexoid.game.camera.OrthoCamera;

public class OptionsScreen extends Screen {
	private static boolean isChanging = false;
	private OrthoCamera camera;
	String difficulty, Options = "Options PRESS ESC TO EXIT";
	ScreenManager screenManager;
	BitmapFont displayOptionsText;
	BitmapFont displayVariableText;

	public void create(ScreenManager screenManager, String difficulty) {
		this.difficulty = difficulty;
		this.screenManager = screenManager;
		camera = new OrthoCamera();
		camera.resize();

		displayOptionsText = new BitmapFont();
		displayOptionsText.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		displayVariableText = new BitmapFont();
		displayVariableText.setColor(1.0f, 1.0f, 1.0f, 1.0f);
	}

	int wait = 50;//so that the player doesn't click on something on accident when loading in to this screen
	int rebindKey = -1;// -1 is no pending rebind, -2 is an invalid key, -3 is wating on the player to enter a key, 131 is escape pressed

	public void update() {
		camera.update();
		int newControl = whatIsPressed();//consistently called by update() this method looks for valid key presses
		if ((newControl == Input.Keys.ESCAPE)){//invoked when escape is pressed and when waiting for a key
			rebindKey = -1;// reset the rebindKey value to no pending rebind
			isChanging = false;
		}
		if (wait > 0)
			wait--;
		if (wait <= 0)
			if (Gdx.input.isTouched() || rebindKey != -1) {// checking for clicks or if a pending keypress is active
				Vector2 touch = camera.unprojectCoordinates(Gdx.input.getX(), Gdx.input.getY());
				isChanging = true;
				// if block for up
				if (false || rebindKey == 0) {
					rebindKey = 0;// sentinel to allow update() to escape here
					if (newControl == -3) {
					} //invoked when a valid key is pressed and the write succeeds when nothing has been pressed yet
					else if (newControl == -2) {//invoked when a valid key is pressed and the write succeeds if an invalid (or disallowed) key is pressed
						SoundManager.sound1.play(1);
						newControl = -3;// play invalid sound and revert newControl to nothing pressed
					} else if (com.vexoid.game.Options.writeControls(rebindKey, newControl)) {//invoked when a valid key is pressed and the write succeeds
						rebindKey = -1;// reset the rebindKey value to no pending rebind
						com.vexoid.game.Options.readControls(com.vexoid.game.Options.validateFile("C:\\\\Vexoids\\controls.txt"));//reads the new control configuration
						isChanging = false;
					}
				} // if block for down
				else if (false || rebindKey == 1) {
					rebindKey = 1;// sentinel to allow update() to escape here
					if (newControl == -3) {
					} //invoked when a valid key is pressed and the write succeeds when nothing has been pressed yet
					else if (newControl == -2) {//invoked when a valid key is pressed and the write succeeds if an invalid (or disallowed) key is pressed
						SoundManager.sound1.play(1);
						newControl = -3;// play invalid sound and revert newControl to nothing pressed
					} else if (com.vexoid.game.Options.writeControls(rebindKey, newControl)) {//invoked when a valid key is pressed and the write succeeds
						rebindKey = -1;
						com.vexoid.game.Options.readControls(com.vexoid.game.Options.validateFile("C:\\\\Vexoids\\controls.txt"));//reads the new control configuration
						isChanging = false;
					}
				} // if block for left
				else if (false || rebindKey == 2) {
					rebindKey = 2;// sentinel to allow update() to escape here
					if (newControl == -3) {
					} //invoked when a valid key is pressed and the write succeeds when nothing has been pressed yet
					else if (newControl == -2) {//invoked when a valid key is pressed and the write succeeds if an invalid (or disallowed) key is pressed
						SoundManager.sound1.play(1);
						newControl = -3;// play invalid sound and revert newControl to nothing pressed
					} else if (com.vexoid.game.Options.writeControls(rebindKey, newControl)) {//invoked when a valid key is pressed and the write succeeds
						System.out.println("IN LEFT AND REBIND KEY INT IS " + rebindKey);
						rebindKey = -1;
						com.vexoid.game.Options.readControls(com.vexoid.game.Options.validateFile("C:\\\\Vexoids\\controls.txt"));//reads the new control configuration
						isChanging = false;
					}
				} // if block for right
				else if (false || rebindKey == 3) {
					rebindKey = 3;// sentinel to allow update() to escape here
					if (newControl == -3) {
					} //invoked when a valid key is pressed and the write succeeds when nothing has been pressed yet
					else if (newControl == -2) {//invoked when a valid key is pressed and the write succeeds if an invalid (or disallowed) key is pressed
						SoundManager.sound1.play(1);
						newControl = -3;// play invalid sound and revert newControl to nothing pressed
					} else if (com.vexoid.game.Options.writeControls(rebindKey, newControl)) {//invoked when a valid key is pressed and the write succeeds
						rebindKey = -1;
						com.vexoid.game.Options.readControls(com.vexoid.game.Options.validateFile("C:\\\\Vexoids\\controls.txt"));//reads the new control configuration
						isChanging = false;
					}
				} // if block for fire
				else if (((touch.x < MainGame.WIDTH / 2 && touch.y > MainGame.HEIGHT / 2)) || rebindKey == 4) {
					rebindKey = 4;// sentinel to allow update() to escape here
					if (newControl == -3) {
					} //invoked when a valid key is pressed and the write succeeds when nothing has been pressed yet
					else if (newControl == -2) {//invoked when a valid key is pressed and the write succeeds if an invalid (or disallowed) key is pressed
						SoundManager.sound1.play(1);
						newControl = -3;// play invalid sound and revert newControl to nothing pressed
					} else if (com.vexoid.game.Options.writeControls(rebindKey, newControl)) {//invoked when a valid key is pressed and the write succeeds
						rebindKey = -1;
						com.vexoid.game.Options.readControls(com.vexoid.game.Options.validateFile("C:\\\\Vexoids\\controls.txt"));//reads the new control configuration
						isChanging = false;
					}
				} // if block for changeSpread
				else if (((touch.x > MainGame.WIDTH / 2 && touch.y > MainGame.HEIGHT / 2)) || rebindKey == 5) {
					rebindKey = 5;// sentinel to allow update() to escape here
					if (newControl == -3) {
					} //invoked when a valid key is pressed and the write succeeds when nothing has been pressed yet
					else if (newControl == -2) {//invoked when a valid key is pressed and the write succeeds if an invalid (or disallowed) key is pressed
						SoundManager.sound1.play(1);
						newControl = -3;// play invalid sound and revert newControl to nothing pressed
					} else if (com.vexoid.game.Options.writeControls(rebindKey, newControl)) {//invoked when a valid key is pressed and the write succeeds
						rebindKey = -1;
						com.vexoid.game.Options.readControls(com.vexoid.game.Options.validateFile("C:\\\\Vexoids\\controls.txt"));//reads the new control configuration
						isChanging = false;
					}
				} // if block for changeMode
				else if (((touch.x > MainGame.WIDTH / 2 && touch.y < MainGame.HEIGHT / 2)) || rebindKey == 6) {
					rebindKey = 6;// sentinel to allow update() to escape here
					if (newControl == -3) {
					} //invoked when a valid key is pressed and the write succeeds when nothing has been pressed yet
					else if (newControl == -2) {//invoked when a valid key is pressed and the write succeeds if an invalid (or disallowed) key is pressed
						SoundManager.sound1.play(1);
						newControl = -3;// play invalid sound and revert newControl to nothing pressed
					} else if (com.vexoid.game.Options.writeControls(rebindKey, newControl)) {//invoked when a valid key is pressed and the write succeeds
						rebindKey = -1;
						com.vexoid.game.Options.readControls(com.vexoid.game.Options.validateFile("C:\\\\Vexoids\\controls.txt"));//reads the new control configuration
						isChanging = false;
					}
				} // if block for slow
				else if (((touch.x < MainGame.WIDTH / 2 && touch.y < MainGame.HEIGHT / 2)) || rebindKey == 7) {
					rebindKey = 7;// sentinel to allow update() to escape here
					if (newControl == -3) {
					} //invoked when a valid key is pressed and the write succeeds when nothing has been pressed yet
					else if (newControl == -2) {//invoked when a valid key is pressed and the write succeeds if an invalid (or disallowed) key is pressed
						SoundManager.sound1.play(1);
						newControl = -3;// play invalid sound and revert newControl to nothing pressed
					} else if (com.vexoid.game.Options.writeControls(rebindKey, newControl)) {//invoked when a valid key is pressed and the write succeeds
						rebindKey = -1;
						com.vexoid.game.Options.readControls(com.vexoid.game.Options.validateFile("C:\\\\Vexoids\\controls.txt"));//reads the new control configuration
						isChanging = false;
					}
				}
			}
	}

	public void render(SpriteBatch sb) {
		sb.setProjectionMatrix(camera.combined);
		sb.begin();
		//if currently rebindig a key, display this message
		if (isChanging){
			displayVariableText.draw(sb, "Please enter a key or ESC to cancel", MainGame.WIDTH/2 - MainGame.WIDTH*.4f, MainGame.HEIGHT / 2);
		}
		try{
		// these are the control values
		displayOptionsText.draw(sb, Input.Keys.toString(com.vexoid.game.Options.controls.getInt("up")) + "  OR UP ARROW",
				MainGame.WIDTH / 2 + MainGame.WIDTH * .3f, MainGame.HEIGHT - 130);
		displayOptionsText.draw(sb, Input.Keys.toString(com.vexoid.game.Options.controls.getInt("down")) + "  OR DOWN ARROW",
				MainGame.WIDTH / 2 + MainGame.WIDTH * .3f, MainGame.HEIGHT - 160);
		displayOptionsText.draw(sb, Input.Keys.toString(com.vexoid.game.Options.controls.getInt("left")) + "  OR LEFT ARROW",
				MainGame.WIDTH / 2 + MainGame.WIDTH * .3f, MainGame.HEIGHT - 190);
		displayOptionsText.draw(sb, Input.Keys.toString(com.vexoid.game.Options.controls.getInt("right")) + "  OR RIGHT ARROW",
				MainGame.WIDTH / 2 + MainGame.WIDTH * .3f, MainGame.HEIGHT - 220);
		displayOptionsText.draw(sb, Input.Keys.toString(com.vexoid.game.Options.controls.getInt("fire")), MainGame.WIDTH / 2 + MainGame.WIDTH * .3f,
				MainGame.HEIGHT - 250);
		displayOptionsText.draw(sb, Input.Keys.toString(com.vexoid.game.Options.controls.getInt("changeSpread")), MainGame.WIDTH / 2 + MainGame.WIDTH * .3f,
				MainGame.HEIGHT - 280);
		displayOptionsText.draw(sb, Input.Keys.toString(com.vexoid.game.Options.controls.getInt("changeMode")), MainGame.WIDTH / 2 + MainGame.WIDTH * .3f,
				MainGame.HEIGHT - 310);
		displayOptionsText.draw(sb, Input.Keys.toString(com.vexoid.game.Options.controls.getInt("slow")), MainGame.WIDTH / 2 + MainGame.WIDTH * .3f,
				MainGame.HEIGHT - 340);
		// these are the literal string values for the options page
		displayOptionsText.draw(sb, Options, MainGame.WIDTH / 2 - 175, MainGame.HEIGHT - 10);
		displayOptionsText.draw(sb, "MOVE PLAYER UP", MainGame.WIDTH / 2, MainGame.HEIGHT - 130);
		displayOptionsText.draw(sb, "MOVE PLAYER DOWN", MainGame.WIDTH / 2, MainGame.HEIGHT - 160);
		displayOptionsText.draw(sb, "MOVE PLAYER LEFT", MainGame.WIDTH / 2, MainGame.HEIGHT - 190);
		displayOptionsText.draw(sb, "MOVE PLAYER RIGHT", MainGame.WIDTH / 2, MainGame.HEIGHT - 220);
		displayOptionsText.draw(sb, "FIRE LASERS", MainGame.WIDTH / 2, MainGame.HEIGHT - 250);
		displayOptionsText.draw(sb, "CHANGE WEAPON SPREAD", MainGame.WIDTH / 2, MainGame.HEIGHT - 280);
		displayOptionsText.draw(sb, "CHANGE WEAPONS", MainGame.WIDTH / 2, MainGame.HEIGHT - 310);
		displayOptionsText.draw(sb, "SLOW SHIP", MainGame.WIDTH / 2, MainGame.HEIGHT - 340);

		// temp rebind strings (delete eventually)
		displayOptionsText.draw(sb, "REBIND FIRE LASERS", MainGame.WIDTH / 2 - 300, MainGame.HEIGHT - 50);
		displayOptionsText.draw(sb, "REBIND CHANGE WEAPON SPREAD", MainGame.WIDTH / 2 + 125, MainGame.HEIGHT - 50);
		displayOptionsText.draw(sb, "REBIND CHANGE WEAPONS", MainGame.WIDTH / 2 + 175, MainGame.HEIGHT - 550);
		displayOptionsText.draw(sb, "REBIND SLOW SHIP", MainGame.WIDTH / 2 - 300, MainGame.HEIGHT - 550);
		}
		catch(Exception e){
			System.err.println("reverting controls file due to an error in OptionsScreen render");
			com.vexoid.game.Options.generateDefaults(com.vexoid.game.Options.validateFile("C:\\\\Vexoids\\controls.txt"));
		}
		sb.end();
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
		return "OptionsScreen";
	}

	public static boolean isChanging() {
		return isChanging;
	}

	private int whatIsPressed() {
		int sentinel = -3;
		if (Gdx.input.isKeyPressed(Input.Keys.A))
			sentinel = Input.Keys.A;
		else if (Gdx.input.isKeyPressed(Input.Keys.B))
			sentinel = Input.Keys.B;
		else if (Gdx.input.isKeyPressed(Input.Keys.C))
			sentinel = Input.Keys.C;
		else if (Gdx.input.isKeyPressed(Input.Keys.D))
			sentinel = Input.Keys.D;
		else if (Gdx.input.isKeyPressed(Input.Keys.E))
			sentinel = Input.Keys.E;
		else if (Gdx.input.isKeyPressed(Input.Keys.F))
			sentinel = Input.Keys.F;
		else if (Gdx.input.isKeyPressed(Input.Keys.G))
			sentinel = Input.Keys.G;
		else if (Gdx.input.isKeyPressed(Input.Keys.H))
			sentinel = Input.Keys.H;
		else if (Gdx.input.isKeyPressed(Input.Keys.I))
			sentinel = Input.Keys.I;
		else if (Gdx.input.isKeyPressed(Input.Keys.J))
			sentinel = Input.Keys.J;
		else if (Gdx.input.isKeyPressed(Input.Keys.K))
			sentinel = Input.Keys.K;
		else if (Gdx.input.isKeyPressed(Input.Keys.L))
			sentinel = Input.Keys.L;
		else if (Gdx.input.isKeyPressed(Input.Keys.M))
			sentinel = Input.Keys.M;
		else if (Gdx.input.isKeyPressed(Input.Keys.N))
			sentinel = Input.Keys.N;
		else if (Gdx.input.isKeyPressed(Input.Keys.O))
			sentinel = Input.Keys.O;
		else if (Gdx.input.isKeyPressed(Input.Keys.P))
			sentinel = Input.Keys.P;
		else if (Gdx.input.isKeyPressed(Input.Keys.Q))
			sentinel = Input.Keys.Q;
		else if (Gdx.input.isKeyPressed(Input.Keys.R))
			sentinel = Input.Keys.R;
		else if (Gdx.input.isKeyPressed(Input.Keys.S))
			sentinel = Input.Keys.S;
		else if (Gdx.input.isKeyPressed(Input.Keys.T))
			sentinel = Input.Keys.T;
		else if (Gdx.input.isKeyPressed(Input.Keys.U))
			sentinel = Input.Keys.U;
		else if (Gdx.input.isKeyPressed(Input.Keys.V))
			sentinel = Input.Keys.V;
		else if (Gdx.input.isKeyPressed(Input.Keys.W))
			sentinel = Input.Keys.W;
		else if (Gdx.input.isKeyPressed(Input.Keys.X))
			sentinel = Input.Keys.X;
		else if (Gdx.input.isKeyPressed(Input.Keys.Y))
			sentinel = Input.Keys.Y;
		else if (Gdx.input.isKeyPressed(Input.Keys.Z))
			sentinel = Input.Keys.Z;
		else if (Gdx.input.isKeyPressed(Input.Keys.SPACE))
			sentinel = Input.Keys.SPACE;
		else if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT))
			sentinel = Input.Keys.CONTROL_LEFT;
		else if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_RIGHT))
			sentinel = Input.Keys.CONTROL_RIGHT;
		else if (Gdx.input.isKeyPressed(Input.Keys.ALT_LEFT))
			sentinel = Input.Keys.ALT_LEFT;
		else if (Gdx.input.isKeyPressed(Input.Keys.ALT_RIGHT))
			sentinel = Input.Keys.ALT_RIGHT;
		else if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT))
			sentinel = Input.Keys.SHIFT_LEFT;
		else if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT))
			sentinel = Input.Keys.SHIFT_RIGHT;
		else if (Gdx.input.isKeyPressed(Input.Keys.TAB))
			sentinel = Input.Keys.TAB;
		else if (Gdx.input.isKeyPressed(Input.Keys.NUM_1))
			sentinel = Input.Keys.NUM_1;
		else if (Gdx.input.isKeyPressed(Input.Keys.NUM_2))
			sentinel = Input.Keys.NUM_2;
		else if (Gdx.input.isKeyPressed(Input.Keys.NUM_3))
			sentinel = Input.Keys.NUM_3;
		else if (Gdx.input.isKeyPressed(Input.Keys.NUM_4))
			sentinel = Input.Keys.NUM_4;
		else if (Gdx.input.isKeyPressed(Input.Keys.NUM_5))
			sentinel = Input.Keys.NUM_5;
		else if (Gdx.input.isKeyPressed(Input.Keys.NUM_6))
			sentinel = Input.Keys.NUM_6;
		else if (Gdx.input.isKeyPressed(Input.Keys.NUM_7))
			sentinel = Input.Keys.NUM_7;
		else if (Gdx.input.isKeyPressed(Input.Keys.NUM_8))
			sentinel = Input.Keys.NUM_8;
		else if (Gdx.input.isKeyPressed(Input.Keys.NUM_9))
			sentinel = Input.Keys.NUM_9;
		else if (Gdx.input.isKeyPressed(Input.Keys.NUM_0))
			sentinel = Input.Keys.NUM_0;
		else if (Gdx.input.isKeyPressed(Input.Keys.NUMPAD_1))
			sentinel = Input.Keys.NUMPAD_1;
		else if (Gdx.input.isKeyPressed(Input.Keys.NUMPAD_2))
			sentinel = Input.Keys.NUMPAD_2;
		else if (Gdx.input.isKeyPressed(Input.Keys.NUMPAD_3))
			sentinel = Input.Keys.NUMPAD_4;
		else if (Gdx.input.isKeyPressed(Input.Keys.NUMPAD_5))
			sentinel = Input.Keys.NUMPAD_5;
		else if (Gdx.input.isKeyPressed(Input.Keys.NUMPAD_6))
			sentinel = Input.Keys.NUMPAD_6;
		else if (Gdx.input.isKeyPressed(Input.Keys.NUMPAD_7))
			sentinel = Input.Keys.NUMPAD_7;
		else if (Gdx.input.isKeyPressed(Input.Keys.NUMPAD_8))
			sentinel = Input.Keys.NUMPAD_8;
		else if (Gdx.input.isKeyPressed(Input.Keys.NUMPAD_9))
			sentinel = Input.Keys.NUMPAD_9;
		else if (Gdx.input.isKeyPressed(Input.Keys.NUMPAD_0))
			sentinel = Input.Keys.NUMPAD_0;
		else if (Gdx.input.isKeyPressed(Input.Keys.LEFT_BRACKET))
			sentinel = Input.Keys.LEFT_BRACKET;
		else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT_BRACKET))
			sentinel = Input.Keys.RIGHT_BRACKET;
		else if (Gdx.input.isKeyPressed(Input.Keys.BACKSLASH))
			sentinel = Input.Keys.BACKSLASH;
		else if (Gdx.input.isKeyPressed(Input.Keys.SLASH))
			sentinel = Input.Keys.SLASH;
		else if (Gdx.input.isKeyPressed(Input.Keys.COMMA))
			sentinel = Input.Keys.COMMA;
		else if (Gdx.input.isKeyPressed(Input.Keys.PERIOD))
			sentinel = Input.Keys.PERIOD;
		else if (Gdx.input.isKeyPressed(Input.Keys.SEMICOLON))
			sentinel = Input.Keys.SEMICOLON;
		else if (Gdx.input.isKeyPressed(Input.Keys.APOSTROPHE))
			sentinel = Input.Keys.APOSTROPHE;
		else if (Gdx.input.isKeyPressed(Input.Keys.MINUS))
			sentinel = Input.Keys.MINUS;
		else if (Gdx.input.isKeyPressed(Input.Keys.EQUALS))
			sentinel = Input.Keys.EQUALS;
		else if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
			sentinel = Input.Keys.ESCAPE;
		else if (Gdx.input.isKeyPressed(-1))
			sentinel = -2;
		return sentinel;
	}
}
