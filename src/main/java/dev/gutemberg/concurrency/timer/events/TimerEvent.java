package dev.gutemberg.concurrency.timer.events;

import javafx.event.Event;
import javafx.event.EventType;

public class TimerEvent extends Event {
    public static final EventType<TimerEvent> START = new EventType<>("START");

    public TimerEvent(final EventType<? extends Event> eventType) {
        super(eventType);
    }
}
