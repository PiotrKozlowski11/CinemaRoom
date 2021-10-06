package org.Cinema.business;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

/**
 * Reservation class, used to book, released booked seats and generate tokens
 */
public class Reservation {

    // information about seat
    @JsonProperty("ticket")
    private final Seat seat;

    // token - unique number which is given to user during reservation. Used to return already booked ticket
    @JsonProperty("token")
    private UUID uuid;

    // whether the seat is free or occupied
    @JsonIgnore
    private boolean occupied;


    /**
     * Constructor with required parameter. Default values for uuid is null and for occupied is false
     *
     * @param seat instance of a Seat class
     */
    public Reservation(Seat seat) {
        this.seat = seat;
        this.uuid = null;
    }

    /**
     * Getter
     *
     * @return uuid number
     */
    public UUID getUuid() {
        return uuid;
    }

    /**
     * Setter
     *
     * @param uuid set uuid number
     */
    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    /**
     * Getter
     *
     * @return seat
     */
    public Seat getSeat() {
        return seat;
    }

    /**
     * Getter
     *
     * @return occupied
     */
    public boolean isOccupied() {
        return occupied;
    }

    /**
     * Setter
     *
     * @param occupied set occupied
     */
    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    /**
     * Method used to reserve seat and generate new random token
     */
    public void reserveSeat() {
        this.uuid = UUID.randomUUID();
        occupied = true;
    }

    /**
     * Method used to release booked reservation - set uuid to null and occupied to false
     */
    public void releaseSeat() {
        this.uuid = null;
        occupied = false;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "uuid=" + uuid +
                ", seat=" + seat +
                ", occupied=" + occupied +
                '}';
    }
}
