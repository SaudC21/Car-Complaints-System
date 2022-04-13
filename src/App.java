import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws FileNotFoundException {

        // Declaration of files and scanners
        File complaintsCatalog = new File("Car Complaints.txt");
        Scanner readFile = new Scanner(complaintsCatalog);
        Scanner in = new Scanner(System.in);

        introMessage(); // This method to welcome the user and check either he/she wants to use the system or not
        steps(in); // This method will show the user our services

    }

    public static int introMessage() {
        // Variables declaration
        int userInput = 0;

        // Printing introduction to user
        System.out.println("==========================================");
        System.out.println("            Car Complaints System");
        System.out.println("==========================================");
        System.out.println("brief description \n");
        System.out.println("[ Through enforcing vehicle performance standards \n" +
                " and partnerships with state and local governments, \n" +
                " NHTSA reduces deaths, injuries and economic losses from motor vehicle crashes. ]\n");

        System.out.print("To start using our services please enter '1'. Otherwise enter '0' to exit: ");

        while (true) {
            try {
                // Read user input and check either to use the system or exit
                Scanner in = new Scanner(System.in);
                userInput = in.nextInt();
                if (userInput == 1) { // Use the system
                    break;
                } else if (userInput == 0) { // Exit the system
                    System.out.println("\n*** Thank you for using our system, See you! ***");
                    System.exit(0);
                } else { // any number other than 1 or 0
                    System.out.print("Please either enter 1 or 0: ");
                    continue;
                }
            } catch (InputMismatchException e) { // Catch if user entered characters
                System.err.print("ERROR: Please enter 1 or 0: ");
                continue;
            }
        }

        return userInput;
    }

    public static void steps(Scanner in) {
        // Printing to user Services Menu

        int userInput;


        while (true) {
            try {
                System.out.println("\n==========================================");
                System.out.println("              Services Menu");
                System.out.println("==========================================");
                System.out.println("1. Check current complaints");
                System.out.println("2. Add new complaints");
                System.out.println("3. Cars companies complaints");
                System.out.println("4. About us");
                System.out.println("5. Exit");
                System.out.print("Please enter your choice: ");

                // Read user input and check either to use the system or exit
                Scanner input = new Scanner(System.in);
                userInput = input.nextInt();

                if (userInput == 2) {
                    // The following lines are 'temporary' to check singleton design pattern and it will be used efficiently in advance
                    Database db = Database.getInstance();
                    PrintWriter writer = db.getWriter();
                    System.out.println(db.toString());
                    writer.println("Hello World!");
                } else if (userInput == 4) // This choice will invoke the 'About Us' choice
                {
                    System.out.println(" " +
                            " \n- [Who are we?]: We are two students from King Abdulaziz University, Faculty of Computing and Information" +
                            "Technology." +
                            " Developing a Car Complaints system." +
                            " \n- [Our vision]: Aiming to reduce the number of" +
                            " accidents caused by malfunctioning cars from the factory.");


                } else if (userInput == 5) // Exit the program
                {
                    System.out.print("\n*** Thank you for using our system, See you! ***");
                    System.exit(0);
                } else if (userInput < 0 || userInput > 5)// any valid choice other than the defined ones
                {
                    System.out.println("\nPlease check the menu again, then enter your choice");
                    continue;
                }

            } catch (InputMismatchException exception) {
                System.err.println("** Please enter a valid number **\n ");
                continue;
            }
        }
    }
}
