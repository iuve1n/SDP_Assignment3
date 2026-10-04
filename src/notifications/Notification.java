package notifications;

public abstract class Notification {
    private final String id;
    private final String message;
    private Channel implementation;

    protected Notification(String id, String message, Channel implementation) {
        this.id = id;
        this.message = message;
        this.implementation = implementation;
    }

    public abstract String execute();

    public void setImplementation(Channel implementation) {
        this.implementation = implementation;
    }

    public String getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    protected String send(String preparedMessage) {
        return implementation.send(id, preparedMessage);
    }
}
