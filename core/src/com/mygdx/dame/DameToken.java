package com.mygdx.dame;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;


public class DameToken extends Token {

	public DameToken(String pfad, Asset as, Player p) {
		super(pfad, as, p);
		GameScreen.stage.addActor(this);
		p.addToken(this);
	}
	
	public void addTargetHorizontal(final ArrayList<Asset> assets, final int target, Player p){
		System.out.println(field.getIndex());
		if(field.getIndex() + target >= 0 && field.getIndex() + target < assets.size()){
			System.out.println("firstif");
			System.out.println(checkifNegativ(target,1));
			dragAndDrop.addTarget(new Target(assets.get(field.getIndex() + target)){
				public boolean drag (Source source, Payload payload, float x, float y, int pointer) {
//					System.out.println(field.getIndex() + target + checkifNegativ(target, 1));
					getActor().setColor(Color.GREEN);
					Gdx.graphics.setContinuousRendering(true);
					return true;
				}

				public void reset (Source source, Payload payload) {
					getActor().setColor(Color.WHITE);
				}
				public void drop (Source source, Payload payload, float x, float y, int pointer) {
					boolean jumpedOver = false;
					boolean tokenInWay = false;
					for(int i = 1; i < Math.abs(target); i++){
						if(assets.get(field.getIndex() + checkifNegativ(target, i)).getToken() != null){
							if(assets.get(field.getIndex() + checkifNegativ(target, i)).getToken().getPlayer().getID() != player.getID()){
								jumpOver(assets.get(field.getIndex() + checkifNegativ(target, i) + checkifNegativ(target, 1)),
								field, assets.get(field.getIndex() + checkifNegativ(target, i)));
								System.out.println("jumpedOver");
								jumpedOver = true;
							}
							tokenInWay = true;
						}
						}
					System.out.println(jumpedOver +" " + tokenInWay);
					if(!jumpedOver && !tokenInWay){
							move(assets.get(field.index + target), field);
						
					}
					Gdx.graphics.setContinuousRendering(false);
				}
			});
		}
	}
	
	
	public  void addTargetVertical(final ArrayList<Asset> assets, final int target, Player p){
		if(field.getIndex() + target >= 0 && field.getIndex() + target < assets.size()){
			dragAndDrop.addTarget(new Target(assets.get(field.getIndex() + target)){
				public boolean drag (Source source, Payload payload, float x, float y, int pointer) {
					getActor().setColor(Color.GREEN);
					Gdx.graphics.setContinuousRendering(true);
					return true;
				}

				public void reset (Source source, Payload payload) {
					getActor().setColor(Color.WHITE);
				}
				public void drop (Source source, Payload payload, float x, float y, int pointer) {
					boolean jumpedOver = false;
					boolean tokenInWay = false;
					for(int i = 8; i < Math.abs(target); i+=8){
						if(assets.get(field.getIndex() + checkifNegativ(target, i)).getToken() != null){
							if(assets.get(field.getIndex() + checkifNegativ(target, i)).getToken().getPlayer().getID() != player.getID()){
								jumpOver(assets.get(field.getIndex() + checkifNegativ(target, i) + checkifNegativ(target, 8)),
								field, assets.get(field.getIndex() + checkifNegativ(target, i)));
								System.out.println("jumpedOver");
								jumpedOver = true;
							}
							tokenInWay = true;
						}
						}
					System.out.println(jumpedOver +" " + tokenInWay);
					if(!jumpedOver && !tokenInWay){
							move(assets.get(field.index + target), field);
						
					}
					Gdx.graphics.setContinuousRendering(false);
				}
			});
		}
	}
	
	public int checkifNegativ(int value, int returnValue){
		if(value >= 0){
			return returnValue;
		}else{
			return (returnValue * -1);
		}
	}
	
	@Override
	public void movement(ArrayList<Asset> assets){
		dragAndDrop.clear();
		final Image imgOrigin = new Image(new Texture(pfad));
		imgOrigin.setBounds(0, 0, DameMain.WIDTH / 8, DameMain.HEIGHT / 8);
		
		final Image imgValid = new Image(new Texture(pfad));
		imgValid.setBounds(0, 0, DameMain.WIDTH / 8, DameMain.HEIGHT / 8);
		dragAndDrop.addSource(new Source(this) {
			public Payload dragStart (InputEvent event, float x, float y, int pointer) {
				Gdx.graphics.setContinuousRendering(true);
				Payload payload = new Payload();
				payload.setObject(imgOrigin);
				payload.setDragActor(imgOrigin);
				payload.setValidDragActor(imgValid);
				this.getActor().setVisible(false);

				return payload;
			}
			
			public void dragStop (InputEvent event, float x, float y, int pointer, Payload payload, Target target) {
				this.getActor().setVisible(true);
				Gdx.graphics.setContinuousRendering(false);
			}
		});
		int xValue = super.getField().getIndex() % 8;
		System.out.println(xValue);
		int yValue = super.getField().getIndex() / 8;
		System.out.println(yValue);
		for(int i = 0; i < xValue; i++){
			addTargetHorizontal(assets, -i, this.getPlayer());
			System.out.println(0);
		}
		for(int i = 1; i < 8 - xValue; i++){
			addTargetHorizontal(assets, i, this.getPlayer());
			System.out.println(1);
		}
		for(int i = 0; i < 56 - (yValue * 8); i++){
			addTargetVertical(assets, i * 8, this.getPlayer());
		}
		for(int i = 0; i < yValue * 8; i++){
			addTargetVertical(assets, -i * 8, this.getPlayer());
		}
	}
}
