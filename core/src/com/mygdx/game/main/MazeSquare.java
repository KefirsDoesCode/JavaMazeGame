package com.mygdx.game.main;

import java.util.ArrayList;
import java.util.Random;

public class MazeSquare extends Maze {
    public MazeSquare(int rows, int columns)
    {
        super(rows, columns);
    }

    @Override
    public void carvePath(Tile origin, Tile target)
    {
        ArrayList<Tile> neighbors = this.getNeighbors(origin);
        for(int i = 0; i < neighbors.size(); i++)
        {
            if(neighbors.get(i) != null && neighbors.get(i).equals(target))
            {
                origin.setWall(i, false);
                switch(i)
                {
                    case 0: target.setWall(2, false); break;
                    case 1: target.setWall(3, false); break;
                    case 2: target.setWall(0, false); break;
                    case 3: target.setWall(1, false); break;
                    default: break;
                }
            }
        }
    }

    @Override
    public boolean isPathClear(Tile origin, Tile target)
    {
        ArrayList<Tile> neighbors = this.getNeighbors(origin);
        for(int i = 0; i < neighbors.size(); i++)
        {
            if(neighbors.get(i) != null && neighbors.get(i).equals(target))
            {
                switch(i)
                {
                    case 0: if(!target.getWalls()[2]) { return true; } break; // MOVED NORTH
                    case 1: if(!target.getWalls()[3]) { return true; } break; // MOVED EAST
                    case 2: if(!target.getWalls()[0]) { return true; } break; // MOVED SOUTH
                    case 3: if(!target.getWalls()[1]) { return true; } break; // MOVED WEST
                    default: return false;
                }
            }
        }

        return false;
    }


    @Override
    public void setEntranceAndExit(Random random)
    {
        Tile entrance = this.getTile(random.nextInt(this.getRows()), 0);
        Tile exit = this.getTile(random.nextInt(this.getRows()), this.getColumns() - 1);

        this.entrance = entrance;
        entrance.setWall(0, false);
        entrance.setEntrance(true);

        this.exit = exit;
        exit.setWall(2, false);
        exit.setExit(true);
    }

    public ArrayList<Tile> getNeighbors(Tile tile)
    {
        ArrayList<Tile> neighbors = new ArrayList<Tile>();

        neighbors.add(this.getNorthNeighbor(tile));
        neighbors.add(this.getEastNeighbor(tile));
        neighbors.add(this.getSouthNeighbor(tile));
        neighbors.add(this.getWestNeighbor(tile));

        return neighbors;
    }

    /**
     * Gets the Tile to the North of the passed in Tile.
     * @param tile The origin Tile.
     * @return The Tile to the North of the origin Tile. Null if no Tile is found.
     */
    private Tile getNorthNeighbor(Tile tile)
    {
        if(this.isWithinBounds(tile.getRow(), tile.getColumn() - 1))
        {
            return this.getTile(tile.getRow(), tile.getColumn() - 1);
        }

        return null;
    }

    /**
     * Gets the Tile to the South of the passed in Tile.
     * @param tile The origin Tile.
     * @return The Tile to the South of the origin Tile. Null if no Tile is found.
     */
    private Tile getSouthNeighbor(Tile tile)
    {
        if(this.isWithinBounds(tile.getRow(), tile.getColumn() + 1))
        {
            return this.getTile(tile.getRow(), tile.getColumn() + 1);
        }

        return null;
    }

    /**
     * Gets the Tile to the East of the passed in Tile.
     * @param tile The origin Tile.
     * @return The Tile to the East of the origin Tile. Null if no Tile is found.
     */
    private Tile getEastNeighbor(Tile tile)
    {
        if(this.isWithinBounds(tile.getRow() + 1, tile.getColumn()))
        {

            return this.getTile(tile.getRow() + 1, tile.getColumn());
        }

        return null;
    }

    /**
     * Gets the Tile to the West of the passed in Tile.
     * @param tile The origin Tile.
     * @return The Tile to the West of the origin Tile. Null if no Tile is found.
     */
    private Tile getWestNeighbor(Tile tile)
    {
        if(this.isWithinBounds(tile.getRow() - 1, tile.getColumn()))
        {
            return this.getTile(tile.getRow() - 1, tile.getColumn());
        }

        return null;
    }
}
