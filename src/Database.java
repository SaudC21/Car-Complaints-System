public class Database {

    private static Database db;
    private final String fileName = "database.txt"; // Initializing file name

    private Database() {
    }

    public static Database getInstance() {
        // If no instance is made then make one
        if (db == null) {
            db = new Database();
        }
        // return instance that has been made or created
        return db;
    }

    public String getFileName() {
        return fileName;
    }

    @Override
    public String toString() {
        return "Database file name: " + fileName + " ..";
    }
}
