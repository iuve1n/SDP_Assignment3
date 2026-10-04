# Assignment 3: Bridge pattern

| Field | Value |
| --- | --- |
| Student | Assemgazy Olzhas |
| Group | SE-2528 |
| Topic | B: Notifications |
| Repository | https://github.com/iuve1n/SDP_Assignment3 |
| Base commit | `5e902fb6be0726e8e82c5097667815f71f66c352` |

## Project overview

This Java application uses the Bridge pattern to separate notification types from delivery channels. A `Reminder` or `UrgentAlert` can use email, SMS, or push delivery without creating a subclass for every combination. The channel on an existing notification can also be replaced at runtime.

The project uses Java 17 and has no external dependencies.

## Role map

| Bridge role | Class | Source path |
| --- | --- | --- |
| Abstraction | `Notification` | `src/notifications/Notification.java` |
| A1 | `Reminder` | `src/notifications/Reminder.java` |
| A2 | `UrgentAlert` | `src/notifications/UrgentAlert.java` |
| Implementor | `Channel` | `src/notifications/Channel.java` |
| I1 | `EmailChannel` | `src/notifications/EmailChannel.java` |
| I2 | `SmsChannel` | `src/notifications/SmsChannel.java` |
| I3 | `PushChannel` | `src/notifications/PushChannel.java` |
| Client | `Main` | `src/Main.java` |

## Code locations

| Requirement | Location |
| --- | --- |
| Interface-typed bridge field | `Notification.java`, field `implementation` |
| Abstraction operation | `Notification.java`, method `execute()` |
| Runtime replacement | `Notification.java`, method `setImplementation(...)` |
| Same-object check | `Main.java`, method `runSwitchCheck(...)` |

## Build and run

Run these commands from the project folder:

```bash
javac --release 17 -encoding UTF-8 -d out "@sources.txt"
java -cp out Main --demo
```

The demo does not require input. It calculates each PASS or FAIL result by comparing the actual result with the expected value.

## Expected checks

| Check | Expected result |
| --- | --- |
| T1 | `Reminder` through `EmailChannel`, with a normal reminder inside an email envelope |
| T2 | The same reminder data through `SmsChannel` on one line |
| T3 | `UrgentAlert` through `EmailChannel`, with the message marked `URGENT` |
| T4 | The same urgent alert data through `SmsChannel` |
| T5 | The same `Reminder` object changes from email to SMS while its ID and message stay unchanged |
| T6 | `Reminder` through the added `PushChannel` |
| T7 | `UrgentAlert` through the added `PushChannel` |

The final line should be `SUMMARY: 7/7 PASS`. The captured run is stored in `demo-output.txt`, and `extension.diff` shows the source changes made for I3.
