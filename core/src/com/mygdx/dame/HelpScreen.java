package com.mygdx.dame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class HelpScreen implements Screen{
	
	SpriteBatch batch = new SpriteBatch();
	Stage s = new Stage();
	BitmapFont font;
	DameMain game;

	public HelpScreen(DameMain game){
		this.game = game;
		ButtonActor ba = new ButtonActor("images/ButtenStart.png", 175, 100, game);
		font = new BitmapFont();
		s.addActor(ba);
	}
	@Override
	public void show() {
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1F, 1F, 1F, 1F);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
		batch.begin();
		font.draw(batch, "Hello. This game is called Dame (german name). The game rule is pretty simple.\n"
				+ "You can move each turn one of your Token one field forward. If an enemy Token stands\n"
				+ "one field ahead of you, you can jump over it, and destroy it. If you are able to\n"
				+ "get to the first field of the enemy Player, your Token will transform to a `Dame`.\n"
				+ "The last Player who is still has a Token and / or a Dame wins."
	, DameMain.WIDTH / 5, DameMain.MAXHEIGHT / 2);
		batch.end();
		s.act();
		s.draw();
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
