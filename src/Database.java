import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Database {

    private static Database db;
    private PrintWriter writer;
    private final String fileName = "Complaints.txt"; // Initializing file name

    private Database() {
        try {
            FileWriter fw = new FileWriter(fileName); // Make a file to write into
            writer = new PrintWriter(fw, true); // enable auto flush
        } catch (IOException ex) {
            ex.printStackTrace();
        }
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
        return "Database{" +
                " File name: " + fileName + '\'' +
                '}';
    }
}
