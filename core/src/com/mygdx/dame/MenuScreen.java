package com.mygdx.dame;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class MenuScreen implements Screen {
	
	private DameMain game;
	private OrthographicCamera camera;
	
	protected static Stage stage;
	
	public MenuScreen(DameMain game){
		this.game = game;
		
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		
		camera = new OrthographicCamera();
		camera.setToOrtho(true, 1920, 1080);
		
		stage.addActor(new Asset("images/MenuMainPicture.png", 0, 0, DameMain.WIDTH, DameMain.HEIGHT));
		stage.addActor(new ButtonActor("images/ButtenStart.png", 175f, 100f, game));
		
	}

	@Override
	public void render(float delta){
		Gdx.gl.glClearColor(1F, 1F, 1F, 1F);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
		camera.update();
		stage.act();
		stage.draw();
		
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub	
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
	
	public DameMain getGame(){
		return game;
	}

}
