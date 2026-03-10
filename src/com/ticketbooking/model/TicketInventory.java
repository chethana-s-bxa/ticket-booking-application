package com.ticketbooking.model;

import java.util.concurrent.locks.ReentrantLock;

public class TicketInventory {
    private final int INITIAL_TICKETS;
    private int availableTickets;
    private int totalTicketsSold;

    private final ReentrantLock lock = new ReentrantLock(true);

    public TicketInventory(int initialTickets) {
        this.INITIAL_TICKETS = initialTickets;
        this.availableTickets = initialTickets;
        this.totalTicketsSold = 0;
    }

    public int getInitialTickets() {
        return INITIAL_TICKETS;
    }

    public int getAvailableTickets() {
        return availableTickets;
    }

    public int getTotalTicketsSold() {
        return totalTicketsSold;
    }

    public BookingResult bookTickets(int requestedTickets){
        lock.lock();
        try{
            if(availableTickets >= requestedTickets){
                availableTickets -= requestedTickets;
                totalTicketsSold += requestedTickets;

                return new BookingResult(true,availableTickets);
            }else{
                return new BookingResult(false,availableTickets);
            }
        }finally {
            lock.unlock();
        }
    }
}
