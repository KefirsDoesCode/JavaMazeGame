package com.mygdx.game.main.driver;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.mygdx.game.main.constants.Constants;
import com.mygdx.game.main.screen.MainMenu;

public class MazeGame extends Game {
	public static SpriteBatch batch;

	public static ShapeRenderer shape;

	public static BitmapFont font;

	public static OrthographicCamera camera;

	public static FillViewport viewport;

	public static Texture texture_right;

	public static Texture texture_down;

	public static Texture texture_left;

	public static Texture texture_up;

	public static Sprite active_sprite;

	public static Sprite sprite_up;

	public static Sprite sprite_down;

	public static Sprite sprite_left;

	public static Sprite sprite_right;

	@Override
	public void create ()
	{
		batch = new SpriteBatch();
		shape = new ShapeRenderer();

		texture_right = new Texture(Gdx.files.internal("Right.png"));
		texture_down = new Texture(Gdx.files.internal("Down.png"));
		texture_left = new Texture(Gdx.files.internal("Left.png"));
		texture_up = new Texture(Gdx.files.internal("Up.png"));
		sprite_down = new Sprite(texture_down);
		sprite_up = new Sprite(texture_up);
		sprite_right = new Sprite(texture_right);
		sprite_left = new Sprite(texture_left);
		active_sprite = sprite_down;
		sprite_down.flip(false, true);
		sprite_right.flip(false, true);
		sprite_up.flip(false, true);
		sprite_left.flip(false, true);

		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Sans.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = 32;
		parameter.flip = true;
		font = generator.generateFont(parameter);
		generator.dispose();
		camera = new OrthographicCamera();
		camera.setToOrtho(true, Constants.SCREEN_X, Constants.SCREEN_Y);
		viewport = new FillViewport(Constants.LOOK_WIDTH, Constants.LOOK_HEIGHT, camera);

		this.setScreen(new MainMenu(this));
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
