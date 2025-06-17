
/**
 * The Car class is for cars. It has extra details like body type, number of doors, and number of seats.
 * It is a type of Vehicle.
 * @author (Aly Shalaby)
 * @version (05/04/2025)
 */
import java.util.Scanner;
public class Car extends Vehicle {
    private String bodyType;
    private int noOfDoors;
    private int noOfSeats;

    @Override
    public void readData(Scanner scanner) {
        super.readData(scanner); // Read Vehicle fields
        this.bodyType = scanner.next().trim();
        this.noOfDoors = Integer.parseInt(scanner.next().trim());
        this.noOfSeats = Integer.parseInt(scanner.next().trim());
    }

    @Override
    public void printDetails() {
        super.printDetails();
        System.out.println("Body Type: " + bodyType);
        System.out.println("Doors: " + noOfDoors + "\tSeats: " + noOfSeats);
    }
}