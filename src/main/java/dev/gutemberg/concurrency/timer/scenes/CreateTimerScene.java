package dev.gutemberg.concurrency.timer.scenes;

import dev.gutemberg.concurrency.timer.components.TimePicker;
import dev.gutemberg.concurrency.timer.events.TimerEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import org.kordamp.ikonli.javafx.FontIcon;
import java.util.Objects;

public class CreateTimerScene {
    private final double width;
    private final double height;
    private final TimePicker timePicker = new TimePicker();

    public CreateTimerScene(final double width, final double height) {
        this.width = width;
        this.height = height;
    }

    public Scene create() {
        final var layout = createLayout();
        StackPane.setMargin(layout, new Insets(20));
        final var container = new StackPane(layout);
        container.getStyleClass().add("container");
        final var scene = new Scene(container, width, height);
        scene.getStylesheets().add(
                Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());
        return scene;
    }

    public Node createLayout() {
        final var layout = new BorderPane();
        final var title = new Label("Create new timer");
        title.getStyleClass().add("title");
        layout.setTop(title);
        layout.setCenter(timePicker.create());
        final var button = createActionButton();
        BorderPane.setAlignment(button, Pos.CENTER);
        layout.setBottom(button);
        return layout;
    }

    public Button createActionButton() {
        final var button = new Button();
        final var icon = new FontIcon("mdmz-play_arrow");
        icon.setIconSize(24);
        button.setGraphic(icon);
        button.setMaxWidth(Double.MAX_VALUE);
        button.getStyleClass().add("action");
        button.setOnAction(actionEvent -> {
            final int timeInSeconds = timePicker.getHours() * 3600
                    + timePicker.getMinutes() * 60
                    + timePicker.getSeconds();
            button.fireEvent(new TimerEvent(TimerEvent.START, timeInSeconds));
        });
        return button;
    }
}
