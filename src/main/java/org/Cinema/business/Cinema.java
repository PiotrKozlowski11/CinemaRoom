package org.Cinema.business;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * Class which represents cinema
 */
public class Cinema {

    // total amount of rows in a cinema
    @JsonProperty("total_rows")
    private final int totalRows;

    // total amount of columns in each row
    @JsonProperty("total_columns")
    private final int totalColumns;

    // list of all reservations in a cinema. Each reservation is linked with seat
    List<Reservation> reservations;

    /**
     * Constructor with required parameters
     *
     * @param totalRows    amount of rows in a cinema
     * @param totalColumns amount of columns in each row
     */
    public Cinema(int totalRows, int totalColumns) {
        this.totalRows = totalRows;
        this.totalColumns = totalColumns;

        this.reservations = fillReservations();


    }

    /**
     * Method used to fill list with reservations in which seats are not occupied.
     * Price for rows 1 to 4 inclusive is 10, for other rows price is 8.
     *
     * @return list of reservations
     */
    private List<Reservation> fillReservations() {
        List<Reservation> seatList = new ArrayList<>();
        IntStream.rangeClosed(1, totalRows)
                .forEach(i -> IntStream.rangeClosed(1, totalColumns)
                        .forEach(j -> {
                                    int price = 8;
                                    if (i <= 4)
                                        price = 10;

                                    seatList.add(new Reservation(new Seat(i, j, price)));
                                }
                        ));
        return List.copyOf(seatList);
    }

    /**
     * Getter
     *
     * @return total rows
     */
    public int getTotalRows() {
        return totalRows;
    }

    /**
     * Getter
     *
     * @return total columns
     */
    public int getTotalColumns() {
        return totalColumns;
    }

    /**
     * Getter
     *
     * @return list of reservations
     */
    public List<Reservation> getReservations() {
        return reservations;
    }

    /**
     * When an instance of a class Seat class is given the reservation is made if the selected seat is free.
     *
     * @param seat Object of a seat class. Must be provided with appropriate row and column, price is not important
     * @return Reservation object with reserved seat.
     * @throws IllegalArgumentException if the selected seat is already occupied
     */
    public Reservation reserveSeat(Seat seat) {
        Reservation singleReservation = reservations.stream()
                .filter(reservation -> seat.sameSeat(reservation.getSeat()))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("The number of a row or a column is out of bounds!"));
        if (singleReservation.isOccupied()) {
            throw new IllegalArgumentException("The ticket has been already purchased!");
        }
        singleReservation.reserveSeat();
        return singleReservation;

    }

    /**
     * @param token UUID token which was given to the user during reservation
     * @return Reservation object with vacated seat
     * @throws IllegalArgumentException if the given token is not found
     */
    public Reservation returnTicket(UUID token) throws IllegalArgumentException {

        Reservation singleReservation = reservations.stream()
                .filter(reservation -> token.equals(reservation.getUuid()))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Wrong token!"));

        singleReservation.releaseSeat();
        return singleReservation;


    }

    /**
     * Method which returns all statistics of a cinema: number of purchased tickets, total income
     * and number of available seats
     *
     * @return Statistics object with parameters listed above
     */
    public Statistics getStatistics() {
        AtomicInteger purchasedTickets = new AtomicInteger();
        AtomicInteger totalIncome = new AtomicInteger();
        reservations.stream()
                .filter(Reservation::isOccupied)
                .forEach(reservation -> {
                    purchasedTickets.getAndIncrement();
                    totalIncome.addAndGet(reservation.getSeat().getPrice());
                });
        int allSeats = reservations.size() - purchasedTickets.intValue();

        return new Statistics(totalIncome.intValue(), allSeats, purchasedTickets.intValue());
    }

    @Override
    public String toString() {
        return "Cinema{" +
                "totalRows=" + totalRows +
                ", totalColumns=" + totalColumns +
                ", availableSeats=" + reservations +
                '}';
    }
}
