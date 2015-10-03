package com.mygdx.dame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Asset extends Image{

	public Token token = null;
	public int index;
	
	public Asset(String pfad, float x, float y, float width, float height){
		super(createTexture(pfad));
		super.setBounds(x, y, width, height);
		
	}
	
	public static Texture createTexture(String pfad){
		Texture texture_back = new Texture(Gdx.files.internal(pfad));
		texture_back.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		return texture_back;
	}
	
	public void setIndex(int index){
		this.index = index;
	}
	
	public int getIndex(){
		return index;
	}
	
	public void setToken(Token token){
		this.token = token;
	}
	
	public Token getToken(){
		return token;
	}
	
	public void removeToken(){
		System.out.println(token.getX());
		token.remove();
		setToken(null);
	}
}
