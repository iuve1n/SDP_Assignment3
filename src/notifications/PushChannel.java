package notifications;

public class PushChannel implements Channel {
    @Override
    public String send(String notificationId, String message) {
        return "PUSH notification envelope [id=" + notificationId
                + ", message=" + message + "]";
    }
}
