package com.mygdx.game.main.maze;

import com.mygdx.game.main.enumer.GenerationType;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class MazeAlgorithms {

    public Maze getMaze(GenerationType gen, int width, int height)
    {
        Maze maze = null;
        maze = getRBMaze(width, height);
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

}
