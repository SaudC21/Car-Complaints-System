import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Operation {
    static Scanner input = new Scanner(System.in);

    static ArrayList<Car> cars = new ArrayList<>();
    static Database db = Database.getInstance();
    static HashSet <String>  uniqueCarsList;

    //TODO: to enable sending the email.
    //TODO: implementing the 'Template' design pattern


    public static void closingDialog() {
        System.out.println("\n*** Thank you for using our system, See you! ***");
        System.exit(0);
    }

    public static void aboutUs() {
        System.out.println(
                """ 
                        \n- [Who are we?]: We are two students from King Abdulaziz University, Faculty of Computing and Information Technology. Developing a Car Complaints system.
                        - [Our vision]: Aiming to reduce the number of accidents caused by malfunctioning cars from the factory.
                           """);
    }

    public static void showDialog() {
        // Printing to user Services Menu
        System.out.println("\n==========================================");
        System.out.println("              Services Menu");
        System.out.println("==========================================");
        System.out.println("1. Cars companies complaints by brand");
        System.out.println("2. Check all complaints");
        System.out.println("3. About us");
        System.out.println("4. Exit");
        System.out.print("Please enter your choice: ");
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

    public static void complaintsBrands() {
        System.out.println();
        printUniqueCars();
        System.out.println();
        System.out.print("\nEnter a brand name to search for: ");
        String brandName = input.next();//
        simulateNetworkLatency();
        getComplaintsByBrands(brandName);
    }

    public static void steps() {
        int userInput;

        while (true) {
            try {
                showDialog();
                // Read user input and check either to use the system or exit
                userInput = input.nextInt();
                if (userInput == 1) {
                    complaintsBrands();
                } else if (userInput == 2) {
                    getAllComplaints();
                } else if (userInput == 3) // This choice will invoke the 'About Us' choice
                {
                    aboutUs();
                } else if (userInput == 4) // Exit the program
                {
                    closingDialog();
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
        String choice;
        System.out.println();
        boolean flag = false;

        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i).getBrand().equalsIgnoreCase(brand)) {
                System.out.println(cars.get(i));
            }
        }

        System.out.print("\nSearch for another brand? Y/N: ");
        choice = input.next();
        while (!flag) {
            try {
                if (choice.equalsIgnoreCase("N") || choice.equalsIgnoreCase("NO")) {
                    steps();
                    flag = true;
                } else if (choice.equalsIgnoreCase("Y") || choice.equalsIgnoreCase("YES")) {
                    printUniqueCars();
                    System.out.print("\nEnter another brand to search for: ");
                    String choiceOfBrand = input.next();
                    getComplaintsByBrands(choiceOfBrand);
                } else {
                    throw new InputMismatchException();
                }
            } catch (InputMismatchException exception) {
                System.err.println("You have entered an invalid input\n");
                System.out.println();
                System.out.print("Please enter Y or N: ");
                choice = input.next();
            }
        }
    }

    public static void getAllComplaints() {
        for (Car c : cars) {
            System.out.println(c);
        }
    }

    public static void retrieveCars() throws FileNotFoundException {
        Scanner in = new Scanner(new File(db.getFileName()));
        StringTokenizer st = new StringTokenizer(in.nextLine(), ",");
        int count = 1;

        // retrieving from the database to add ..
        while (st.hasMoreTokens())
        {
            String brand = st.nextToken();
            String model = st.nextToken();
            int year = Integer.parseInt(st.nextToken());
            int vehicleNumber = Integer.parseInt(st.nextToken());
            String issue = st.nextToken();
            cars.add(new Car(brand, model, year, vehicleNumber, issue));
            if (in.hasNext())
                st = new StringTokenizer(in.nextLine(), ",");
        }


        uniqueCarsList = new HashSet();   // Adding elements to a unique set for printing purposes ..
        for (int i = 0; i < cars.size(); i++)
        {
            uniqueCarsList.add(cars.get(i).getBrand());
        }
    }

    public static void printUniqueCars() {
         Iterator<String> i = uniqueCarsList.iterator();
        while (i.hasNext()) {
            System.out.println(i.next());
        }
    }
    public static void simulateNetworkLatency() {
        try {
            System.out.println();
            for (int i = 0; i <= 10; i++) {
                System.out.print(".");
                Thread.sleep(250);
                if (i == 5) {
                    System.out.print(" Fetching ");
                }
            }
            System.out.println("\n");
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
