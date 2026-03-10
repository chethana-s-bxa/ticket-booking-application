package com.ticketbooking.util;

import com.ticketbooking.model.TicketInventory;
import com.ticketbooking.model.TicketInventoryAtomic;

public class BookingSummary {
    private final TicketInventoryAtomic inventory;
    private final long executionTime;

    public BookingSummary(TicketInventoryAtomic inventory, long executionTime) {
        this.inventory = inventory;
        this.executionTime = executionTime;
    }

    public void printSummary(){
        System.out.println("-------------------------------------------------");
        System.out.println("SUMMARY");
        System.out.println("-------------------------------------------------");
        System.out.println("Initial tickets: "+inventory.getInitialTickets());
        System.out.println("Total tickets sold: "+inventory.getTotalTicketsSold());
        System.out.println("Remaining tickets: "+inventory.getAvailableTickets());
        System.out.println("Execution time: "+executionTime+"ms");
    }
}
