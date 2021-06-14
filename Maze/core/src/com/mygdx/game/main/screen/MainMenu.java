package com.mygdx.game.main.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.main.constants.Constants;
import com.mygdx.game.main.driver.MazeGame;
import com.mygdx.game.main.enumer.Difficulty;

public class MainMenu implements Screen {

    final MazeGame game;
    final Stage stage;
    Viewport viewport;
    protected Skin skin;

    OrthographicCamera camera;

    public MainMenu(final MazeGame game){
        this.game = game;

        TextureAtlas buttonAtlas = new TextureAtlas("uiskin.atlas");
        skin = new Skin(Gdx.files.internal("uiskin.json"), buttonAtlas);

        camera = new OrthographicCamera();
        camera.position.set(Constants.SCREEN_X,Constants.SCREEN_Y, 0);

        viewport = new FitViewport(Constants.LOOK_WIDTH, Constants.LOOK_HEIGHT,camera);
        viewport.apply();

        stage = new Stage(viewport, MazeGame.batch);

    }
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

        Table mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.top();

        TextButton playButtonEasy = new TextButton("Play - easy peasy lemon squeazy", skin);
        TextButton playButtonMedium = new TextButton("Play - not as easy peasy but still lemon squeazy", skin);
        TextButton playButtonHard = new TextButton("Play - hard hard lemon hard", skin);
        TextButton exitButton = new TextButton("Exit", skin);

        mainTable.center();
        mainTable.add(playButtonEasy);
        mainTable.row();
        mainTable.add(playButtonMedium);
        mainTable.row();
        mainTable.add(playButtonHard);
        mainTable.row();
        mainTable.add(exitButton);

        stage.addActor(mainTable);

        playButtonEasy.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game)Gdx.app.getApplicationListener()).setScreen(new GameScreen(game,Difficulty.EASY));
                dispose();
            }
        });
        playButtonMedium.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game)Gdx.app.getApplicationListener()).setScreen(new GameScreen(game,Difficulty.MEDIUM));
                dispose();
            }
        });
        playButtonHard.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game)Gdx.app.getApplicationListener()).setScreen(new GameScreen(game,Difficulty.HARD));
                dispose();
            }
        });
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0,0,0.2f,1);

        camera.update();
        MazeGame.batch.setProjectionMatrix(camera.combined);

        stage.act();
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
