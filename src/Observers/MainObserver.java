package Observers;

public abstract class MainObserver {
    private String recipient;

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public abstract void update(String m);
}
