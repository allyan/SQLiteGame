package com.example.allyan.sqlitegame;

/**
 * Created by Allyan on 28/04/2017.
 */

public class GamerInfo  {
    private String name;
    private String time;
    private int clickes;

    public GamerInfo(String name, String time, int clickes) {
        this.name = name;
        this.time = time;
        this.clickes = clickes;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public int getClickes() {
        return clickes;
    }
}
