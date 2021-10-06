package org.Cinema.business;

/**
 * Class represents single seat specified with row, column and price
 */
public class Seat {
    // row of a seat
    private final int row;
    // column of a seat
    private final int column;
    //price of a seat
    private final int price;


    /**
     * All args constructor
     *
     * @param row    row of a seat
     * @param column column of a seat
     * @param price  price of a seat
     */
    public Seat(int row, int column, int price) {
        this.row = row;
        this.column = column;
        this.price = price;
    }


    /**
     * Getter
     *
     * @return row
     */
    public int getRow() {
        return row;
    }

    /**
     * Getter
     *
     * @return column
     */
    public int getColumn() {
        return column;
    }


    /**
     * Getter
     *
     * @return price
     */
    public int getPrice() {
        return price;
    }

    /**
     * Method used to compare if given seat has the same place (same row and column)
     *
     * @param seat with given row and column, price is not important
     * @return true if row and column are the same, otherwise false
     */
    public boolean sameSeat(Seat seat) {
        return this.row == seat.row && this.column == seat.column;
    }

}
