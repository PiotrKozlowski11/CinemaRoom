package org.Cinema.CinemaRoom;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashMap;

public class Seat {
    private int row;
    private int column;
    private int price;
    @JsonIgnore
    private boolean occupied;



    public Seat(int row, int column, int price) {
        this.row = row;
        this.column = column;
        this.price = price;
        //occupied=false;
    }


    @JsonCreator
    public Seat(int row, int column) {
        this(row,column,0);
    }



    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }


    public int getPrice() {
        return price;
    }


    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public boolean sameSeat(Seat seat){
        return this.row == seat.row && this.column == seat.column;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "row=" + row +
                ", column=" + column +
                ", price=" + price +
                ", occupied=" + occupied +
                '}';
    }
}
