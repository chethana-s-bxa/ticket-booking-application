package com.ticketbooking.service;

import com.ticketbooking.model.TicketInventory;
import com.ticketbooking.task.BookingRequest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class BookingSimulationService {
    private static final int TOTAL_USER_COUNT = 100;
    private final TicketInventory inventory;

    public BookingSimulationService(TicketInventory inventory) {
        this.inventory = inventory;
    }

    public long startSimulation() throws InterruptedException {
        long startTime = System.currentTimeMillis();
        ExecutorService executor = Executors.newFixedThreadPool(5);

        for(int i=1 ; i <= TOTAL_USER_COUNT ; i++){
            BookingRequest request = new BookingRequest("User-"+i, inventory);
            executor.submit(request);
        }
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);

        long endTime = System.currentTimeMillis();

        return endTime-startTime;
    }
}
