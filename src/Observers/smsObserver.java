package Observers;

public class smsObserver extends MainObserver {
    public smsObserver(String  recipient){
        super.setRecipient(recipient);
    }

    @Override
    public void update(String message){
        System.out.println("\n >> SMS Client :: " + getRecipient() + " " + message);
    }
}
