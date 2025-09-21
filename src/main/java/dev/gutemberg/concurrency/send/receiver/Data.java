package dev.gutemberg.concurrency.send.receiver;

public class Data {
    private String packet;
    private boolean transfer = true;

    public synchronized String receive() {
        while (transfer) {
            try {
                wait();
            } catch (final InterruptedException exception) {
                Thread.currentThread().interrupt();
                System.out.println("Thread interrupted");
            }
        }
        transfer = true;
        final var returnPacket = packet;
        notifyAll();
        return returnPacket;
    }

    public synchronized void send(final String packet) {
        while (!transfer) {
            try {
                wait();
            } catch (final InterruptedException exception) {
                Thread.currentThread().interrupt();
                System.out.println("Thread interrupted");
            }
        }
        transfer = false;
        this.packet = packet;
        notifyAll();
    }
}
