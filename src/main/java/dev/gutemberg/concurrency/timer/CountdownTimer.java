package dev.gutemberg.concurrency.timer;

import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class CountdownTimer {
    public static void main(final String[] args) {
        final var totalTimeInSeconds = new AtomicInteger(getTimerValueInSeconds());
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            if (totalTimeInSeconds.get() == 0) {
                System.out.println("Time done!");
                return;
            }
            displayTimer(totalTimeInSeconds.decrementAndGet());
        }, 0, 1, TimeUnit.SECONDS);
    }

    public static int getTimerValueInSeconds() {
        final var scanner = new Scanner(System.in);
        System.out.println("Insert a value to start (00:00:00):");
        final var input = scanner.next().split(":");
        int hours = Integer.parseInt(input[0]);
        int minutes = Integer.parseInt(input[1]);
        int seconds = Integer.parseInt(input[2]);
        return hours * 3600 + minutes * 60 + seconds;
    }

    public static void displayTimer(int totalTimeInSeconds) {
        int hours = totalTimeInSeconds / 3600;
        int minutes = (totalTimeInSeconds % 3600) / 60;
        int seconds = totalTimeInSeconds % 60;
        System.out.printf("%02d:%02d:%02d\n", hours, minutes, seconds);
    }
}
