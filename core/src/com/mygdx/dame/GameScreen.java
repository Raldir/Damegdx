package com.mygdx.dame;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class GameScreen implements Screen {

	private static DameMain game;
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

		Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
		TextField tf = new TextField("hello", skin);
		stage.addActor(tf);
		tr = init();
		for(Token t : tr){
			stage.addActor(t);
		}
		updateTokenPosition();
		Gdx.graphics.requestRendering();
	}
	
	public static void endgame(int ID){
		if(ID == 0){
			game.setScreen(new EndScreen(game, 1));
		}else{
			game.setScreen(new EndScreen(game, ID));
		}
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
	
	public static void updateDameTokenPosition(Token tr){
		int[] iter = new int[32];
		for(int i = 0; i < 32; i+=4){
			iter[i] = i;
			iter[i +1] = (i * -1);
			iter[i + 2] = (i * 8);
			iter [i + 3] = (i * -8);
		}
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
