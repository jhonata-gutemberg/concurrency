package dev.gutemberg.concurrency.timer;

import dev.gutemberg.concurrency.timer.events.TimerEvent;
import dev.gutemberg.concurrency.timer.scenes.CreateTimerScene;
import dev.gutemberg.concurrency.timer.scenes.TimerScene;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.util.Objects;

public class CountdownTimer extends Application {
    private static final double STAGE_WIDTH = 320;
    private static final double STAGE_HEIGHT = 350;

    public static void main(final String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage stage) {
        final var icon = new Image(Objects.requireNonNull(getClass().getResource("/icons/app.png"))
                .toExternalForm());
        final var createTimerScene = new CreateTimerScene(STAGE_WIDTH, STAGE_HEIGHT);
        final var timerScene = new TimerScene(STAGE_WIDTH, STAGE_HEIGHT);
        stage.setScene(createTimerScene.create());
        stage.addEventHandler(TimerEvent.START, timerEvent -> {
            stage.setScene(timerScene.create());
            timerScene.start(timerEvent);
        });
        stage.setResizable(false);
        stage.setTitle("Timer");
        stage.getIcons().add(icon);
        stage.show();
    }
}
