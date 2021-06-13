package com.mygdx.game.main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FillViewport;

public class MazeGame extends Game {
	public static SpriteBatch batch;

	public static ShapeRenderer shape;

	public static BitmapFont font;

	public static OrthographicCamera camera;

	public static FillViewport viewport;

	@Override
	public void create ()
	{
		batch = new SpriteBatch();
		shape = new ShapeRenderer();
		//font = new BitmapFont(true);
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Sans.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = 16;
		parameter.flip =true;
		font = generator.generateFont(parameter);

		generator.dispose();

		camera = new OrthographicCamera();
		camera.setToOrtho(true, Constants.LOOK_WIDTH, Constants.LOOK_HEIGHT);
		viewport = new FillViewport(Constants.LOOK_WIDTH, Constants.LOOK_HEIGHT, camera);

		this.setScreen(new GameScreen());
	}

	@Override
	public void render ()
	{
		super.render();
	}

	@Override
	public void resize(int width, int height)
	{
		super.resize(width, height);

	}

	@Override
	public void dispose ()
	{
		batch.dispose();
		shape.dispose();
		font.dispose();
	}
}
