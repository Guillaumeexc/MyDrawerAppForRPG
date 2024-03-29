package com.excoffier.guillaume.mydrawerappforrpg;

/**
 * Created by guigu on 11/04/2018.
 */

public class Room {
    static final int NO_EXIT = -1;
    private int north;
    private int east;
    private int south;
    private int west;
    private String description;
    private String inventory;




    Room()
    {
        north = NO_EXIT;
        east = NO_EXIT;
        south = NO_EXIT;
        west = NO_EXIT;
        description = "NOTHING";
        inventory="nothing";


    }
    public String getInventory() {
        return inventory;
    }

    public void setInventory(String inventory) {
        this.inventory = inventory;
    }

    public static int getNoExit() {
        return NO_EXIT;
    }

    public int getNorth() {
        return north;
    }

    public void setNorth(int north) {
        this.north = north;
    }

    public int getEast() {
        return east;
    }

    public void setEast(int east) {
        this.east = east;
    }

    public int getSouth() {
        return south;
    }

    public void setSouth(int south) {
        this.south = south;
    }

    public int getWest() {
        return west;
    }

    public void setWest(int west) {
        this.west = west;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }




} // public class Room

