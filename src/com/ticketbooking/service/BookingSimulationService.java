package com.ticketbooking.service;

import com.ticketbooking.model.TicketInventory;
//import com.ticketbooking.task.BookingRequest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class BookingSimulationService {
    private static final int THREAD_POOL_SIZE = 5;
    private static final int TOTAL_USER_COUNT = 1000;
    private final TicketInventory inventory;

    public BookingSimulationService(TicketInventory inventory) {
        this.inventory = inventory;
    }

    public long startSimulation(){
        long startTime = System.currentTimeMillis();
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

        for(int i=1 ; i <= TOTAL_USER_COUNT ; i++){
//            BookingRequest request = new BookingRequest("User-"+i, inventory);
            int userId =i;
            executor.submit(() -> processBooking("User-"+userId));
        }
        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            System.out.println("Execution is interrupted");
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();

        return endTime-startTime;
    }

    private void processBooking(String userName) {

        int requestedTickets = ThreadLocalRandom.current().nextInt(1, 4);

        boolean bookingResult = inventory.bookTickets(requestedTickets);

        if (bookingResult) {
            System.out.println(userName + " requested " + requestedTickets +
                    " tickets -> Booked -> Remaining: " +
                    inventory.getAvailableTickets());
        } else {
            System.out.println(userName + " requested " + requestedTickets +
                    " tickets -> Failed (Not enough tickets) -> Remaining: " +
                    inventory.getAvailableTickets());
        }
    }
}
