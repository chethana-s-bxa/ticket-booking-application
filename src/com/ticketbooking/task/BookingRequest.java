package com.ticketbooking.task;

import com.ticketbooking.model.TicketInventory;

import java.util.concurrent.ThreadLocalRandom;

public class BookingRequest implements Runnable {

    private final String userName;
    private final TicketInventory inventory;

    public BookingRequest(String userName, TicketInventory inventory) {
        this.userName = userName;
        this.inventory = inventory;
    }

    @Override
    public void run() {
        int requestedTickets = ThreadLocalRandom.current().nextInt(1,4);
        boolean bookingResult = inventory.bookTickets(requestedTickets);

        if(bookingResult){
            System.out.println(userName+" requested "+requestedTickets+" tickets -> Booked -> Remaining:"+inventory.getAvailableTickets());
        }else{
            System.out.println(userName+" requested "+requestedTickets+" tickets -> Failed (Not enough tickets) -> Remaining:"+inventory.getAvailableTickets());
        }
    }
}
