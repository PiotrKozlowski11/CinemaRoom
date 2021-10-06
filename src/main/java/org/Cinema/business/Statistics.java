package org.Cinema.business;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class represents statistics of a cinema: Current income, number of available seats, number of purchased tickets
 */
public class Statistics {
    @JsonProperty("current_income")
    private final int income;
    @JsonProperty("number_of_available_seats")
    private final int availableSeats;
    @JsonProperty("number_of_purchased_tickets")
    private final int purchasedTickets;

    /**
     * All args constructor
     *
     * @param income           current income
     * @param availableSeats   number of available seats
     * @param purchasedTickets number of purchased tickets
     */
    public Statistics(int income, int availableSeats, int purchasedTickets) {
        this.income = income;
        this.availableSeats = availableSeats;
        this.purchasedTickets = purchasedTickets;
    }

    /**
     * Getter
     *
     * @return current income
     */
    public int getIncome() {
        return income;
    }

    /**
     * Getter
     *
     * @return number of available seats
     */
    public int getAvailableSeats() {
        return availableSeats;
    }

    /**
     * Getter
     *
     * @return number of purchased tickets
     */
    public int getPurchasedTickets() {
        return purchasedTickets;
    }
}
