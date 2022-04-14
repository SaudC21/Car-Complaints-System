public class Car {

    private String brand;
    private String model;
    private int year;
    private int vehicleNumber;
    private String issue;
    private String solution;

    public Car(String brand, String model, int year, int vehicleNumber, String issue, String solution) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.vehicleNumber = vehicleNumber;
        this.issue = issue;
        this.solution = solution;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(int vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", vehicleNumber=" + vehicleNumber +
                ", issue='" + issue + '\'' +
                ", solution='" + solution + '\'' +
                '}';
    }
}