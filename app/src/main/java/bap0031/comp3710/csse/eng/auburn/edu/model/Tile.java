package bap0031.comp3710.csse.eng.auburn.edu.model;

/**
 * Created by bradley on 4/11/17
 */

public class Tile {

    private int value, row, coloumn;
    private boolean active;

    public Tile(int value, int row, int column, boolean active) {
        this.value = value;
        this.row = row;
        this.coloumn = column;
        this.active = active;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColoumn(int coloumn) {
        this.coloumn = coloumn;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getValue() {
        return value;
    }

    public int getRow() {
        return row;
    }

    public int getColoumn() {
        return coloumn;
    }

    public boolean isActive() {
        return active;
    }

    @Override
    public String toString() {
        return value + "";
    }
}
