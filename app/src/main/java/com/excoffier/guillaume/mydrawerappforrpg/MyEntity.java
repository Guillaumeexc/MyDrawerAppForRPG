package com.excoffier.guillaume.mydrawerappforrpg;

import android.media.Image;

/**
 * Created by guigu on 25/04/2018.
 */

public class MyEntity {

    private String name;
    private String path;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public MyEntity(String name, String path) {
        this.name = name;
        this.path = path;
    }

}
