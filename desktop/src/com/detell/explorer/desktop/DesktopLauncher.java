package com.detell.explorer.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.detell.explorer.ExplorerGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Explorer";
		config.width = 1280;
		config.height = 768;

		new LwjglApplication(new ExplorerGame(), config);
	}
}
