package notifications;

public interface Channel {
    String send(String notificationId, String message);
}
