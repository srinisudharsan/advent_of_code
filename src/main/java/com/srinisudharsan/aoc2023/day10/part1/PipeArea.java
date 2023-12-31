package com.srinisudharsan.aoc2023.day10.part1;

import java.util.HashMap;
import java.util.Map;

enum Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT;
}

public class PipeArea {
    private char[][] area;
    private static final char NORTHSOUTH_PIPE = '|';
    private static final char EASTWEST_PIPE = '-';
    private static final char NORTHEAST_PIPE = 'L';
    private static final char NORTHWEST_PIPE = 'J';
    private static final char SOUTHWEST_PIPE = '7'; 
    private static final char SOUTHEAST_PIPE = 'F';
    private static final char START_PIPE = 'S';
    private static final char GROUND = '.';

    private Map<Character, Direction[]> possibleDirections = new HashMap<Character, Direction[]>();

    public PipeArea(char[][] area){
        this.area = area;
        this.possibleDirections.put(NORTHSOUTH_PIPE, new Direction[]{Direction.UP, Direction.DOWN});
        this.possibleDirections.put(EASTWEST_PIPE, new Direction[]{Direction.LEFT, Direction.RIGHT});
        this.possibleDirections.put(NORTHEAST_PIPE, new Direction[]{Direction.UP, Direction.RIGHT});
        this.possibleDirections.put(NORTHWEST_PIPE, new Direction[]{Direction.UP, Direction.LEFT});
        this.possibleDirections.put(SOUTHWEST_PIPE, new Direction[]{Direction.DOWN, Direction.LEFT});
        this.possibleDirections.put(SOUTHEAST_PIPE, new Direction[]{Direction.DOWN, Direction.RIGHT});
    }

    public int getFarthestLocationDistance(){
        Location start = this.findStart();
        if(start == null){
            System.out.println("No start location found.");
            return -1;
        }
        for(Direction dir: Direction.values()){
            Location currentLocation = this.makeMove(start, dir);
            Location prevLocation = start;
            int loopSize = 0;
            while(currentLocation != null && !start.equals(currentLocation)){
                Location nextLocation = this.findNextMove(currentLocation, prevLocation);
                prevLocation = currentLocation;
                currentLocation = nextLocation;
                loopSize++;
            }
            if(start.equals(currentLocation)){
                return loopSize/2 + 1;
            }
        }
        return -1;
    }

    private Location findStart(){
        for(int i = 0; i < this.area.length; i++){
            for(int j = 0; j < this.area[i].length; j++){
                if(this.area[i][j] == START_PIPE){
                    return new Location(i, j);
                }
            }
        }
        return null;
    }

    // One location can only have two possible moves. If one of them is given, we only need to find the next one.
    private Location findNextMove(Location currLoc, Location prevLoc){
        Location retVal = null;
        Direction[] possibleDirections = new Direction[2];
        char currChar = this.area[currLoc.getRow()][currLoc.getCol()];
        possibleDirections = this.possibleDirections.get(currChar);
        if(possibleDirections == null){
            System.out.println("No entry for char " + currChar + " in possibleDirections map.");
            return null;
        }

        Location firstMove = makeMove(currLoc, possibleDirections[0]);
        if(prevLoc.equals(firstMove)){
            retVal = makeMove(currLoc, possibleDirections[1]);
        } else {
            retVal = firstMove;
        }

        if(retVal == null){
            System.out.println("Location: " + currLoc.getRow() + ", " + currLoc.getCol() + " has no pipes connected to it.");
        }
        return retVal;
    }

    // Returns the next location if the move is valid, else returns null
    private Location makeMove(Location currLoc, Direction dir){
        int row = currLoc.getRow();
        int col = currLoc.getCol();
        switch(dir){
            case UP:
                int upRow = row - 1;
                if(upRow < 0 || this.area[upRow][col] == GROUND){
                    return null;
                }
                return new Location(upRow, col);
            case DOWN:
                int downRow = row + 1; 
                if(downRow >= this.area.length || this.area[downRow][col] == GROUND){
                    return null;
                }
                return new Location(downRow, col);
            case RIGHT:
                int rightCol = col + 1; 
                if(rightCol >= this.area[0].length || this.area[row][rightCol] == GROUND){
                    return null;
                }
                return new Location(row, rightCol);
            case LEFT:
                int leftCol = col - 1; 
                if(leftCol < 0 || this.area[row][leftCol] == GROUND){
                    return null;
                }
                return new Location(row, leftCol);
            default:
                return null;
        }
    }

    private class Location{
        protected int row;
        protected int col;
        public Location(int row, int col){
            this.row = row;
            this.col = col;
        }

        public int getRow(){
            return row;
        }

        public int getCol(){
            return col;
        }

        @Override
        public boolean equals(Object other){
            if(other == null){
                return false;
            }
            if(!(other instanceof Location)){
                return false;
            }
            Location otherLoc = (Location) other;
            return this.row == otherLoc.row && this.col == otherLoc.col;
        }

        @Override
        public int hashCode(){
            return this.row * 31 + this.col;
        }
    }
}
