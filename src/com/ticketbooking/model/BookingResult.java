package com.ticketbooking.model;

public class BookingResult {
    private final boolean success;
    private final int remainingTickets;

    public BookingResult(boolean success, int remainingTickets) {
        this.success = success;
        this.remainingTickets = remainingTickets;
    }

    public boolean isSuccess() {
        return success;
    }

    public int getRemainingTickets() {
        return remainingTickets;
    }
}
