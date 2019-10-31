package com.excoffier.guillaume.mydrawerappforrpg;

import android.media.Image;

/**
 * Created by guigu on 25/04/2018.
 */

public class Armor extends MyEntity {
    int armor;

    public Armor(String name, String path, int armor) {
        super(name, path);
        this.armor = armor;
    }



    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }
}
