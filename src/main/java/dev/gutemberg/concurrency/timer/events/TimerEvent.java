package dev.gutemberg.concurrency.timer.events;

import javafx.event.Event;
import javafx.event.EventType;

public class TimerEvent extends Event {
    public static final EventType<TimerEvent> START = new EventType<>("START");
    private final int timeInSeconds;

    public TimerEvent(final EventType<? extends Event> eventType, final int timeInSeconds) {
        super(eventType);
        this.timeInSeconds = timeInSeconds;
    }

    public int getTimeInSeconds() {
        return timeInSeconds;
    }
}
