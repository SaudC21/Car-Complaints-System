package Subjects;

import java.util.ArrayList;
import Observers.MainObserver;

public class MessageSubject implements Subject {
    ArrayList<MainObserver> arr = new ArrayList<>();

    @Override
    public void subscribe(MainObserver o) {
        System.out.println("[!] You have been Subscribed to: " + o.getRecipient());
        arr.add(o);
    }

    @Override
    public void unsubscribe(MainObserver o) {
        System.out.println("[!] You have been unsubscribed to: " + o.getRecipient());
        arr.remove(o);
    }

    @Override
    public void notifyUpdate(String m) {
        for (MainObserver o: arr)
        {
            o.update(m);
        }
    }
}
