package dev.gutemberg.concurrency.timer;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CountdownTimerApp extends Application {
    public static void main(final String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage stage) {
        stage.setScene(new Scene(createContent(), 300, 300));
        stage.show();
    }

    private Parent createContent() {
        return new StackPane(new Text("Hello!"));
    }
}
