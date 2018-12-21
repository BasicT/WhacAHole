package com.codework.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.codework.game.WhacAHole;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        float scale = 0.32f;
        config.width = (int)(1080*scale);
        config.height = (int)(1920*scale);
        config.resizable = false;
		new LwjglApplication(new WhacAHole(), config);
	}
}
