package com.mygdx.game.main;

public class Tile {

    /**The Maze object that the Tile belongs to.*/
    private final Maze maze;

    /**The row position of the Tile within the maze.*/
    private final int row;

    /**The column position of the Tile within the maze.*/
    private final int column;

    /**The number of sides that the Tile has.*/
    private final int sides;

    /**A boolean[] representing the state of the Tile's walls.*/
    private boolean[] walls;

    /**True when the Tile has been visited. Otherwise false.*/
    private boolean visited;

    /**True when the Tile is an entrance. Otherwise false.*/
    private boolean isEntrance;

    /**True when the Tile is an exit. Otherwise false.*/
    private boolean isExit;

    /**
     * Creates a new Tile object.
     * @param maze The Maze the Tile belongs to.
     * @param row The Tile's row position within the maze.
     * @param column The Tile's column position within the maze.
     * @param sides The number of sides that the Tile has.
     */
    public Tile(Maze maze, int row, int column)
    {
        this.maze = maze;

        this.row = row;
        this.column = column;

        this.sides = 4;
        this.walls = new boolean[this.sides];

        this.visited = false;
        this.isEntrance = false;
        this.isExit = false;

        this.initializeWalls();
    }

    /**Initializes all the Tile's walls by setting them to true.*/
    private void initializeWalls()
    {
        for(int i = 0; i < this.sides; i++)
        {
            this.walls[i] = true;
        }
    }

    /**@return The Maze the Tile belongs to.*/
    public Maze getMaze()
    {
        return maze;
    }

    /**@return The Tile's row position within the maze.*/
    public int getRow()
    {
        return this.row;
    }

    /**@return The Tile's column position within the maze.*/
    public int getColumn()
    {
        return this.column;
    }

    /**@return The number of sides the Tile has.*/
    public int getSides()
    {
        return sides;
    }

    /**@return The Tile's walls.*/
    public boolean[] getWalls()
    {
        return this.walls;
    }

    /**@param walls Sets the value of the Tile's walls.*/
    public void setWalls(boolean[] walls)
    {
        this.walls = walls;
    }

    /**
     * @param index The Tile wall to set the value of.
     * @param wall The value of the wall.
     */
    public void setWall(int index, boolean wall)
    {
        this.walls[index] = wall;
    }

    /**@return True when the Tile is marked as visited. Otherwise false.*/
    public boolean isVisited()
    {
        return this.visited;
    }

    /**@param visited Whether or not the Tile should be marked as visited.*/
    public void setVisited(boolean visited)
    {
        this.visited = visited;
    }

    /**@return True when the Tile is an entrance. Otherwise false.*/
    public boolean isEntrance()
    {
        return isEntrance;
    }

    /**@param isEntrance Whether or not the Tile should be an entrance.*/
    public void setEntrance(boolean isEntrance)
    {
        this.isEntrance = isEntrance;
        this.isExit = false;
    }

    /**@return True when the Tile is an exit. Otherwise false.*/
    public boolean isExit()
    {
        return isExit;
    }

    /**@param isExit Whether or not the Tile should be an exit.*/
    public void setExit(boolean isExit)
    {
        this.isExit = isExit;
        this.isEntrance = false;
    }
}
