package com.mygdx.game.main.maze;

import com.mygdx.game.main.enumer.GenerationType;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class MazeAlgorithms {

    public Maze getMaze(GenerationType gen, int width, int height)
    {
        Maze maze = null;

        switch(gen)
        {
            case ALDOUS_BRODER:
                maze = getABMaze(width, height);
                break;

            case RECURSIVE_BACKTRACK:
                maze = getRBMaze(width, height);
                break;

            default:
                maze = getRBMaze(width, height);
                break;


        }


        return maze;
    }

    public MazeCarver getRBMaze(int width, int height)
    {
        MazeCarver maze = new MazeCarver(width, height);
        return recursiveBacktrackAlgorithm(maze);
    }

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

    public MazeCarver getABMaze(int width, int height)
    {
        MazeCarver maze = new MazeCarver(width, height);
        return aldousBroderAlgorithm(maze);
    }

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
