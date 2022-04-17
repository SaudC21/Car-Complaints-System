import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

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
        int userInput;

        while (true) {
            try {
                // Printing to user Services Menu
                System.out.println("\n==========================================");
                System.out.println("              Services Menu");
                System.out.println("==========================================");
                System.out.println("1. Cars companies complaints by brand");
                System.out.println("2. Check all complaints"); // TODO: implementing the 'Observer' design pattern to enable sending the email.
                System.out.println("3. About us");
                System.out.println("4. Exit");
                System.out.print("Please enter your choice: ");

                // Read user input and check either to use the system or exit
                Scanner input = new Scanner(System.in);
                userInput = input.nextInt();

                if (userInput == 1) {
                    System.out.println();
                    printUniqueBrands();
                    System.out.println();
                    System.out.print("\nEnter a brand name to search for: ");
                    String brandName = input.next();// TODO: error handling.
                    getComplaintsByBrands(brandName);
                } else if (userInput == 2) {
                    getAllComplaints();
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
        simulateNetworkLatency();
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
            printUniqueBrands();
            System.out.print("\nEnter another brand to search for: ");
            String choiceOfBrand = in.next();
            getComplaintsByBrands(choiceOfBrand);
        }
    }

    public static void getAllComplaints() {
        for (Car c : cars) {
            System.out.println(c);
        }
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

    public static void printUniqueBrands() {
        int iterator = 1;
        int carsLen = cars.size();
        String duplicated[] = new String[carsLen];
        String unique[] = new String[carsLen];
        int index = 0;

        for (int i = 0; i < carsLen; i++) {
            duplicated[i] = cars.get(i).getBrand();
        }

        Arrays.sort(duplicated);

        for (int i = 0; i < duplicated.length; i++) {
            while (i < carsLen - 1 && duplicated[i] == duplicated[i + 1]) {
                i++;
            }
            unique[index] = duplicated[i];
            index++;
        }

        for (int i = 0; i < index; i++) {
            System.out.println(iterator++ + ". " + unique[i] + " ");
        }
    }


    public static void simulateNetworkLatency() {
        try {
            System.out.println();
            for (int i = 0; i <= 10; i++) {
                System.out.print(".");
                Thread.sleep(250);
                if (i == 5){
                    System.out.print(" Fetching ");
                }
            }
            System.out.println("\n");
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
