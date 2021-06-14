package com.mygdx.game.main.maze;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public abstract class Maze {

    protected final MazeColorRender drawer;

    protected final int rows;

    protected final int columns;

    protected Tile[][] maze;

    protected Tile entrance;

    protected Tile exit;

    public Maze(int rows, int columns)
    {

        this.drawer = new MazeDrawer(this);

        this.rows = rows;
        this.columns = columns;

        this.maze = new Tile[rows][columns];

        this.entrance = null;
        this.exit = null;

        this.populate();
    }


    abstract public void carvePath(Tile origin, Tile target);

    abstract public boolean isPathClear(Tile origin, Tile target);

    abstract public void setEntranceAndExit(Random random);

    abstract public ArrayList<Tile> getNeighbors(Tile tile);

    public void render()
    {
        this.drawer.render();
    }

    private void populate()
    {
        for(int i = 0; i < this.rows; i++)
        {
            for(int j = 0; j < this.columns; j++)
            {
                this.maze[i][j] = new Tile(this, i, j);
            }
        }
    }

    public boolean isWithinBounds(int row, int column)
    {
        if((row >= 0 && column >= 0) && (row < this.getRows() && column < this.getColumns()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public Tile getRandomNeighbor(Random random, Tile tile)
    {
        ArrayList<Tile> neighbors = this.getNeighbors(tile);

        neighbors.removeAll(Collections.singleton(null));

        return neighbors.get(random.nextInt(neighbors.size()));
    }

    public ArrayList<Tile> getUnvisitedNeighbors(Tile origin)
    {
        ArrayList<Tile> unvisited = new ArrayList<Tile>();

        for(Tile neighbor : this.getNeighbors(origin))
        {
            if(neighbor != null && !neighbor.isVisited())
            {
                unvisited.add(neighbor);
            }
        }

        return unvisited;
    }

    public Tile[][] getMaze()
    {
        return this.maze;
    }

    public void setMaze(Tile[][] maze)
    {
        this.maze = maze;
    }

    public int getRows()
    {
        return this.rows;
    }

    public int getColumns()
    {
        return this.columns;
    }

    public Tile getTile(int row, int col)
    {
        return this.maze[row][col];
    }

    public Tile getEntrance()
    {
        return this.entrance;
    }

    public Tile getExit()
    {
        return this.exit;
    }

    public MazeColorRender getDrawer()
    {
        return drawer;
    }
}
