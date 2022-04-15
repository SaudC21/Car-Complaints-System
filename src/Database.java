import java.io.PrintWriter;

public class Database {

    private static Database db;
    private final String fileName = "database.txt"; // Initializing file name
    private PrintWriter writer;

    private Database() {
//        try {
//            writer = new PrintWriter(fileName); // enable auto flush
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
    }

    public static Database getInstance() {
        if (db == null) { // If no instance is made then make one
            db = new Database();
        }
        return db; // return instance that has been made or created
    }

    public String getFileName() {
        return fileName;
    }

    public PrintWriter getWriter() {
        return writer;
    }

    @Override
    public String toString() {
        return "Database file name: " + fileName + " ..";
    }
}
