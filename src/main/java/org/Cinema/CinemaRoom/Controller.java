package org.Cinema.CinemaRoom;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

@RestController
public class Controller {


    Cinema cinema = new Cinema(9, 9);


    @GetMapping("/seats")
    public Cinema getCinema() {
        return cinema;
    }

    @PostMapping("/purchase")
    public ResponseEntity<?> buySeat(@RequestBody Seat seat) {
        //return seat;


        try {

            Seat selectedSeat = cinema.reserveSeat(seat);

            return ResponseEntity.ok(selectedSeat);
        } catch (IllegalArgumentException e) {
            //throw new ResponseStatusException(HttpStatus.BAD_REQUEST, illegalArgumentException.getMessage());
            //throw new WrongSeatExceptions(illegalArgumentException.getMessage());
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }


    }

    @PostMapping("/return")
    public Map<String, UUID> returnTicket(@RequestBody Seat seat) {
        //return seat;
        UUID uuid = UUID.randomUUID();
        return Map.of("token",uuid);


        /*
        try {

            Seat selectedSeat = cinema.reserveSeat(seat);

            return ResponseEntity.ok(selectedSeat);
        } catch (IllegalArgumentException e) {
            //throw new ResponseStatusException(HttpStatus.BAD_REQUEST, illegalArgumentException.getMessage());
            //throw new WrongSeatExceptions(illegalArgumentException.getMessage());
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }

         */


    }
}

