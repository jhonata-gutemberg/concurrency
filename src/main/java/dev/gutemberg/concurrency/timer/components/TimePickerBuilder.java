package dev.gutemberg.concurrency.timer.components;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
    private int hours;
    private int minutes;
    private int seconds;
    private final TextField hoursInput = createTimeInput();
    private final TextField minutesInput = createTimeInput();
    private final TextField secondsInput = createTimeInput();

    public Node build() {
        final var layout = new VBox(createIncrementContainer(), createTimerContainer(), createDecrementContainer());
        layout.setSpacing(10);
        layout.setAlignment(Pos.CENTER);
        return layout;
    }

    private Node createIncrementContainer() {
        final var incrementHours = createIncrementButton();
        incrementHours.setOnAction(handleIncrementHour());
        incrementHours.setFocusTraversable(false);
        final var incrementMinutes = createIncrementButton();
        incrementMinutes.setOnAction(handleIncrementMinute());
        incrementMinutes.setFocusTraversable(false);
        final var incrementSeconds = createIncrementButton();
        incrementSeconds.setOnAction(handleIncrementSeconds());
        incrementSeconds.setFocusTraversable(false);
        final var borderPane = new BorderPane();
        borderPane.setLeft(incrementHours);
        borderPane.setCenter(incrementMinutes);
        borderPane.setRight(incrementSeconds);
        StackPane.setMargin(borderPane, new Insets(0, 22, 0, 22));
        return new StackPane(borderPane);
    }

    private Node createTimerContainer() {
        Platform.runLater(hoursInput::requestFocus);
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

    private Node createDecrementContainer() {
        final var decrementHours = createDecrementButton();
        decrementHours.setOnAction(handleDecrementHour());
        decrementHours.setFocusTraversable(false);
        final var decrementMinutes = createDecrementButton();
        decrementMinutes.setOnAction(handleDecrementMinute());
        decrementMinutes.setFocusTraversable(false);
        final var decrementSeconds = createDecrementButton();
        decrementSeconds.setOnAction(handleDecrementSeconds());
        decrementSeconds.setFocusTraversable(false);
        final var borderPane = new BorderPane();
        borderPane.setLeft(decrementHours);
        borderPane.setCenter(decrementMinutes);
        borderPane.setRight(decrementSeconds);
        StackPane.setMargin(borderPane, new Insets(0, 22, 0, 22));
        return new StackPane(borderPane);
    }

    private Button createIncrementButton() {
        final var incrementButton = createController();
        final var icon = new FontIcon(Material2AL.KEYBOARD_ARROW_UP);
        incrementButton.setGraphic(icon);
        return incrementButton;
    }

    private Button createDecrementButton() {
        final var decrementButton = createController();
        final var icon = new FontIcon(Material2AL.KEYBOARD_ARROW_DOWN);
        decrementButton.setGraphic(icon);
        return decrementButton;
    }

    private Button createController() {
        final var incrementButton = new Button();
        incrementButton.getStyleClass().add("controller");
        return incrementButton;
    }

    private TextField createTimeInput() {
        final var timeInput = new TextField();
        timeInput.setText("00");
        timeInput.getStyleClass().add("time-input");
        timeInput.setEditable(false);
        return timeInput;
    }

    private Node createDivisor() {
        final var divisor = new Text(":");
        divisor.getStyleClass().add("divisor");
        return divisor;
    }

    private EventHandler<ActionEvent> handleIncrementHour() {
        return event -> {
            hoursInput.requestFocus();
            hours++;
            if (hours > 99) {
                hours = 0;
            }
            displayTime();
        };
    }

    private EventHandler<ActionEvent> handleDecrementHour() {
        return event -> {
            hoursInput.requestFocus();
            hours--;
            if (hours < 0) {
                hours = 99;
            }
            displayTime();
        };
    }

    private EventHandler<ActionEvent> handleIncrementMinute() {
        return event -> {
            minutesInput.requestFocus();
            minutes++;
            if (minutes > 59) {
                minutes = 0;
            }
            displayTime();
        };
    }

    private EventHandler<ActionEvent> handleDecrementMinute() {
        return event -> {
            minutesInput.requestFocus();
            minutes--;
            if (minutes < 0) {
                minutes = 59;
            }
            displayTime();
        };
    }

    private EventHandler<ActionEvent> handleIncrementSeconds() {
        return event -> {
            secondsInput.requestFocus();
            seconds++;
            if (seconds > 59) {
                seconds = 0;
            }
            displayTime();
        };
    }

    private EventHandler<ActionEvent> handleDecrementSeconds() {
        return event -> {
            secondsInput.requestFocus();
            seconds--;
            if (seconds < 0) {
                seconds = 59;
            }
            displayTime();
        };
    }

    private void displayTime() {
        var format = "%02d";
        hoursInput.setText(format.formatted(hours));
        minutesInput.setText(format.formatted(minutes));
        secondsInput.setText(format.formatted(seconds));
    }
}
