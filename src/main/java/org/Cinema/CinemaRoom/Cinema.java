package org.Cinema.CinemaRoom;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Cinema {
    @JsonProperty("total_rows")
    private final int totalRows;

    @JsonProperty("total_columns")
    private final int totalColumns;

    @JsonProperty("available_seats")
    List<Seat> availableSeats;


    public Cinema(int totalRows, int totalColumns) {
        this.totalRows = totalRows;
        this.totalColumns = totalColumns;
        /*
        for (int i =1;i<=totalRows;i++){

            for (int j =1;j<=totalColumns;j++){
                Map<String,Integer> temp = new LinkedHashMap<>();
                temp.put("row",i);
                temp.put("column",j);
                availableSeats.add(temp);
            }

        }

         */
        this.availableSeats=fillSeats();


    }

    private List<Seat> fillSeats(){
        List<Seat> seatList = new ArrayList<>();
        IntStream.rangeClosed(1, totalRows)
                .forEach(i -> IntStream.rangeClosed(1, totalColumns)
                        .forEach(j -> {
                            int price = 8;
                            if (i<=4)
                                price=10;

                            seatList.add(new Seat(i,j,price));
                        }
    ));
        return List.copyOf(seatList);
    }

    public int getTotalRows() {
        return totalRows;
    }

    public int getTotalColumns() {
        return totalColumns;
    }

    public List<Seat> getAvailableSeats() {
        return availableSeats;
    }

    public Seat reserveSeat(Seat seat){
        if (seat.getRow()>=1 && seat.getRow()<=totalRows && seat.getColumn()>=1 && seat.getColumn()<=totalColumns) {
            for (Seat singleSeat: availableSeats){
                if (singleSeat.sameSeat(seat)){
                    if (singleSeat.isOccupied()){
                        throw new IllegalArgumentException("The ticket has been already purchased!");
                        //System.out.println("The ticket has been already purchased!");
                    }
                    else {
                        singleSeat.setOccupied(true);
                        return singleSeat;
                        //break;
                    }
                }
            }
        }
        else {
            throw new IllegalArgumentException("The number of a row or a column is out of bounds!");
            //System.out.println("The number of a row or a column is out of bounds!");
        }

        return seat;
    }

    @Override
    public String toString() {
        return "Cinema{" +
                "totalRows=" + totalRows +
                ", totalColumns=" + totalColumns +
                ", availableSeats=" + availableSeats +
                '}';
    }
}
