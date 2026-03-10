package com.ticketbooking;

import com.ticketbooking.model.TicketInventory;
import com.ticketbooking.model.TicketInventoryAtomic;
import com.ticketbooking.service.BookingSimulationService;
import com.ticketbooking.util.BookingSummary;

public class TicketBookingApplication {
    public static void main(String[] args) {
        TicketInventoryAtomic inventory = new TicketInventoryAtomic(500);
        BookingSimulationService simulationService = new BookingSimulationService(inventory);
        long executionTime = simulationService.startSimulation();
        BookingSummary summary = new BookingSummary(inventory,executionTime);
        summary.printSummary();
    }
}
