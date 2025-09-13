package dev.gutemberg.concurrency.timer;

import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class CountdownTimer {
    public static void main(String[] args) {
        final var scanner = new Scanner(System.in);
        System.out.println("Insert a value to start (00:00:00):");
        final var input = scanner.next().split(":");
        int hours = Integer.parseInt(input[0]);
        int minutes = Integer.parseInt(input[1]);
        int seconds = Integer.parseInt(input[2]);
        AtomicInteger totalTimeInSeconds = new AtomicInteger(hours * 60 * 60 + minutes * 60 + seconds);
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            if (totalTimeInSeconds.get() == 0) {
                System.out.println("Time done!");
                return;
            }
            totalTimeInSeconds.getAndDecrement();
            int hours2 = totalTimeInSeconds.get() / 3600;
            int minutes2 = (totalTimeInSeconds.get() % 3600) / 60;
            int seconds2 = totalTimeInSeconds.get() % 60;
            System.out.printf("%02d:%02d:%02d\n", hours2, minutes2, seconds2);
        }, 0, 1, TimeUnit.SECONDS);
    }
}
