package dev.gutemberg.concurrency.send.receiver;

import java.util.concurrent.ThreadLocalRandom;

public class Receiver implements Runnable {
    private final Data load;

    public Receiver(final Data load) {
        this.load = load;
    }

    @Override
    public void run() {
        for (var receivedMessage = load.receive(); !receivedMessage.equals("End"); receivedMessage = load.receive()) {
            System.out.println(receivedMessage);
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 5000));
            } catch (final InterruptedException exception) {
                Thread.currentThread().interrupt();
                System.out.println("Thread interrupted");
            }
        }
    }
}
