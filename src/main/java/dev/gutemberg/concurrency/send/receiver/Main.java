package dev.gutemberg.concurrency.send.receiver;

public class Main {
    public static void main(final String[] args) {
        final var data = new Data();
        final var sender = new Thread(new Sender(data));
        final var receiver = new Thread(new Receiver(data));
        sender.start();
        receiver.start();
    }
}
