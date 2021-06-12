package com.mygdx.game.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;



public class GameScreen implements Screen {
    private MazeFactory factory;

    private Maze maze;

    private GenerationType genType;

    private float timeSeconds = 0f;

    private int mazeHeight;

    private int mazeWidth;

    private EntityManager manager;

    private EntityPlayer player;
    

    SpriteBatch renderer;

    BitmapFont font;

    //private GameUI ui;


    public GameScreen()
    {
        this.initMaze();
        this.initEntities();

        // this.ui = new GameUI(this);
    }

    @Override
    public void render(float delta)
    {
        if(maze != null)
        {
            renderer = new SpriteBatch();
            
            FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Sans.ttf"));
            FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
            parameter.size = 16;
            font = generator.generateFont(parameter);
		    generator.dispose();    

            RenderUtil.clearScreen(this.maze.getDrawer().getBgColor());
            RenderUtil.updateCamera();

            //Update Entities
            manager.update(delta);
            //ui.act(delta);

            //Draw Maze and Entities
            maze.render();
            manager.render(delta);

            //Draw UI
            //ui.getViewport().apply();
            //ui.draw();
            MazeGame.batch.begin();
            MazeGame.font.draw(MazeGame.batch,"Laiks:"+timeSeconds,100,100);
            MazeGame.batch.end();

            timeSeconds += Gdx.graphics.getDeltaTime();

            //Check if player has won
            this.checkAndHandlePlayerWin();

            //Regenerate the Maze
            if(Gdx.input.isKeyJustPressed(Input.Keys.R))
            {
                this.createMaze( this.genType, this.mazeWidth, this.mazeHeight);
            }
        }
        else
        {
            this.createMaze( this.genType, this.mazeWidth, this.mazeHeight);
        }
    }

    private void initMaze()
    {
        this.factory = new MazeFactory();
        this.maze = null;

        this.genType = GenerationType.RECURSIVE_BACKTRACK;
        this.mazeWidth = Constants.MAZE_WIDTH;
        this.mazeHeight = Constants.MAZE_HEIGHT;
    }

    private void initEntities()
    {
        this.manager = new EntityManager();
        this.player = null;
    }

    public void createMaze(GenerationType genType, int width, int height)
    {
        if(this.maze != null)
        {
            this.manager.getEntities().remove(this.player);
            this.maze = null;
            this.player = null;
        }
        else
        {
            this.maze = factory.getMaze( genType, width, height);
            this.player = new EntityPlayer(this.maze, this.maze.getEntrance());

            this.manager.addEntity(player);
        }
    }

    private void checkAndHandlePlayerWin()
    {
        if(this.player.getTile().getRow() == this.maze.getExit().getRow()
                && this.player.getTile().getColumn() == this.maze.getExit().getColumn())
        {

            Gdx.gl.glClearColor(0, .25f, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            final float clearTime = timeSeconds;
            renderer.begin();
            font.draw(renderer, "You win!", Gdx.graphics.getWidth()*.25f, Gdx.graphics.getHeight() * .75f);
            font.draw(renderer, "Press enter to restart.", Gdx.graphics.getWidth()*.25f, Gdx.graphics.getHeight() * .25f);
            font.draw(renderer, "Your completion time :"+clearTime,  Gdx.graphics.getWidth()*.25f, Gdx.graphics.getHeight() * .50f);
            
            renderer.end();
            //TODO Beigt speletaja kustibu kaut ka, (Disable player?)
            //TODO apturet laika iteraciju
            player.dispose();
            if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER))
            {
                this.createMaze(this.genType, this.mazeWidth, this.mazeHeight);
            }
           
        }
    }

    @Override
    public void resize(int width, int height)
    {
        //this.ui.getViewport().update(width, height);
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
    public void show()
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
