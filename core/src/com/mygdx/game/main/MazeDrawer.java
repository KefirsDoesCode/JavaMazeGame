package com.mygdx.game.main;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public abstract class MazeDrawer {

        protected Maze maze;

        protected int mazeWidth;

        protected int mazeHeight;

        protected int wallSize;

        protected Color bgColor;

        protected Color tileColor;

        protected Color visitedColor;

        protected Color wallColor;

        protected Color entranceColor;

        protected Color exitColor;

        protected Color playerColor;

        public MazeDrawer(Maze maze, int wallSize)
        {
            this.maze = maze;

            this.mazeWidth = maze.getRows();
            this.mazeHeight = maze.getColumns();

            this.wallSize = wallSize;

            this.setDarkModeColors();
        }

        private void setLightModeColors()
        {
            this.bgColor = new Color(1f, 1f, 1f, 1.0f);
            this.tileColor = new Color(1f, 1f, 1f, 1.0f);
            this.visitedColor = new Color(1f, 1f, 1f, 1.0f);

            this.wallColor =  new Color(0f, 0f, 0f, 1.0f);

            this.entranceColor = new Color(1f, 1f, 1f, 1.0f);
            this.exitColor = new Color(1f, 1f, 1f, 1.0f);

            this.playerColor  = new Color(1f, 0f, 0f, 1.0f);
        }

        private void setDarkModeColors()
        {
            this.bgColor = new Color(0f, 0f, 0f, 0f);
            this.tileColor = new Color(0f, 0f, 0f, 0f);
            this.visitedColor = new Color(0f, 0f, 0f, 0f);

            this.wallColor =  new Color(1f, 1f, 1f, 1.0f);

            this.entranceColor = new Color(0f, 0f, 0f, 0f);
            this.exitColor = new Color(0f, 0f, 0f, 0f);

            this.playerColor  = new Color(0f, 1f, 0f, 1.0f);
        }

        public void render()
        {
            if(maze != null)
            {
                MazeGame.shape.begin(ShapeRenderer.ShapeType.Filled);
                for(int i = 0; i < maze.getRows(); i++)
                {
                    for(int j = 0; j < maze.getColumns(); j++)
                    {
                        if(this.isOnScreen(i, j))
                        {
                            this.draw(i, j);
                        }
                    }
                }
                MazeGame.shape.end();
            }
        }

        abstract protected boolean isOnScreen(int i, int j);

        abstract protected void draw(int i, int j);

        abstract public float getTileX(int row, int column);

        abstract public float getTileY(int row, int column);

        abstract public float getTileCenterX(int row, int column);

        abstract public float getTileCenterY(int row, int column);

        public Maze getMaze()
        {
            return maze;
        }

        public void setMaze(Maze maze)
        {
            this.maze = maze;
            this.mazeWidth = maze.getRows();
            this.mazeHeight = maze.getColumns();
        }

        public int getMazeWidth()
        {
            return mazeWidth;
        }

        public void setMazeWidth(int mazeWidth)
        {
            this.mazeWidth = mazeWidth;
        }

        public int getMazeHeight()
        {
            return mazeHeight;
        }

        public void setMazeHeight(int mazeHeight)
        {
            this.mazeHeight = mazeHeight;
        }

        public int getWallSize()
        {
            return wallSize;
        }

        public void setWallSize(int wallSize)
        {
            this.wallSize = wallSize;
        }

        public Color getBgColor()
        {
            return bgColor;
        }

        public void setBgColor(Color bgColor)
        {
            this.bgColor = bgColor;
        }

        public Color getTileColor()
        {
            return tileColor;
        }

        public void setTileColor(Color tileColor)
        {
            this.tileColor = tileColor;
        }

        public Color getVisitedColor()
        {
            return visitedColor;
        }

        public void setVisitedColor(Color visitedColor)
        {
            this.visitedColor = visitedColor;
        }

        public Color getWallColor()
        {
            return wallColor;
        }

        public void setWallColor(Color wallColor)
        {
            this.wallColor = wallColor;
        }

        public Color getEntranceColor()
        {
            return entranceColor;
        }

        public void setEntranceColor(Color entranceColor)
        {
            this.entranceColor = entranceColor;
        }

        public Color getExitColor()
        {
            return exitColor;
        }

        public void setExitColor(Color exitColor)
        {
            this.exitColor = exitColor;
        }

        public Color getPlayerColor()
        {
            return playerColor;
        }

        public void setPlayerColor(Color playerColor)
        {
            this.playerColor = playerColor;
        }


}
