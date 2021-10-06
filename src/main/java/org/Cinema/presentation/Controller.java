package org.Cinema.presentation;

import org.Cinema.business.Cinema;
import org.Cinema.business.Reservation;
import org.Cinema.business.Seat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * RestController class used to handle post, get mappings
 */
@RestController
public class Controller {


    // instance of a cinema class
    Cinema cinema = new Cinema(9, 9);


    /**
     * Get Mapping method used to get information about all rows and columns in cinema
     * and list of all seats with listed prices
     *
     * @return ResponseEntity map
     */
    @GetMapping("/seats")
    public ResponseEntity<Map<String, Object>> getCinema() {

        List<Seat> seatList = cinema.getReservations()
                .stream()
                .map(Reservation::getSeat)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(Map.of(
                "total_rows", cinema.getTotalRows(),
                "total_columns", cinema.getTotalColumns(),
                "available_seats", seatList));
    }

    /**
     * Post mapping method used to reserve selected seat
     *
     * @param seat with selected values of row and column, price is not important
     * @return ResponseEntity with instance of Seat class - selectedSeat
     */
    @PostMapping("/purchase")
    public ResponseEntity<?> buySeat(@RequestBody Seat seat) {

        try {

            Reservation selectedSeat = cinema.reserveSeat(seat);

            return ResponseEntity.ok(selectedSeat);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }


    }

    /**
     * Post mapping method used to release selected seat by sending token obtained during reservation.
     *
     * @param map <String, UUID> with key "token" and value of unique token number from reservation
     * @return ResponseEntity with Map<String,Seat> String is "returned_ticket", seat is object of released seat
     */
    @PostMapping("/return")
    public ResponseEntity<?> returnTicket(@RequestBody Map<String, UUID> map) {

        try {
            Reservation reservation = cinema.returnTicket(map.get("token"));
            return ResponseEntity.ok(Map.of("returned_ticket", reservation.getSeat()));
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }


    }

    /**
     * Post mapping method used to get statistics about cinema.
     * Including current income, number of available seats, number of purchased tickets
     *
     * @param password required to get access to statistics
     * @return ResponseEntity with instance of Statistics class
     */
    @PostMapping("/stats")
    public ResponseEntity<?> stat(@RequestParam(required = false, name = "password") String password) {
        if ("super_secret".equals(password)) {
            return ResponseEntity.ok().body(cinema.getStatistics());
        }
        //return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "The password is wrong!"));
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "The password is wrong!");
    }


}

