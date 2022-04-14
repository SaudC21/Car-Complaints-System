import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws FileNotFoundException {

        Operation operation = new Operation();
        operation.introMessage(); // This method to welcome the user and check either he/she wants to use the system or not
        operation.steps(); // This method will show the user our services

    }

}
