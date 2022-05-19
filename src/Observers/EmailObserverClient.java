package Observers;

import Utilities.SendEmail;

public class EmailObserverClient extends MainObserver {

    public EmailObserverClient(String recipient){
        super.setRecipient(recipient);

    }
    @Override
    public void update(String message){
        SendEmail.send("A new episode is out! ",  message, getRecipient());
        System.out.println("\n >> Email Client :: " + super.getRecipient() + " " + message);
     }
}
