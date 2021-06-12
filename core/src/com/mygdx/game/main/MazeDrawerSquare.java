package com.mygdx.game.main;

public class MazeDrawerSquare extends MazeDrawer{
    protected int tileSize;

    public MazeDrawerSquare(Maze maze)
    {
        super(maze, 4);

        this.tileSize = 24;

    }

    @Override
    protected boolean isOnScreen(int i, int j)
    {
        float x = (i * this.tileSize);
        float y = (j * this.tileSize);

        if(MazeGame.camera.frustum.boundsInFrustum(x, y, 0f, this.tileSize, this.tileSize, 0f))
        {
            return true;
        }

        return false;
    }

    @Override
    protected void draw(int i, int j)
    {
        if(this.maze.getMaze()[i][j] != null)
        {
            this.drawTiles(i, j);
            this.drawCorners(i, j);
            this.drawWalls(i, j);
        }
    }

    private void drawTiles(int i, int j)
    {
        if(this.maze.getMaze()[i][j].isEntrance())
        {
            this.drawEntrance(i, j);
        }
        else if(this.maze.getMaze()[i][j].isExit())
        {
            this.drawExit(i, j);
        }
        else
        {
            //Draw Tile Background
            if(this.maze.getMaze()[i][j].isVisited())
            {
                MazeGame.shape.setColor(this.visitedColor);
            }
            else
            {
                MazeGame.shape.setColor(this.tileColor);
            }

            this.drawTile(i, j);

        }
    }

    private void drawCorners(int i, int j)
    {
        MazeGame.shape.setColor(this.wallColor);
        this.drawUpperLeftCorners(i, j);
        this.drawLowerRightCorners(i, j);
    }

    private void drawWalls(int i, int j)
    {
        //Draw Tile Walls
        MazeGame.shape.setColor(this.wallColor);
        if(this.maze.getMaze()[i][j].getWalls()[0]) //North Wall
        {
            this.drawNorthWall(i, j);
        }

        if(this.maze.getMaze()[i][j].getWalls()[1]) //East Wall
        {
            this.drawEastWall(i, j);
        }

        if(this.maze.getMaze()[i][j].getWalls()[2]) //South Wall
        {
            this.drawSouthWall(i, j);
        }

        if(this.maze.getMaze()[i][j].getWalls()[3]) //West Wall
        {
            this.drawWestWall(i, j);
        }
    }

    private void drawEntrance(int i, int j)
    {
        MazeGame.shape.setColor(this.tileColor);
        MazeGame.shape.rect(this.tileSize * i,
                this.tileSize * j,
                this.tileSize,
                this.tileSize + this.wallSize);

        MazeGame.shape.setColor(this.entranceColor);
        MazeGame.shape.rect(this.tileSize * i,
                this.tileSize * j,
                this.tileSize,
                this.wallSize);
    }

    private void drawExit(int i, int j)
    {
        MazeGame.shape.setColor(this.tileColor);
        MazeGame.shape.rect(this.tileSize * i,
                this.tileSize * j,
                this.tileSize,
                this.tileSize + this.wallSize);

        MazeGame.shape.setColor(this.exitColor);
        MazeGame.shape.rect(this.tileSize * i,
                this.tileSize * j + this.tileSize,
                this.tileSize,
                this.wallSize);
    }

    private void drawTile(int i, int j)
    {
        MazeGame.shape.rect(this.tileSize * i,
                this.tileSize * j,
                this.tileSize,
                this.tileSize + this.wallSize);
    }

    private void drawNorthWall(int i, int j)
    {
        MazeGame.shape.rect(this.tileSize * i,
                this.tileSize * j,
                this.tileSize,
                this.wallSize);
    }

    private void drawEastWall(int i, int j)
    {
        MazeGame.shape.rect((this.tileSize * i) + (this.tileSize),
                this.tileSize * j,
                this.wallSize,
                this.tileSize);
    }

    private void drawSouthWall(int i, int j)
    {
        MazeGame.shape.rect(this.tileSize * i,
                (this.tileSize * j) + (this.tileSize),
                this.tileSize,
                this.wallSize);
    }

    private void drawWestWall(int i, int j)
    {
        MazeGame.shape.rect(this.tileSize * i,
                this.tileSize * j,
                this.wallSize,
                this.tileSize + this.wallSize);
    }

    private void drawUpperLeftCorners(int i, int j)
    {
        MazeGame.shape.rect(this.tileSize * i,
                this.tileSize * j,
                this.wallSize,
                this.wallSize);
    }

    private void drawLowerRightCorners(int i, int j)
    {
        MazeGame.shape.rect((this.tileSize * i) + this.tileSize,
                (this.tileSize * j) + (this.tileSize),
                this.wallSize,
                this.wallSize);
    }

    public int getWallSize()
    {
        return wallSize;
    }

    public void setWallSize(int wallSize)
    {
        this.wallSize = wallSize;
    }

    public int getTileSize()
    {
        return tileSize;
    }

    public void setTileSize(int tileSize)
    {
        this.tileSize = tileSize;
    }

    @Override
    public float getTileX(int row, int column)
    {
        return this.tileSize * row;
    }

    @Override
    public float getTileY(int row, int column)
    {
        return this.tileSize * column;
    }

    @Override
    public float getTileCenterX(int row, int column)
    {
        return (this.tileSize * row) + (this.tileSize / 2);
    }

    @Override
    public float getTileCenterY(int row, int column)
    {
        return (this.tileSize * column) + (this.tileSize / 2);
    }
}
