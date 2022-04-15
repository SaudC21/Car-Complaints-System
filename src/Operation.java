import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Operation {

    static ArrayList<Car> cars = new ArrayList<>();
    static Database db = Database.getInstance();

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
                    retrieveCars();
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
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        return userInput;
    }

    public static void steps() {
        boolean flag = true;
        int userInput;

        while (true) {
            try {
                // Printing to user Services Menu
                System.out.println("\n==========================================");
                System.out.println("              Services Menu");
                System.out.println("==========================================");
                System.out.println("1. Cars companies complaints by brand");
                System.out.println("2. Check all complaints");
                System.out.println("3. About us");
                System.out.println("4. Exit");
                System.out.print("Please enter your choice: ");

                // Read user input and check either to use the system or exit
                Scanner input = new Scanner(System.in);
                userInput = input.nextInt();

                if (userInput == 1) {
                    int iterator = 1;
                    System.out.println();
                    for (Car c : cars) {
                        System.out.println(iterator++ + ". " + c.getBrand()); // TODO: fixing printing only unique values of brands.
                    }
                    System.out.println();
                    System.out.print("\nEnter a brand name to search for: ");
                    String brandName = input.next();// TODO: error handling.
                    getComplaintsByBrands(brandName);
                } else if (userInput == 2) {
                    for (Car c : cars) {
                        System.out.println(c);
                    }
                } else if (userInput == 3) // This choice will invoke the 'About Us' choice
                {
                    System.out.println(" " +
                            " \n- [Who are we?]: We are two students from King Abdulaziz University, Faculty of Computing and Information" +
                            "Technology." +
                            " Developing a Car Complaints system." +
                            " \n- [Our vision]: Aiming to reduce the number of" +
                            " accidents caused by malfunctioning cars from the factory.");


                } else if (userInput == 4) // Exit the program
                {
                    System.out.println("\n*** Thank you for using our system, See you! ***");
                    System.exit(0);
                } else if (userInput < 0 || userInput > 4)// any valid choice other than the defined ones
                {
                    System.out.println("\nPlease check the menu again, then enter your choice");
                    continue;
                }

            } catch (InputMismatchException exception) {
                System.err.println("** Please enter a valid number **\n");
                continue;
            }
        }
    }

    public static void getComplaintsByBrands(String brand) {
        Scanner in = new Scanner(System.in);
        System.out.println();
        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i).getBrand().equalsIgnoreCase(brand)) {
                System.out.println(cars.get(i));
            }
        }
        System.out.print("\n Search for another brand? Y/N: ");
        String choice = in.next();
        if (choice.contains("n")) {
            steps();
        } else {
            System.out.print("Enter another brand to search for: ");
            String choiceOfBrand = in.next();
            getComplaintsByBrands(choiceOfBrand);
        }
    }

    public static String getAllComplaints() {

        return "";
    }

    public static void retrieveCars() throws FileNotFoundException {
        //
        Scanner in = new Scanner(new File(db.getFileName()));
        StringTokenizer st = new StringTokenizer(in.nextLine(), ",");
        int count = 1;

        while (st.hasMoreTokens()) {
            String brand = st.nextToken();
            String model = st.nextToken();
            int year = Integer.parseInt(st.nextToken());
            int vehicleNumber = Integer.parseInt(st.nextToken());
            String issue = st.nextToken();
            cars.add(new Car(brand, model, year, vehicleNumber, issue));
            if (in.hasNext())
                st = new StringTokenizer(in.nextLine(), ",");
        }
    }
}
