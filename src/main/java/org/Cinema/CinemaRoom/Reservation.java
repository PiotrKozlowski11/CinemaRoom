package org.Cinema.CinemaRoom;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Reservation {
    private UUID uuid;
    private Seat seat;



    public Reservation(Seat seat) {
        this.seat = seat;
        uuid= UUID.randomUUID();
    }

    public UUID getUuid() {
        return uuid;
    }

    public Seat getSeat() {
        return seat;
    }


}
