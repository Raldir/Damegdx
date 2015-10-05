package com.mygdx.dame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Asset extends AbstractAsset{

	
	public Asset(String pfad, float x, float y, float width, float height, int index){
		super(pfad, x ,y, width, height, index);
	}
	
	@Override
	public boolean specialEvent() {
		return false;
	}
	
}
