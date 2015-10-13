package com.mygdx.dame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;

public class DameMain extends Game {
	
	public static String TITLE = "Dame";
	public static int WIDTH = 800;
	public static int HEIGHT = 600;
	public static int HEIGHTINTERFACE = 150;
	public static final int MAXHEIGHT= HEIGHT + HEIGHTINTERFACE;
	public static int SCALE = 1;

	
	public MenuScreen menu_screen;
	
	
	@Override
	public void create () {
		menu_screen = new MenuScreen(this);
		this.setScreen(menu_screen);
	}
	
	
//    void setMenuScreen()
//    {
//        menu_screen = new MenuScreen(this);
//        setScreen(menu_screen);
//    }
    
	public void reset(){
		this.dispose();
		create();
	}
    
	@Override
	public void render () {
		super.render();
	}
}
