package Subjects;

import Observers.MainObserver;

public interface Subject {

    public void subscribe(MainObserver o);
    public void unsubscribe(MainObserver o);
    public void notifyUpdate(String m);

}
