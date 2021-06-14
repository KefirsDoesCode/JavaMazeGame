package com.mygdx.game.main.maze;

public class Tile {

    private final Maze maze;

    private final int row;

    private final int column;

    private final int sides = 4;

    private boolean[] walls;

    private boolean visited;

    private boolean isEntrance;

    private boolean isExit;


    public Tile(Maze maze, int row, int column)
    {
        this.maze = maze;

        this.row = row;
        this.column = column;

        this.walls = new boolean[this.sides];

        this.visited = false;
        this.isEntrance = false;
        this.isExit = false;

        this.initializeWalls();
    }

    private void initializeWalls()
    {
        for(int i = 0; i < this.sides; i++)
        {
            this.walls[i] = true;
        }
    }


    public Maze getMaze()
    {
        return maze;
    }

    public int getRow()
    {
        return this.row;
    }

    public int getColumn()
    {
        return this.column;
    }

    public int getSides()
    {
        return sides;
    }

    public boolean[] getWalls()
    {
        return this.walls;
    }

    public void setWalls(boolean[] walls)
    {
        this.walls = walls;
    }

    public void setWall(int index, boolean wall)
    {
        this.walls[index] = wall;
    }

    public boolean isVisited()
    {
        return this.visited;
    }

    public void setVisited(boolean visited)
    {
        this.visited = visited;
    }

    public boolean isEntrance()
    {
        return isEntrance;
    }

    public void setEntrance(boolean isEntrance)
    {
        this.isEntrance = isEntrance;
        this.isExit = false;
    }

    public boolean isExit()
    {
        return isExit;
    }

    public void setExit(boolean isExit)
    {
        this.isExit = isExit;
        this.isEntrance = false;
    }
}
