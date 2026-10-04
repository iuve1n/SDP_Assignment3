package notifications;

public class Reminder extends Notification {
    public Reminder(String id, String message, Channel implementation) {
        super(id, message, implementation);
    }

    @Override
    public String execute() {
        return send("Reminder: " + getMessage());
    }
}
