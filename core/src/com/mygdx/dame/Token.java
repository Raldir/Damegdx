package com.mygdx.dame;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;

public class Token extends Image {

	Asset field;
	DragAndDrop dragAndDrop = new DragAndDrop();
	Player player;
	String pfad;

	public Token(String pfad, ArrayList<Asset> as, int index){
		super(createTexture(pfad));
		super.setWidth(DameMain.WIDTH / 8);
		super.setHeight((DameMain.HEIGHT / 8));
		this.pfad = pfad;
		move(as.get(index));
	}
	
	public Actor getField(){
		return field;
	}
	
	public void setPlayer(Player p){
		player = p;
	}
	
	public Player getPlayer(){
		return player;
	}
	
	public void move(Asset field){
		this.field = field;
		field.setToken(this);
		setPosition(field.getX(), field.getY());
		setVisible(true);
	}
	
	public void move(Asset field, Asset prevField){
		if(player.getPlayerCanMove()){
			this.field = field;
			this.field.setToken(this);
			prevField.setToken(null);
			setPosition(field.getX(), field.getY());
			setVisible(true);
			player.setPlayerCanMove(false);
			if(player.getID() == 0){
				GameScreen.players[1].setPlayerCanMove(true);
			}else{
				GameScreen.players[0].setPlayerCanMove(true);
			}
		}
	}
	
	public void jumpOver(Asset field, Asset prevField, Asset jumpOverField){
		if(player.getPlayerCanMove()){
			this.field = field;
			this.field.setToken(this);
			prevField.setToken(null);
			setPosition(field.getX(), field.getY());
			jumpOverField.removeToken();
			setVisible(true);
		}
	}
	
	public void addTarget(final ArrayList<Asset> assets, final int target, Player p){
		if(field.getIndex() + target + target >= 0 &&field.getIndex() + target + target < assets.size() && assets.get(field.getIndex() + target).getToken() != null && assets.get(field.getIndex() + target).getToken().getPlayer() != this.player){
			dragAndDrop.addTarget(new Target(assets.get(field.getIndex() + target + target)){
				public boolean drag (Source source, Payload payload, float x, float y, int pointer) {
					getActor().setColor(Color.GREEN);
					Gdx.graphics.setContinuousRendering(true);
					return true;
				}

				public void reset (Source source, Payload payload) {
					getActor().setColor(Color.WHITE);
				}
				public void drop (Source source, Payload payload, float x, float y, int pointer) {
					jumpOver(assets.get(field.getIndex() + target + target), field, assets.get(field.getIndex() + target));
					GameScreen.updateTokenPosition();
					Gdx.graphics.setContinuousRendering(false);
				}
			});
		}
	}
	
	public void setGenerellMovement(Player p, final ArrayList<Asset> assets){
		if(this.player.getID() == 0){
			if(field.getIndex() + 8 < assets.size() && assets.get(field.getIndex() + 8).getToken() == null){
				dragAndDrop.addTarget(new Target(assets.get(field.getIndex() + 8)) {
					public boolean drag (Source source, Payload payload, float x, float y, int pointer) {
						getActor().setColor(Color.GREEN);
						Gdx.graphics.setContinuousRendering(true);
						return true;
					}
					public void reset (Source source, Payload payload) {
						getActor().setColor(Color.WHITE);
					}
	
					public void drop (Source source, Payload payload, float x, float y, int pointer) {
						move(assets.get(field.getIndex() + 8), field);
						GameScreen.updateTokenPosition();
						Gdx.graphics.setContinuousRendering(false);
					}
				});
			}
		}else{
				if(field.getIndex() - 8 >= 0 && assets.get(field.getIndex() - 8).getToken() == null){
					dragAndDrop.addTarget(new Target(assets.get(field.getIndex() - 8)) {
						public boolean drag (Source source, Payload payload, float x, float y, int pointer) {
							Gdx.graphics.setContinuousRendering(true);
							getActor().setColor(Color.GREEN);
							return true;
						}
						public void reset (Source source, Payload payload) {
							getActor().setColor(Color.WHITE);
						}
		
						public void drop (Source source, Payload payload, float x, float y, int pointer) {
							move(assets.get(field.getIndex() - 8), field);
							GameScreen.updateTokenPosition();
							Gdx.graphics.setContinuousRendering(false);
						}
					});
				}
			}
	}
	
	
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
			setGenerellMovement(player, assets);
			int[] values = {1, -1 ,8,-8};
			for(int i = 0; i < 4; i++){
				addTarget(assets, values[i], player);
			}
	}
	
	
	private static Texture createTexture(String pfad){
		Texture texture_back = new Texture(Gdx.files.internal(pfad));
		texture_back.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		return texture_back;
	}
	
}

