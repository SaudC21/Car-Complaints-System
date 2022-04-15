import java.io.PrintWriter;

public class Database {

    private static Database db;
    private final String fileName = "database.txt"; // Initializing file name

    public static Database getInstance() {
        if (db == null) { // If no instance is made then make one
            db = new Database();
        }
        return db; // return instance that has been made or created
    }

    public String getFileName() {
        return fileName;
    }


    @Override
    public String toString() {
        return "Database file name: " + fileName + " ..";
    }
}
