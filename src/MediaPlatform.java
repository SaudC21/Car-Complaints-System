import Observers.*;
import Subjects.*;

import java.util.Scanner;

public class MediaPlatform {

        MainObserver emailClient = new EmailObserverClient("Abdullah.m.bajaman@gmail.com");
        MainObserver smsClient = new smsObserver("+966000000000");
        Subject s = new MessageSubject();

        public static String  getParticipant()
        {
            Scanner scan = new Scanner(System.in);
            String email;
            System.out.println("please enter an email you want to" +
                    " receive all new updates on: ");
            email = scan.nextLine();
            return email;
        }
    }

