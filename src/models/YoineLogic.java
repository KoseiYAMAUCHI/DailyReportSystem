package models;

public class YoineLogic {

    public void yoinePlus(Yoine y) {
        int count = y.getYoineCount();
        count++;
        y.setYoineCount(count);
    }
}
