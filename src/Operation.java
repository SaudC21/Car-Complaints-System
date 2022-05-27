import Observers.*;
import Subjects.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Operation {

    static Scanner input = new Scanner(System.in);
    static ArrayList<Car> cars = new ArrayList<>();
    static Database db = Database.getInstance();
    static HashSet<String> uniqueCarsList;

    public static void introMessage() {
        // Variables declaration
        int userInput = 0;

        showWelcomeMessage();

        while (true) {
            try {
                // Read user input and check either to use the system or exit
                Scanner in = new Scanner(System.in);
                userInput = in.nextInt();
                if (userInput == 1) { // Use the system
                    initCars();
                    break;
                } else if (userInput == 0) { // Exit the system
                    System.out.println("\n*** Thank you for using our system, See you! ***");
                    System.exit(0);
                } else { // any number other than 1 or 0
                    System.out.print("Please either enter 1 or 0: ");
                    continue;
                }
            } catch (InputMismatchException e) { // Catch if user entered characters
                System.err.print("[!] ERROR: Please enter 1 or 0: ");
                continue;
            }
        }
    }

    public static void showWelcomeMessage() {
        // Printing introduction to user
        System.out.println("==========================================");
        System.out.println("            Car Complaints System");
        System.out.println("==========================================");
        System.out.println("Brief description \n");
        System.out.println("[ Through enforcing vehicle performance standards \n" +
                " and partnerships with state and local governments, \n" +
                " NHTSA reduces deaths, injuries and economic losses from motor vehicle crashes. ]\n");

        System.out.print("To start using our services please enter '1'. Otherwise enter '0' to exit: ");
    }

    public static void aboutUs() {
        System.out.print(
                """ 
                        \n- [Who are we?]: We are two students from King Abdulaziz University, Faculty of Computing and Information Technology. Developing a Car Complaints system.
                        - [Our vision]: Aiming to reduce the number of accidents caused by malfunctioning cars from the factory.
                           """);
        steps();
    }

    public static void showMenu() {
        // Printing to user Services Menu
        System.out.println("\n==========================================");
        System.out.println("              Services Menu");
        System.out.println("==========================================");
        System.out.println("1. Cars companies complaints by brand");
        System.out.println("2. Check all complaints");
        System.out.println("3. Send email");
        System.out.println("4. About us");
        System.out.println("5. Exit");
        System.out.print("Please enter your choice: ");
    }

    public static void complaintsBrands() {
        printUniqueCars();
        System.out.print("\nEnter a brand name to search for: ");
        String brandName = input.next();
        simulateNetworkLatency();
        getComplaintsByBrands(brandName);
    }

    public static void steps() {
        int userInput;

        try {
            showMenu();
            // Read user input and check either to use the system or exit
            userInput = input.nextInt();
            if (userInput == 1)
            {
                complaintsBrands();
            } else if (userInput == 2)
            {
                getAllComplaints();
            } else if (userInput == 3) // This choice will invoke the 'About Us' choice
            {
                sendingEmail();
            } else if (userInput == 4)
            {
                aboutUs();
            } else if (userInput == 5) // Exit the program
            {
                closingDialog();
            } else if (userInput < 0 || userInput > 4)// any valid choice other than the defined ones
            {
                System.out.println("\nPlease check the menu again, then enter your choice");
            }

        } catch (InputMismatchException exception) {
            System.err.println("** Please enter a valid number **\n");

        }

    }

    public static void sendingEmail()
    {
        System.out.print("Enter the brand you'd like to receive compliant about: ");
        String brand = input.next();
        System.out.print("Enter your email: ");
        String email = input.next();

        MainObserver emailClient = new EmailObserverClient(email);
        Subject s = new MessageSubject();

        s.add(emailClient);

        simulateEmailLatency();

        String message = getComplaintsByBrandsEmail(brand);
        if (message.equals("")){
            System.out.println("\"" + brand + "\"" + " could not be found within our database.");
        } else {
            s.notifyUpdateUnsubscribed(message);
            emailSubscription(s, emailClient);
        }
    }

    public static void emailSubscription(Subject s, MainObserver emailClient) {
        System.out.print("Would you like to subscribe to our newsletter with the previous email? Yes/No: ");
        String choice = input.next();
        while (true) {
            try {
                if (choice.equalsIgnoreCase("Y") || choice.equalsIgnoreCase("YES")) {
                    s.subscribe(emailClient);
                    closingDialog();
                } else if (choice.equalsIgnoreCase("N") || choice.equalsIgnoreCase("NO")) {
                    closingDialog();
                } else {
                    throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                System.err.print("[!] You have entered an invalid input");
                System.out.print("\n\nPlease enter Y or N: ");
                choice = input.next();
            }
        }
    }

    public static String getComplaintsByBrandsEmail(String brand) {
        System.out.println();
        StringBuffer brandComplaints = new StringBuffer();
        String chosenBrand = brand;

        // Print selected brand
        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i).getBrand().equalsIgnoreCase(chosenBrand)) {
                brandComplaints.append(cars.get(i).toString());
                brandComplaints.append("\n");
            }
        }
        String brands = brandComplaints.toString();
        return brands;
    }

    public static void printSelectedBrand(String brand){
        boolean flag2 = false;
        // Print selected brand
        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i).getBrand().equalsIgnoreCase(brand)) {
                System.out.println(cars.get(i).toString());
                flag2 = true;
            }
        }
        if (flag2 == false) {
            System.out.println("\"" + brand + "\"" + " could not be found within our database.");
        }
    }

    public static void getComplaintsByBrands(String brand) {
        System.out.println();
        boolean flag = false;

        printSelectedBrand(brand);

        System.out.print("\nSearch for another brand? Yes/No: ");
        String choice = input.next();
        while (!flag) {
            try {
                if (choice.equalsIgnoreCase("N") || choice.equalsIgnoreCase("NO")) {
                    steps();
                    flag = true;
                } else if (choice.equalsIgnoreCase("Y") || choice.equalsIgnoreCase("YES")) {
                    printUniqueCars();
                    System.out.print("\nEnter brand to search for: ");
                    String choiceOfBrand = input.next();
                    getComplaintsByBrands(choiceOfBrand);
                } else {
                    throw new InputMismatchException();
                }
            } catch (InputMismatchException exception) {
                System.err.print("[!] You have entered an invalid input");
                System.out.print("\n\nPlease enter Y or N: ");
                choice = input.next();
            }
        }
    }

    public static void getAllComplaints() {
        simulateNetworkLatency();
        for (Car c : cars) {
            System.out.println(c);
        }
        closingDialog();
    }

    public static void initCars() {
        Scanner in = null;
        try {
            in = new Scanner(new File(db.getFileName()));
        } catch (FileNotFoundException e) {
            System.err.println("Database file is not found.");
            System.exit(0);
        }

        StringTokenizer st = new StringTokenizer(in.nextLine(), ",");
        int count = 1;

        // retrieving from the database to add ..
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

        uniqueCarsList = new HashSet();   // Adding elements to a unique set for printing purposes ..
        for (int i = 0; i < cars.size(); i++) {
            uniqueCarsList.add(cars.get(i).getBrand());
        }
    }

    public static void printUniqueCars() {
        System.out.println();
        Iterator<String> i = uniqueCarsList.iterator();
        int iterator = 1;
        while (i.hasNext()) {
            System.out.println(iterator++ + ". " + i.next());
        }
    }

    public static void simulateEmailLatency() {
        try {
            System.out.println();
            for (int i = 0; i <= 20; i++) {
                System.out.print(".");
                Thread.sleep(500);
                if (i == 10) {
                    System.out.print(" Sending the Email, please bare with us ");
                }
            }
            System.out.print("\n");
        } catch (InterruptedException ex) {
            ex.printStackTrace();
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
            System.out.print("\n");
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public static void closingDialog() {
        System.out.println("\n*** Thank you for using our system, See you! ***");
        System.exit(0);
    }
}

