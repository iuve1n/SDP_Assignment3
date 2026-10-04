package notifications;

public class UrgentAlert extends Notification {
    public UrgentAlert(String id, String message, Channel implementation) {
        super(id, message, implementation);
    }

    @Override
    public String execute() {
        return send("URGENT: " + getMessage());
    }
}
