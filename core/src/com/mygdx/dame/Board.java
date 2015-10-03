package com.mygdx.dame;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;

public class Board{
	
	ArrayList<Asset> squares = new ArrayList<Asset>();
	
	
	public Board(){
		create();
		
	}
	
	public void create(){
		for(int i = 0; i < 64; i++){
			Asset s;
			if((i / 8) % 2 == 0){
				if(i % 2 == 0){
					squares.add(s = new Asset("images/Square_Black.png", i % 8 * (DameMain.WIDTH / 8), (i / 8) * (DameMain.HEIGHT / 8) , (DameMain.WIDTH / 8), (DameMain.HEIGHT / 8)));
					s.setIndex(i);
				}else{
					squares.add(s = new Asset("images/Square_White.png", i % 8 * (DameMain.WIDTH / 8), (i / 8) * (DameMain.HEIGHT /8 ), (DameMain.WIDTH / 8), (DameMain.HEIGHT / 8)));
					s.setIndex(i);
				}
			}else{
				if(i % 2 == 0){
					squares.add(s = new Asset("images/Square_White.png", i % 8 * (DameMain.WIDTH / 8), (i / 8) * (DameMain.HEIGHT /8), (DameMain.WIDTH / 8), (DameMain.HEIGHT / 8)));
					s.setIndex(i);
				}else{
					squares.add(s =new Asset("images/Square_Black.png", i % 8 * (DameMain.WIDTH / 8), (i / 8) * (DameMain.HEIGHT / 8) , (DameMain.WIDTH / 8), (DameMain.HEIGHT / 8)));
					s.setIndex(i);
				}
			}
		}
	}
	
	
	public ArrayList<Asset> getAssets(){
		return squares;
	}
}
