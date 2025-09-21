![Concurrency](docs/assets/concurrency.png)

A small playground to explore core Java concurrency patterns using simple, focused examples:

- Countdown Timer (JavaFX): uses `ScheduledExecutorService`, `AtomicInteger`, and cooperative cancellation.
- Sender/Receiver (Console): demonstrates `wait/notifyAll` coordination with `synchronized` and two threads.

This project is built with Gradle, targets Java 21, and uses JavaFX for the timer UI.

## 📋 Requirements

- JDK: Java 21 (Gradle toolchain enforces this)
- Gradle: Wrapper included (`gradlew` / `gradlew.bat`)

Gradle pulls JavaFX modules automatically via the OpenJFX Gradle plugin.

## 🛠️ Build

- Unix/macOS: `./gradlew build`
- Windows: `gradlew.bat build`

Artifacts compile under `build/`.

## ▶️ Run Examples

### ⏱️ 1) JavaFX Countdown Timer (default app)

- Unix/macOS: `./gradlew run`
- Windows: `gradlew.bat run`

What it shows:
- A minimal timer UI where you pick HH:MM:SS and start.
- A single-thread `ScheduledExecutorService` decrements time every second.
- Uses `AtomicInteger` for the counter and cancels the scheduled task when it hits zero.

### 🔁 2) Sender/Receiver (console, wait/notifyAll)

Option A — Run directly with `java` after compiling classes (no extra deps used):

- Unix/macOS:
  ```bash
  ./gradlew classes && \
  java -cp build/classes/java/main dev.gutemberg.concurrency.send.receiver.Main
  ```
- Windows (PowerShell/CMD):
  ```powershell
  gradlew.bat classes && ^
  java -cp build\classes\java\main dev.gutemberg.concurrency.send.receiver.Main
  ```

Option B — From an IDE: Run class `dev.gutemberg.concurrency.send.receiver.Main`.

Option C — Temporarily switch `application.mainClass` in `build.gradle` to the sender/receiver main and run `gradlew run`.

## ⚡ Concurrency Highlights

- Timer:
  - `ScheduledExecutorService`: fixed-rate ticks; cancels via `ScheduledFuture`.
  - `AtomicInteger`: thread-safe countdown state.
  - Separation of concerns between scene setup and ticking logic.
- Sender/Receiver:
  - `synchronized`, `wait()`, `notifyAll()` to coordinate producers/consumers.
  - Alternates send/receive via a boolean flag to avoid busy-waiting.

## 🚀 Next Ideas to Explore

- `CompletableFuture` composition and timeouts.
- Custom thread pools and `ThreadFactory` naming.
- Locks/Conditions (`ReentrantLock`, `Condition`) vs. `synchronized`.
- Phaser/CyclicBarrier and countdown latches.
- Reactive pipelines (Flow API) and backpressure.
