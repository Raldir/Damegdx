package com.mygdx.dame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.dame.DameMain;

public class DesktopLauncher {
	
	public static void main(String[] args) {
		
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = DameMain.TITLE;
		cfg.width = DameMain.WIDTH * DameMain.SCALE;
		cfg.height = (DameMain.HEIGHT + DameMain.HEIGHTINTERFACE)* DameMain.SCALE;
		new LwjglApplication(new DameMain(), cfg);
		
	}
}
