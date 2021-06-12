package com.mygdx.game.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class MazeFactory {
    /**
     * Get's a maze based on the passed in TileType and GenerationType.
     * @param tile The type of tile that the maze should consist of.
     * @param gen The method of generation that should be used to create the maze.
     * @param width The width, in tiles, of the maze.
     * @param height The height, in tiles, of the maze.
     * @return A fully generated mazed object.
     */
    public Maze getMaze(GenerationType gen, int width, int height)
    {
        Maze maze = null;


        switch(gen)
        {
            case ALDOUS_BRODER:
                maze = getSquareABMaze(width, height);
                break;

            case RECURSIVE_BACKTRACK:
                maze = getSquareRBMaze(width, height);
                break;

            default:
                maze = getSquareRBMaze(width, height);
                break;


        }


        return maze;
    }

    /**
     * Gets a square maze generated using a recursive backtracking algorithm.
     * @param width The width, in tiles, of the maze.
     * @param height The height, in tiles, of the maze.
     * @return A fully generated square maze.
     */
    public MazeSquare getSquareRBMaze(int width, int height)
    {
        MazeSquare maze = new MazeSquare(width, height);
        return recursiveBacktrackAlgorithm(maze);
    }

    /**
     * A generic method which processes the passed in Maze object
     * using a recursive backtracking algorithm.
     * @param maze The maze to process.
     * @return A fully generated maze.
     */
    private <T extends Maze> T recursiveBacktrackAlgorithm(T maze)
    {
        //Create random generator.
        Random random = new Random();

        //Create a Stack of Tile objects.
        Stack<Tile> stack = new Stack<>();

        //Get Random Tile and set to Visited
        int row = random.nextInt(maze.getRows());
        int col = random.nextInt(maze.getColumns());

        Tile active = maze.getTile(row, col);
        active.setVisited(true);

        //Calculate the number of remaining unvisited Tiles
        int remaining = maze.getRows() * maze.getColumns() - 1;

        while(remaining > 0)
        {
            ArrayList<Tile> unvisited = maze.getUnvisitedNeighbors(active);
            if(!unvisited.isEmpty())
            {
                //Choose a random neighbor
                Tile target = unvisited.get(random.nextInt(unvisited.size()));

                //Push current cell to stack
                stack.push(active);

                //Move wall between current and random and set target to visited.
                maze.carvePath(active, target);
                target.setVisited(true);

                //Make random cell current
                active = target;

                remaining--;
            }
            else
            {
                active = stack.pop();
            }
        }

        //Add entrance and exit.
        maze.setEntranceAndExit(random);

        return maze;
    }

    /**
     * Gets a square maze generated using the Aldous-Broder algorithm.
     * @param width The width, in tiles, of the maze.
     * @param height The height, in tiles, of the maze.
     * @return A fully generated square maze.
     */
    public MazeSquare getSquareABMaze(int width, int height)
    {
        MazeSquare maze = new MazeSquare(width, height);
        return aldousBroderAlgorithm(maze);
    }

    /**
     * A generic method which processes the passed in Maze object
     * using the Aldous-Broder algorithm.
     * @param maze The maze to process.
     * @return A fully generated maze.
     */
    private <T extends Maze> T aldousBroderAlgorithm(T maze)
    {
        Random random = new Random();

        //Get Random Tile and set to Visited
        int row = random.nextInt(maze.getRows());
        int col = random.nextInt(maze.getColumns());

        Tile active = maze.getTile(row, col);
        active.setVisited(true);

        //Calculate the number of remaining unvisited Tiles
        int remaining = maze.getRows() * maze.getColumns() - 1;

        //Loop until every Tile has been visited.
        while(remaining > 0)
        {
            //Get a random neighbor of the active Tile.
            Tile target = maze.getRandomNeighbor(random, active);

            //If that Tile has not been visited, carve a path between
            //the active and the target. Set the target to visited
            if(!target.isVisited())
            {
                maze.carvePath(active, target);
                target.setVisited(true);
                remaining -= 1;
            }

            //Update active Tile
            active = target;
        }

        //Add entrance and exit.
        maze.setEntranceAndExit(random);

        return maze;
    }
}
