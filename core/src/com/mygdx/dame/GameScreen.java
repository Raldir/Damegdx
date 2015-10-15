package com.mygdx.dame;

import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldListener;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class GameScreen implements Screen {

	private static DameMain game;
	private OrthographicCamera camera;
	
	private static Board board = new Board();
	private static ArrayList<Token> tr = new ArrayList<Token>();
	
	protected static Player[] players = new Player[2];
	protected static Stage stage;
	protected static TextField[] textFieldL = new TextField[4];
	
	public GameScreen(DameMain game){
		Gdx.graphics.setContinuousRendering(false);
		GameScreen.game = game;
		
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		
		camera = new OrthographicCamera();
		camera.setToOrtho(true, 800, 600);
		
		for(Image a: board.getAssets()){
			stage.addActor(a);
		}

		Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
		Window w = new Window("Information and Control Table", skin);
		w.setBounds(0, DameMain.HEIGHT + DameMain.HEIGHTINTERFACE, DameMain.WIDTH, DameMain.HEIGHTINTERFACE);
		w.setMovable(false);
		initTextBoxes(w);
		stage.addActor(w);
		tr = initTokens();
		for(Token t : tr){
			stage.addActor(t);
		}
		updateTokenPosition();
		Gdx.graphics.requestRendering();
	}
	
	public static void endgame(int ID){
		if(ID == 0){
			game.setScreen(new EndScreen(game, 2));
		}else{
			game.setScreen(new EndScreen(game, 1));
		}
	}
	
	public static void updateTextFieldList(String newText, int index){
		char[] alp = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H','I'};
		String[] textList = new String[4];
		for(int i = 1; i < 4; i++){
			textList[i] = textFieldL[i].getText();
			if(i > 1){
				textFieldL[i].setText(textList[i - 1]);
			}else{
				textFieldL[i].setText(newText + " " + alp[index / 8] + index % 8);
			}
		}

	}
	
	public void initTextBoxes(Window w){
		for(int i = 0; i < 4; i++){
			textFieldL[i] = new TextField(" " , new Skin(Gdx.files.internal("uiskin.json")));
			textFieldL[i].setBounds(0,(DameMain.HEIGHTINTERFACE / 5) * i, DameMain.WIDTH, (DameMain.HEIGHTINTERFACE / 5));
			w.addActor(textFieldL[i]);
			if(i != 0){
				textFieldL[i].setDisabled(true);
			}else{
				textFieldL[i].setColor(Color.GREEN);
				textFieldL[i].setText("If you want to restart type: restart, to get help type: help");				
				}
				textFieldL[i].addListener(new ClickListener(){
					
					public void clicked(InputEvent event, float x, float y){
						textFieldL[0].setText(" ");
					}
				});
				textFieldL[i].setTextFieldListener(new TextFieldListener() {
					
					@Override
					public void keyTyped(TextField textField, char c) {
						if(Integer.valueOf(c) == 3){
							textField.setText(" ");
						}
						if(Integer.valueOf(c) == 13){
							if(textField.getText().contains("restart")){
								game.reset();
							}
							if(textField.getText().contains("help")){
								Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
								final Dialog dialog = new Dialog("Help", skin);
								dialog.setBounds(0, DameMain.MAXHEIGHT / 3, DameMain.WIDTH * 0.88f, DameMain.MAXHEIGHT / 5f);
								dialog.add(("Hello. This game is called Dame (german name). The game rule is pretty simple.\n"
				+ "You can move each turn one of your Token one field forward. If an enemy Token stands\n"
				+ "one field ahead of you, you can jump over it, and destroy it. If you are able to\n"
				+ "get to the first field of the enemy Player, your Token will transform to a `Dame`.\n"
				+ "The last Player who is still has a Token and / or a Dame wins."));
								Button button = new Button();
								button.addListener(new ClickListener() {
								    public void clicked(InputEvent event, float x, float y) {
								    	dialog.remove();
								    }
								});
								dialog.button("close", button);
								stage.addActor(dialog);
							}
						}
						
					}
				});
			}
	}

	public ArrayList<Token> initTokens(){
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
	int[] iter = {0, 1, -1, 8 , -8};
		for(int i = 0 ; i < iter.length; i++){
			if(tr.getField().getIndex() + iter[i] >= 0 && tr.getField().getIndex() + iter[i] < 64)
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
			if(tr.getField().getIndex() + iter[i] >= 0 && tr.getField().getIndex() + iter[i] < 64)
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
