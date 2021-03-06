package com.mygdx.dame;

import java.util.ArrayList;


public class Board{
	
	ArrayList<Asset> squares = new ArrayList<Asset>();
	
	
	public Board(){
		create();
		
	}
	
	public void create(){
		int xcomponent =(DameMain.WIDTH / 8);
		int ycomponent = ((DameMain.MAXHEIGHT - DameMain.HEIGHTINTERFACE)/ 8);
		for(int i = 0; i < 8 ; i++){
				if(i % 2 == 0){
					squares.add(new DameTriggerField("images/Square_White.png", i % 8 * xcomponent, (i / 8) * ycomponent , xcomponent, ycomponent, i, "images/DamePicture.png"));
				}else{
					squares.add(new DameTriggerField("images/Square_Black.png", i % 8 * (DameMain.WIDTH / 8), (i / 8) * ycomponent  , xcomponent, ycomponent, i, "images/DamePicture.png"));
				}
			}
		for(int i = 8; i < 56; i++){
			if((i / 8) % 2 == 0){
				if(i % 2 == 0){
						squares.add(new Asset("images/Square_White.png", i % 8 * xcomponent, (i / 8) * ycomponent , (DameMain.WIDTH / 8), ycomponent, i));
					}else{
						squares.add(new Asset("images/Square_Black.png", i % 8 * xcomponent, (i / 8) * ycomponent , (DameMain.WIDTH / 8), ycomponent, i));
					}
			}else{
				if(i % 2 == 0){
					squares.add(new Asset("images/Square_Black.png", i % 8 * xcomponent, (i / 8) * (DameMain.HEIGHT /8), xcomponent, ycomponent, i));

				}else{
					squares.add(new Asset("images/Square_White.png", i % 8 * xcomponent, (i / 8) * (DameMain.HEIGHT / 8) , xcomponent, ycomponent, i));

				}
			}
		}
		for(int i = 56; i < 64; i++){
			if(i % 2 == 0){
				squares.add(new DameTriggerField("images/Square_Black.png", i % 8 * (DameMain.WIDTH / 8), (i / 8) * (DameMain.HEIGHT / 8) , (DameMain.WIDTH / 8), (DameMain.HEIGHT / 8), i, "images/DamePicture.png"));
			}else{
				squares.add(new DameTriggerField("images/Square_White.png", i % 8 * (DameMain.WIDTH / 8), (i / 8) * (DameMain.HEIGHT / 8) , (DameMain.WIDTH / 8), (DameMain.HEIGHT / 8), i, "images/DamePicture.png"));
			}
		}
	}
	
	
	public ArrayList<Asset> getAssets(){
		return squares;
	}
}
