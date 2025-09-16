package dev.gutemberg.concurrency.timer;

import dev.gutemberg.concurrency.timer.scenes.CreateTimerSceneBuilder;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class CountdownTimer extends Application {
    public static void main(final String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage stage) {
        final var icon = new Image(Objects.requireNonNull(getClass().getResource("/icons/app.png"))
                .toExternalForm());
        stage.setScene(CreateTimerSceneBuilder.build());
        stage.setResizable(false);
        stage.setTitle("Timer");
        stage.getIcons().add(icon);
        stage.show();
    }

    private Parent createContent(final Stage stage) {
        final var hours = createNode();
        final var minutes = createNode();
        final var seconds = createNode();
        final var hbox = new HBox(hours, new Text(":"), minutes, new Text(":"), seconds);
        final var button = new Button("Start");
        button.setOnAction(event -> {
            final var result = new Text("Started!");
            stage.setScene(new Scene(new StackPane(result),350, 350));
            final var totalTimeInSeconds = new AtomicInteger(
                    Integer.parseInt(hours.getText()) * 3600
                            + Integer.parseInt(minutes.getText()) * 60
                            + Integer.parseInt(seconds.getText()));
            final AtomicReference<ScheduledFuture<?>> futureRef = new AtomicReference<>();
            ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
            final ScheduledFuture<?> future = scheduledExecutorService.scheduleAtFixedRate(() -> {
                if (totalTimeInSeconds.get() == 0) {
                    System.out.println("Time done!");
                    futureRef.get().cancel(false);
                    scheduledExecutorService.shutdown();
                    return;
                }
                displayTimer(totalTimeInSeconds.decrementAndGet(), result);
            }, 0, 1, TimeUnit.SECONDS);
            futureRef.set(future);
        });
        //button.setAlignment(Pos.TOP_CENTER);
        final var v = new VBox(new Text("Create new timer"), hbox, button);
        v.setSpacing(20);
        v.setAlignment(Pos.TOP_CENTER);
        return v;
    }

    private TextField createNode() {
        final var txt = new TextField("00");
        txt.setAlignment(Pos.CENTER);
        return txt;
    }

    private void displayTimer(int totalTimeInSeconds, final Text result) {
        int hours = totalTimeInSeconds / 3600;
        int minutes = (totalTimeInSeconds % 3600) / 60;
        int seconds = totalTimeInSeconds % 60;
        result.setText("%02d:%02d:%02d".formatted(hours, minutes, seconds));
    }
}
