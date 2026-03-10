package com.ticketbooking.model;


import java.util.concurrent.atomic.AtomicInteger;

public class TicketInventoryAtomic {

    private final int INITIAL_TICKETS;

    private final AtomicInteger availableTickets;
    private final AtomicInteger totalTicketsSold;

    public TicketInventoryAtomic(int initialTickets) {
        System.out.println("Using atomic constructor");
        this.INITIAL_TICKETS = initialTickets;
        this.availableTickets = new AtomicInteger(initialTickets);
        this.totalTicketsSold = new AtomicInteger(0);
    }

    public int getInitialTickets() {
        return INITIAL_TICKETS;
    }

    public int getAvailableTickets() {
        return availableTickets.get();
    }

    public int getTotalTicketsSold() {
        return totalTicketsSold.get();
    }

    public boolean bookTickets(int requestedTickets) {

        while (true) {

            int currentTickets = availableTickets.get();

            if (requestedTickets > currentTickets) {
                return false;
            }

            int newRemaining = currentTickets - requestedTickets;

            if (availableTickets.compareAndSet(currentTickets, newRemaining)) {
                totalTicketsSold.addAndGet(requestedTickets);
                return true;
            }
        }
    }
}
