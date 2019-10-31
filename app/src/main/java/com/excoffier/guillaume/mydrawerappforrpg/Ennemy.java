package com.excoffier.guillaume.mydrawerappforrpg;

/**
 * Created by guigu on 26/04/2018.
 */

public class Ennemy {
    int ennemyPos;
    private int hpActuel;
    private int hpMax;
    private Integer path;

    public int getEnnemyPos() {
        return ennemyPos;
    }

    public void setEnnemyPos(int ennemyPos) {
        this.ennemyPos = ennemyPos;
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

    public Integer getPath() {
        return path;
    }

    public void setPath(Integer path) {
        this.path = path;
    }

    public Ennemy(int ennemyPos, int hpActuel, int hpMax, Integer path) {
        this.ennemyPos = ennemyPos;
        this.hpActuel = hpActuel;
        this.hpMax = hpMax;
        this.path = path;
    }

}


