package com.mygdx.game.main.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.main.maze.Maze;
import com.mygdx.game.main.maze.MazeDrawer;
import com.mygdx.game.main.driver.MazeGame;
import com.mygdx.game.main.maze.Tile;

import java.util.ArrayList;

public class EntityPlayer extends Entity {

    public EntityPlayer(Maze maze, Tile tile)
    {
        super(maze, tile);
    }
    public boolean moving = true;

    @Override
    public void update(float delta)
    {
        if(moving) {
            this.handlePlayerMovement();
            this.trackPlayerMovement();
        }
    }

    @Override
    public void render(float delta)
    {
        this.drawPlayer();
    }

    private void handlePlayerMovement()
    {
        ArrayList<Tile> neighbors = this.maze.getNeighbors(tile);
        if(Gdx.input.isKeyJustPressed(Input.Keys.W)) // UP MOVEMENT
        {
            if(neighbors.get(0) != null && this.maze.isPathClear(tile, neighbors.get(0)))
            {
                changeDirection(2);
                this.setTile(neighbors.get(0));

            }
        }
        else if(Gdx.input.isKeyJustPressed(Input.Keys.A)) // LEFT MOVEMENT
        {
            if(neighbors.get(3) != null && this.maze.isPathClear(tile, neighbors.get(3)))
            {
                changeDirection(3);
                this.setTile(neighbors.get(3));

            }
        }
        else if(Gdx.input.isKeyJustPressed(Input.Keys.S)) // DOWN MOVEMENT
        {
            if(neighbors.get(2) != null && this.maze.isPathClear(tile, neighbors.get(2)))
            {
                changeDirection(1);
                this.setTile(neighbors.get(2));

            }
        }
        else if(Gdx.input.isKeyJustPressed(Input.Keys.D)) // RIGHT MOVEMENT
        {
            if(neighbors.get(1) != null && this.maze.isPathClear(tile, neighbors.get(1)))
            {
                changeDirection(4);
                this.setTile(neighbors.get(1));

            }
        }
    }

    private void changeDirection(int direction)
    {
        switch(direction){
            case 1:
                MazeGame.active_sprite = MazeGame.sprite_down;
                break;
            case 2:
                MazeGame.active_sprite = MazeGame.sprite_up;
                break;
            case 3:
                MazeGame.active_sprite = MazeGame.sprite_left;
                break;
            case 4:
                MazeGame.active_sprite = MazeGame.sprite_right;
                break;
            default: break;
        }
        MazeDrawer drawer = (MazeDrawer) this.maze.getDrawer();
        MazeGame.batch.begin();
        MazeGame.active_sprite.draw(MazeGame.batch);

        MazeGame.active_sprite.setSize( drawer.getTileSize() , drawer.getTileSize());
        MazeGame.active_sprite.setPosition(this.maze.getDrawer().getTileCenterX(this.tile.getRow(), this.tile.getColumn()) - drawer.getTileSize() /2.5f,
                this.maze.getDrawer().getTileCenterY(this.tile.getRow(), this.tile.getColumn()) - drawer.getTileSize() / 2.25f
        );
        MazeGame.batch.end();


    }

    private void trackPlayerMovement()
    {
        float x = this.maze.getDrawer().getTileCenterX(this.tile.getRow(), this.tile.getColumn());
        float y = this.maze.getDrawer().getTileCenterY(this.tile.getRow(), this.tile.getColumn());

        MazeGame.camera.position.lerp(new Vector3((int) x, (int) y, 0), 0.1f);
    }

    private void drawPlayer()
    {

        MazeDrawer drawer = (MazeDrawer) this.maze.getDrawer();

        MazeGame.batch.begin();
        MazeGame.active_sprite.draw(MazeGame.batch);

        MazeGame.active_sprite.setSize( drawer.getTileSize() , drawer.getTileSize());
        MazeGame.active_sprite.setPosition(this.maze.getDrawer().getTileCenterX(this.tile.getRow(), this.tile.getColumn()) - drawer.getTileSize() /2.5f,
                this.maze.getDrawer().getTileCenterY(this.tile.getRow(), this.tile.getColumn()) - drawer.getTileSize() / 2.25f
        );

        MazeGame.batch.end();
    }


    @Override
    public void dispose()
    {

    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }
}
