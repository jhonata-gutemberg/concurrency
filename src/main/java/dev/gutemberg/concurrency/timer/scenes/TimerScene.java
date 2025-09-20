package dev.gutemberg.concurrency.timer.scenes;

import dev.gutemberg.concurrency.timer.events.TimerEvent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class TimerScene {
    private final double width;
    private final double height;
    private final Text result = new Text("00:00:00");

    public TimerScene(final double width, final double height) {
        this.width = width;
        this.height = height;
    }

    public Scene create() {
        result.getStyleClass().add("time-input");
        result.setFill(Color.WHITE);
        final var container = new StackPane(result);
        container.getStyleClass().add("container");
        final var scene =  new Scene(container, width, height);
        scene.getStylesheets().add(
                Objects.requireNonNull(getClass().getResource("/css/global.css")).toExternalForm());
        return scene;
    }

    public void start(final TimerEvent timerEvent) {
        final var timeInSeconds = new AtomicInteger(timerEvent.getTimeInSeconds());
        final AtomicReference<ScheduledFuture<?>> futureRef = new AtomicReference<>();
        final var scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        final ScheduledFuture<?> future = scheduledExecutorService.scheduleAtFixedRate(() -> {
            if (timeInSeconds.get() == 0) {
                futureRef.get().cancel(false);
                scheduledExecutorService.shutdown();
                return;
            }
            updateResult(timeInSeconds.decrementAndGet());
        }, 0, 1, TimeUnit.SECONDS);
        futureRef.set(future);
    }

    void updateResult(final int timeInSeconds) {
        int hours = timeInSeconds / 3600;
        int minutes = (timeInSeconds % 3600) / 60;
        int seconds = timeInSeconds % 60;
        result.setText("%02d:%02d:%02d".formatted(hours, minutes, seconds));
    }
}
