package com.vexoid.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.vexoid.game.MainGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Vexoids";
		config.useGL30 = true;
		config.width = MainGame.WIDTH;
		config.height = MainGame.HEIGHT;
		config.resizable = false;
		
		new LwjglApplication(new MainGame(), config);
	}
}
