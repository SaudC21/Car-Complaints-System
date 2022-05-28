package Subjects;

import Observers.MainObserver;

import java.util.ArrayList;

public class MessageSubject implements Subject {
    ArrayList<MainObserver> subscribed = new ArrayList<>();
    ArrayList<MainObserver> unsubscribed = new ArrayList<>();

    @Override
    public void subscribe(MainObserver o) {
        System.out.println("\nYou have been Subscribed from (" + o.getRecipient() + ")");
        for (int i = 0; i < subscribed.size(); i++) {
            if(!o.getRecipient().equals(subscribed.get(i).getRecipient())) {
                subscribed.add(o);
            } else {
                System.out.println("You're already subscribed to our newsletter");
            }
        }
    }

    @Override
    public void unsubscribe(MainObserver o) {
        System.out.println("\nYou have been unsubscribed to: " + o.getRecipient());
        subscribed.remove(o);
    }

    @Override
    public void add(MainObserver o) {
        unsubscribed.add(o);
    }

    @Override
    public void remove(MainObserver o) {
        unsubscribed.remove(o);
    }

    @Override
    public void notifyUpdateSubscribed(String m) {
        for (MainObserver o: subscribed)
        {
            o.update(m);
        }
    }
    @Override
    public void notifyUpdateUnsubscribed(String m) {
        for (MainObserver o: unsubscribed)
        {
            o.update(m);
        }
    }
}
