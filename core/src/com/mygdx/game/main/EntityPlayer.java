package com.mygdx.game.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;

public class EntityPlayer extends Entity {
    public EntityPlayer(Maze maze, Tile tile)
    {
        super(maze, tile);
    }

    @Override
    public void update(float delta)
    {
        this.handlePlayerMovement();
        this.trackPlayerMovement();
    }

    @Override
    public void render(float delta)
    {
        this.drawPlayer();
    }

    private void handlePlayerMovement()
    {
        this.handleSquarePlayerMovement();

    }

    private void handleSquarePlayerMovement()
    {
        ArrayList<Tile> neighbors = this.maze.getNeighbors(tile);
        if(Gdx.input.isKeyJustPressed(Input.Keys.W)) // NORTH MOVEMENT
        {
            if(neighbors.get(0) != null && this.maze.isPathClear(tile, neighbors.get(0)))
            {
                this.setTile(neighbors.get(0));
            }
        }
        else if(Gdx.input.isKeyJustPressed(Input.Keys.A)) // WEST MOVEMENT
        {
            if(neighbors.get(3) != null && this.maze.isPathClear(tile, neighbors.get(3)))
            {
                this.setTile(neighbors.get(3));
            }
        }
        else if(Gdx.input.isKeyJustPressed(Input.Keys.S)) // SOUTH MOVEMENT
        {
            if(neighbors.get(2) != null && this.maze.isPathClear(tile, neighbors.get(2)))
            {
                this.setTile(neighbors.get(2));
            }
        }
        else if(Gdx.input.isKeyJustPressed(Input.Keys.D)) // EAST MOVEMENT
        {
            if(neighbors.get(1) != null && this.maze.isPathClear(tile, neighbors.get(1)))
            {
                this.setTile(neighbors.get(1));
            }
        }
    }

    private void trackPlayerMovement()
    {
        float x = this.maze.getDrawer().getTileCenterX(this.tile.getRow(), this.tile.getColumn());
        float y = this.maze.getDrawer().getTileCenterY(this.tile.getRow(), this.tile.getColumn());

        MazeGame.camera.position.lerp(new Vector3((int) x, (int) y, 0), 0.15f);
    }

    private void drawPlayer()
    {
        this.drawSquareMazePlayer();
    }

    private void drawSquareMazePlayer()
    {
        MazeDrawerSquare drawer = (MazeDrawerSquare) this.maze.getDrawer();

        MazeGame.shape.begin(ShapeRenderer.ShapeType.Filled);
        MazeGame.shape.setColor(this.maze.getDrawer().getPlayerColor());
        MazeGame.shape.rect(this.maze.getDrawer().getTileCenterX(this.tile.getRow(), this.tile.getColumn()) - drawer.getTileSize() / 6,
                this.maze.getDrawer().getTileCenterY(this.tile.getRow(), this.tile.getColumn()) - drawer.getTileSize() / 6,
                drawer.getTileSize() / 2,
                drawer.getTileSize() / 2);
        MazeGame.shape.end();
    }


    @Override
    public void dispose()
    {
        
    }
}
