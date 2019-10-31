package com.excoffier.guillaume.mydrawerappforrpg;

import android.media.Image;

/**
 * Created by guigu on 25/04/2018.
 */

public class Helmet extends MyEntity {
    int health;

    public Helmet(String name, String path, int health) {
        super(name, path);
        this.health = health;
    }



    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
