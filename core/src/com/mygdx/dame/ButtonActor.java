package com.mygdx.dame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class ButtonActor extends Image{
	
	

	
	public ButtonActor(String pfad, float x, float y, final DameMain game) {
		super(createTexture(pfad));
		super.setBounds(x, y, super.getWidth(), super.getHeight());
		super.addListener(new ClickListener(){
			 public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
			    {
				 	game.setScreen(new GameScreen(game));
			        return false;
			    }
		});
	}
	
//	public static void initializeButtonActor(String pfad, final DameMain game){
//		Texture texture_back = new Texture(Gdx.files.internal(pfad));
//		texture_back.setFilter(TextureFilter.Linear, TextureFilter.Linear);
//		
//		Image image = new Image(texture_back);
//		image.setBounds(170, 100, image.getWidth(), image.getHeight());
//		MenuScreen.stage.addActor(image);
//		image.addListener(new ClickListener(){
//			 public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
//			    {
//			        game.setScreen(new GameScreen(game));
//			        System.out.println("hello");
//			        return true;
//			    }
//		});
//	}
	
	private static Texture createTexture(String pfad){
		Texture texture_back = new Texture(Gdx.files.internal(pfad));
		texture_back.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		return texture_back;
	}
	
}
