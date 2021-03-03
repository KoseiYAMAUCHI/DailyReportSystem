package models;

import java.io.Serializable;

public class Yoine implements Serializable {
    private int yoineCount = 0;

    public void setYoineCount(int yoineCount) {
        this.yoineCount = yoineCount;
    }

    public int getYoineCount() {
        return yoineCount;
    }
}