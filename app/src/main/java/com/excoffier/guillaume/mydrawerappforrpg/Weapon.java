package com.excoffier.guillaume.mydrawerappforrpg;

import android.media.Image;

/**
 * Created by guigu on 25/04/2018.
 */

public class Weapon extends MyEntity {
    int damage;



    public Weapon(String name, String path, int damage) {
        super(name, path);
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }


}
