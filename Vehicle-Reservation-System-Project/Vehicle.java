
/**
 * The Vehicle class is for all vehicles. It has details like group, ID, registration number, make, model,
 * and other info. It can read data from a file and show vehicle details.
 *
 * @author (Aly Shalaby)
 * @version (05/04/2025)
 */
import java.util.Scanner;
public class Vehicle {
    // instance variables
    private String group;
    private String vehID;
    private String regNo;
    private String make;
    private String model;
    private boolean airCon;
    private double engineSize;
    private String fuelType;
    private String gearbox;
    private String transmission;
    private int mileage;
    private String dateFirstRegistered;
    
    
    /**
     * Method to read data from a Scanner object
     */
    public void readData(Scanner scanner) {
        this.group = scanner.next().trim();
        this.vehID = scanner.next().trim();
        this.regNo = scanner.next().trim();
        this.make = scanner.next().trim();
        this.model = scanner.next().trim();
        this.airCon = scanner.next().trim().equalsIgnoreCase("Yes");
        this.engineSize = Double.parseDouble(scanner.next().trim());
        this.fuelType = scanner.next().trim();
        this.gearbox = scanner.next().trim();
        this.transmission = scanner.next().trim();
        this.mileage = Integer.parseInt(scanner.next().trim());
        this.dateFirstRegistered = scanner.next().trim();
    }
    // Print details
    public void printDetails() {
        System.out.println(make + " " + model + "\tGroup: " + group + "\tVehicle ID: " + vehID);
        System.out.println("Air conditioning/Climate Control: " + (airCon ? "Yes" : "No"));
        System.out.println("Engine Size: " + engineSize + "\tFuel: " + fuelType);
        System.out.println("Gearbox: " + gearbox + "\tTransmission: " + transmission);
        System.out.println("Mileage: " + mileage + "\tDate first registered: " + dateFirstRegistered);
    }
    
    public String getVehID() {
        return vehID;
    }
}
