package com.mygdx.game.main.entity;

import com.mygdx.game.main.maze.Maze;
import com.mygdx.game.main.maze.Tile;

public abstract class Entity {

    protected final Maze maze;

    protected Tile tile;

    public Entity(final Maze maze, Tile tile)
    {
        this.maze = maze;
        this.tile = tile;
    }

    abstract public void update(float delta);

    abstract public void render(float delta);

    abstract public void dispose();

    public Maze getMaze()
    {
        return this.maze;
    }

    public Tile getTile()
    {
        return this.tile;
    }

    public void setTile(Tile tile)
    {
        this.tile = tile;
    }

    public void setTile(int row, int col)
    {
        if(this.maze.isWithinBounds(row, col))
        {
            this.tile = this.maze.getTile(row, col);
        }
    }
}
