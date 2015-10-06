package com.mygdx.dame;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;

public class GameScreen implements Screen {

	private DameMain game;
	private OrthographicCamera camera;
	
	private static Board board = new Board();
	private static ArrayList<Token> tr = new ArrayList<Token>();
	
	protected static Player[] players = new Player[2];
	protected static Stage stage;
	
	public GameScreen(DameMain game){
		Gdx.graphics.setContinuousRendering(false);
		this.game = game;
		
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		
		camera = new OrthographicCamera();
		camera.setToOrtho(true, 800, 600);
		
		for(Image a: board.getAssets()){
			stage.addActor(a);
		}
		
		tr = init();
		for(Token t : tr){
			stage.addActor(t);
		}
		updateTokenPosition();
		Gdx.graphics.requestRendering();
	}
	
	public ArrayList<Token> init(){
		ArrayList<Token> tokens = new ArrayList<Token>();
		players[0] = new Player("images/TokenWhite.png", 0, board.getAssets());
		players[1] = new Player("images/Token.png", 1, board.getAssets());
		for(int i = 0; i <players[0].getRemainingTokenCount(); i++){
			tokens.add(players[0].getRemainingToken().get(i));
			tokens.add(players[1].getRemainingToken().get(i));
		}
		return tokens;
		}

	public static void updateTokenPosition(){
		for(Token t : tr){
			t.movement(board.getAssets());
		}
	}
	
	public static void updateTokenPosition(Token tr){
	int[] iter = {0, 1, -1, 8 , -8, 16, -16};
		for(int i = 0 ; i < iter.length; i++){
			if(tr.getField().getIndex() + iter[i] > 0 && tr.getField().getIndex() + iter[i] < 64)
				board.getAssets().get(tr.getField().getIndex() + iter[i]).refreshToken(board.getAssets());
		}
	}
	
	@Override
	public void show() {
		
		
	}
	@Override
	public void render(float delta) {
		System.out.println("Rendering");
		Gdx.gl.glClearColor(1F, 1F, 1F, 1F);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
		camera.update();
		stage.act();
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		DameMain.HEIGHT = height;
		DameMain.WIDTH = width;
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	
}
