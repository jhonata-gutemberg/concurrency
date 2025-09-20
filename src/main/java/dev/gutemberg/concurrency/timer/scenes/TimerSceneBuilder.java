package dev.gutemberg.concurrency.timer.scenes;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class TimerSceneBuilder {
    private final double width;
    private final double height;

    public TimerSceneBuilder(final double width, final double height) {
        this.width = width;
        this.height = height;
    }

    public Scene build() {
        final var result = new Text("Started!");
        return new Scene(new StackPane(result), width, height);
    }
}
