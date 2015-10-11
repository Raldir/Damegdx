package com.mygdx.dame;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class EndScreen implements Screen {

	
	private DameMain game;
	private OrthographicCamera camera;
	
	protected static Stage stage;
	
	public EndScreen(DameMain game, int playerlost){
		Gdx.graphics.setContinuousRendering(false);
		this.game = game;
		
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		
		Image mainPicture = new Image(new Texture("images/MainEndPicture.png"));
		mainPicture.setBounds(0, 0, DameMain.WIDTH, DameMain.HEIGHT);
		Texture temp = new Texture("images/Player" + (playerlost+1) + ".png");
		Image player = new Image(temp);
		player.setBounds(DameMain.WIDTH * 0.35f, DameMain.HEIGHT * 0.733f, temp.getWidth() * (DameMain.WIDTH / 1920f), temp.getHeight() * (DameMain.HEIGHT / 1080f));
		System.out.println((DameMain.WIDTH / 1920f));
		camera = new OrthographicCamera();
		camera.setToOrtho(true, 800, 600);
		stage.addActor(mainPicture);
		stage.addActor(player);
		
		Gdx.graphics.requestRendering();
	}
	
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
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