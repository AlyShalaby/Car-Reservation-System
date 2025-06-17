
/**
 * The Commercial class is for commercial vehicles like vans and trucks. It has an extra detail for
 * how much weight it can carry (payload).
 * @author (Aly Shalaby)
 * @version (05/04/2025)
 */
import java.util.Scanner;
public class Commercial extends Vehicle {
    protected double payload;
    @Override
    public void readData(Scanner scanner) {
        super.readData(scanner); // Read Vehicle fields
        this.payload = Double.parseDouble(scanner.next().trim());
    }

    @Override
    public void printDetails() {
        super.printDetails();
        System.out.println("Payload: " + payload + " kg");
    }
}