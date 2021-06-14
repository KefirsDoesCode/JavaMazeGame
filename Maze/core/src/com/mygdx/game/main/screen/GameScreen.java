package com.mygdx.game.main.screen;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.game.main.constants.Constants;
import com.mygdx.game.main.driver.MazeGame;
import com.mygdx.game.main.enumer.Difficulty;
import com.mygdx.game.main.enumer.GenerationType;
import com.mygdx.game.main.entity.EntityPlayer;
import com.mygdx.game.main.maze.Maze;
import com.mygdx.game.main.maze.MazeAlgorithms;

public class GameScreen implements Screen {
    private MazeAlgorithms factory;

    MazeGame game;

    private Maze maze;

    private final Difficulty difficulty;

    private GenerationType genType;

    private float timeSeconds = 0f;

    private int mazeHeight;

    private int mazeWidth;

    private EntityPlayer player;

    private boolean isCounting = true;


    public GameScreen(MazeGame game,Difficulty difficulty)
    {
        this.game = game;
        this.difficulty = difficulty;
        this.initMaze();
        this.initEntities();

    }

    @Override
    public void render(float delta)
    {
        if(maze != null)
        {

            clearScreen(this.maze.getDrawer().getBgColor());
            updateCamera();

            //Update Entities
            player.update(delta);

            //Draw Maze and Entities
            maze.render();
            player.render(delta);

            //Time stuff
            MazeGame.batch.begin();
            MazeGame.font.setColor(Color.CYAN);
            MazeGame.font.draw(MazeGame.batch, "Laiks:" + timeSeconds,
                    getPlayerX()-Gdx.graphics.getWidth()/2.05f,
                    getPlayerY()-Gdx.graphics.getHeight()/2.05f);
            MazeGame.batch.end();

            if (isCounting)
                timeSeconds += Gdx.graphics.getDeltaTime();

            //Check if player has won
            this.checkAndHandlePlayerWin();


            //Regenerate the Maze
            if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
                this.createMaze(this.genType, this.mazeWidth, this.mazeHeight);
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
                game.setScreen(new MainMenu(game));

            }



        }
        else
        {
            gameStart();
        }
    }
    @Override
    public void show()
    {

    }
    private void gameStart(){

        if (difficulty == Difficulty.EASY) {
            timeSeconds = 0f;
            isCounting = true;
            this.createMaze(this.genType, this.mazeWidth, this.mazeHeight);
        }
        if (difficulty == Difficulty.MEDIUM) {
            timeSeconds = 0f;
            isCounting = true;
            this.createMaze(this.genType, this.mazeWidth * 2, this.mazeHeight * 2);
        }
        if (difficulty == Difficulty.HARD) {
            timeSeconds = 0f;
            isCounting = true;
            this.createMaze(this.genType, this.mazeWidth * 10, this.mazeHeight * 10);
        }

    }
    private void initMaze()
    {
        this.factory = new MazeAlgorithms();
        this.maze = null;

        this.genType = GenerationType.RECURSIVE_BACKTRACK;
        this.mazeWidth = Constants.MAZE_WIDTH;
        this.mazeHeight = Constants.MAZE_HEIGHT;
    }

    private void initEntities()
    {
        this.player = null;
    }

    public void createMaze(GenerationType genType, int width, int height)
    {
        if(this.maze != null)
        {

            this.maze = null;
            this.player = null;
        }
        else
        {
            this.maze = factory.getMaze( genType, width, height);
            this.player = new EntityPlayer(this.maze, this.maze.getEntrance());

        }
    }

    private void checkAndHandlePlayerWin()
    {
        if(this.player.getTile().getRow() == this.maze.getExit().getRow()
                && this.player.getTile().getColumn() == this.maze.getExit().getColumn())
        {
            isCounting = false;
            Gdx.gl.glClearColor(0, .25f, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            final float clearTime = timeSeconds;
            player.setMoving(false);

            MazeGame.batch.begin();
            MazeGame.camera.setToOrtho(true,Constants.LOOK_WIDTH,Constants.LOOK_HEIGHT);
            MazeGame.font.draw(MazeGame.batch, "You win!", Gdx.graphics.getWidth()/2f, Gdx.graphics.getHeight()/4f);
            MazeGame.font.draw(MazeGame.batch, "Your completion time :"+clearTime+"s",  Gdx.graphics.getWidth()/4.1f, Gdx.graphics.getHeight()/2.5f);
            MazeGame.font.setColor(Color.BROWN);
            MazeGame.font.draw(MazeGame.batch, "Press enter to restart.", Gdx.graphics.getWidth()/3f, Gdx.graphics.getHeight()/1.8f);
            MazeGame.font.draw(MazeGame.batch, "Escape to main menu.", Gdx.graphics.getWidth()/3f, Gdx.graphics.getHeight()/1.5f);
            MazeGame.batch.end();

            player.dispose();
            if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER))
            {
                this.createMaze(this.genType, this.mazeWidth, this.mazeHeight);
            }
        }
    }
    private float getPlayerX(){
        return player.getMaze().getDrawer().getTileCenterX(player.getTile().getRow(), player.getTile().getColumn());
    }

    private float getPlayerY(){
        return player.getMaze().getDrawer().getTileCenterY(player.getTile().getRow(),player.getTile().getColumn());
    }

    public static void clearScreen(Color color)
    {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
    public static void updateCamera()
    {
        MazeGame.camera.update(true);
        MazeGame.viewport.apply();
        MazeGame.batch.setProjectionMatrix(MazeGame.camera.combined);
        MazeGame.shape.setProjectionMatrix(MazeGame.camera.combined);
    }
    @Override
    public void resize(int width, int height)
    {

        MazeGame.viewport.update(width, height, true);
        MazeGame.camera.update();
    }

    @Override
    public void dispose()
    {

    }

    @Override
    public void pause()
    {

    }

    @Override
    public void resume()
    {

    }



    @Override
    public void hide()
    {

    }

    public Maze getMaze()
    {
        return maze;
    }

    public void setMaze(Maze maze)
    {
        this.maze = maze;
    }

    public GenerationType getGenType()
    {
        return genType;
    }

    public void setGenType(GenerationType genType)
    {
        this.genType = genType;
    }

    public int getMazeHeight()
    {
        return mazeHeight;
    }

    public void setMazeHeight(int mazeHeight)
    {
        this.mazeHeight = mazeHeight;
    }

    public int getMazeWidth()
    {
        return mazeWidth;
    }

    public void setMazeWidth(int mazeWidth)
    {
        this.mazeWidth = mazeWidth;
    }
}
