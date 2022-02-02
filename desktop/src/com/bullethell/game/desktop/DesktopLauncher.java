package com.bullethell.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.bullethell.game.BulletHellMain;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = BulletHellMain.width;
		config.height = BulletHellMain.height;
		new LwjglApplication(new BulletHellMain(), config);
	}
}
