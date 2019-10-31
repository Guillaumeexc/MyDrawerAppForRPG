package com.excoffier.guillaume.mydrawerappforrpg;

/**
 * Created by guigu on 11/04/2018.
 */

public class Player {
    int playerPos;
    private String inventory;
    private int hpActuel;
    private int hpMax;
    private int xp;
    private int Level;
    private int damage;

    public int getPlayerPos() {
        return playerPos;
    }

    public void setPlayerPos(int playerPos) {
        this.playerPos = playerPos;
    }

    public String getInventory() {
        return inventory;
    }

    public void setInventory(String inventory) {
        this.inventory = inventory;
    }

    public int getHpActuel() {
        return hpActuel;
    }

    public void setHpActuel(int hpActuel) {
        this.hpActuel = hpActuel;
    }

    public int getHpMax() {
        return hpMax;
    }

    public void setHpMax(int hpMax) {
        this.hpMax = hpMax;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getLevel() {
        return Level;
    }

    public void setLevel(int level) {
        Level = level;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public Player(int playerPos, String inventory, int hpActuel, int hpMax, int xp, int level, int damage) {
        this.playerPos = playerPos;
        this.inventory = inventory;
        this.hpActuel = hpActuel;
        this.hpMax = hpMax;
        this.xp = xp;
        Level = level;
        this.damage = damage;
    }
}
