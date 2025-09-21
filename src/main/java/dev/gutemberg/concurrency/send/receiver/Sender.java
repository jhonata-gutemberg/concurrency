package dev.gutemberg.concurrency.send.receiver;

import java.util.concurrent.ThreadLocalRandom;

public class Sender implements Runnable {
    private final Data data;

    public Sender(final Data data) {
        this.data = data;
    }

    @Override
    public void run() {
        final var packets = new String[]{
                "First packet",
                "Second packet",
                "Third packet",
                "Fourth packet",
                "End"
        };
        for (final var packet: packets) {
            data.send(packet);
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 5000));
            } catch (final InterruptedException exception) {
                Thread.currentThread().interrupt();
                System.out.println("Thread interrupted");
            }
        }
    }
}
