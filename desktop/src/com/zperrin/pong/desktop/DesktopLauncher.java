package com.zperrin.pong.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.zperrin.pong.Pong;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Pong.WIDTH;
		config.height = Pong.HEIGHT;

		new LwjglApplication(new Pong(), config);
	}
}
