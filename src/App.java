import Observers.EmailObserverClient;
import Observers.MainObserver;
import Observers.smsObserver;
import Subjects.MessageSubject;
import Subjects.Subject;

public class App {
    public static void main(String[] args) {
        Operation operation = new Operation();
        operation.introMessage();
        operation.steps();

        MainObserver emailClient = new EmailObserverClient("Abdullah.m.bajaman@gmail.com");
        MainObserver smsClient = new smsObserver("+966000000000");
        Subject s = new MessageSubject();

        s.subscribe(smsClient);
        s.subscribe(emailClient);
    }



}