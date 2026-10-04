package notifications;

public class EmailChannel implements Channel {
    @Override
    public String send(String notificationId, String message) {
        return "EMAIL envelope [id=" + notificationId + ", message=" + message + "]";
    }
}
