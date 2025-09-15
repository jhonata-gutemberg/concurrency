package dev.gutemberg.concurrency.timer.components;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.material2.Material2AL;

public class TimePickerBuilder {
    public static Node build() {
        final var layout = new VBox(createIncrementContainer(), createTimerContainer(), createDecrementContainer());
        layout.setSpacing(10);
        layout.setAlignment(Pos.CENTER);
        return layout;
    }

    private static Node createIncrementContainer() {
        final var incrementHours = createIncrementButton();
        final var incrementMinutes = createIncrementButton();
        final var incrementSeconds = createIncrementButton();
        final var borderPane = new BorderPane();
        borderPane.setLeft(incrementHours);
        borderPane.setCenter(incrementMinutes);
        borderPane.setRight(incrementSeconds);
        StackPane.setMargin(borderPane, new Insets(0, 28, 0, 28));
        return new StackPane(borderPane);
    }

    private static Node createTimerContainer() {
        final var hoursInput = createTimeInput();
        Platform.runLater(hoursInput::requestFocus);
        final var minutesInput = createTimeInput();
        final var secondsInput = createTimeInput();
        final var timerContainer = new HBox(
                hoursInput,
                createDivisor(),
                minutesInput,
                createDivisor(),
                secondsInput
        );
        timerContainer.getStyleClass().add("time-container");
        timerContainer.setSpacing(10);
        return timerContainer;
    }

    private static Node createDecrementContainer() {
        final var decrementHours = createDecrementButton();
        final var decrementMinutes = createDecrementButton();
        final var decrementSeconds = createDecrementButton();
        final var borderPane = new BorderPane();
        borderPane.setLeft(decrementHours);
        borderPane.setCenter(decrementMinutes);
        borderPane.setRight(decrementSeconds);
        StackPane.setMargin(borderPane, new Insets(0, 28, 0, 28));
        return new StackPane(borderPane);
    }

    private static Button createIncrementButton() {
        final var incrementButton = createController();
        final var icon = new FontIcon(Material2AL.KEYBOARD_ARROW_UP);
        incrementButton.setGraphic(icon);
        return incrementButton;
    }

    private static Button createDecrementButton() {
        final var decrementButton = createController();
        final var icon = new FontIcon(Material2AL.KEYBOARD_ARROW_DOWN);
        decrementButton.setGraphic(icon);
        return decrementButton;
    }

    private static Button createController() {
        final var incrementButton = new Button();
        incrementButton.getStyleClass().add("controller");
        return incrementButton;
    }

    private static TextField createTimeInput() {
        final var timeInput = new TextField();
        timeInput.setText("00");
        timeInput.getStyleClass().add("time-input");
        timeInput.setEditable(false);
        return timeInput;
    }

    private static Node createDivisor() {
        final var divisor = new Text(":");
        divisor.getStyleClass().add("divisor");
        return divisor;
    }
}
