package dev.gutemberg.concurrency.timer.scenes;

import dev.gutemberg.concurrency.timer.components.TimePickerBuilder;
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

public class CreateTimerSceneBuilder {
    private static final double SCENE_WIDTH = 320;
    private static final double SCENE_HEIGHT = 350;

    public static Scene build() {
        final var layout = createLayout();
        StackPane.setMargin(layout, new Insets(20));
        final var container = new StackPane(layout);
        container.getStyleClass().add("container");
        final var scene = new Scene(container, SCENE_WIDTH, SCENE_HEIGHT);
        scene.getStylesheets().add(
                Objects.requireNonNull(CreateTimerSceneBuilder.class.getResource("/style.css"))
                        .toExternalForm());
        return scene;
    }

    public static Node createLayout() {
        final var layout = new BorderPane();

        final var title = new Label("Create new timer");
        title.getStyleClass().add("title");
        layout.setTop(title);

        final var timePicker = TimePickerBuilder.build();
        layout.setCenter(timePicker);

        final var button = new Button();
        final var icon = new FontIcon("mdmz-play_arrow");
        icon.setIconSize(24);
        button.setGraphic(icon);
        button.setMaxWidth(Double.MAX_VALUE);
        button.getStyleClass().add("action");
        BorderPane.setAlignment(button, Pos.CENTER);
        layout.setBottom(button);

        return layout;
    }
}
