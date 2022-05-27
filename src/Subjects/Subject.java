package Subjects;

import Observers.MainObserver;

public interface Subject {

    public void subscribe(MainObserver o);
    public void add(MainObserver o);
    public void remove(MainObserver o);
    public void unsubscribe(MainObserver o);
    public void notifyUpdateSubscribed(String m);
    public void notifyUpdateUnsubscribed(String m);

}
