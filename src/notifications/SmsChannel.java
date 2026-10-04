package notifications;

public class SmsChannel implements Channel {
    @Override
    public String send(String notificationId, String message) {
        return "SMS [id=" + notificationId + ", message=" + message + "]";
    }
}
