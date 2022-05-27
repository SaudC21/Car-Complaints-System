package Observers;

import Utilities.SendEmail;

public class EmailObserverClient extends MainObserver {

    public EmailObserverClient(String recipient){
        super.setRecipient(recipient);

    }
    @Override
    public synchronized void update(String message){
        SendEmail.send(" Complaints you requested",  message, getRecipient());
        System.out.println("........... Email has been sent to (" + super.getRecipient() + ") ...........");
        System.out.println("\n >> Message content :: " + "\n" + message);
    }
}
