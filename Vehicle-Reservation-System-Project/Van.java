
/**
 * The Van class is for vans. It has extra details like load volume and if it has a sliding side door.
 * It is a type of Commercial vehicle.
 * @author (Aly Shalaby)
 * @version (05/04/2025)
 */
import java.util.Scanner;
public class Van extends Commercial {
    private double loadVolume;
    private boolean slidingSideDoor;
    @Override
    public void readData(Scanner scanner) {
        super.readData(scanner); // Read Commercial fields
        this.loadVolume = Double.parseDouble(scanner.next().trim());
        this.slidingSideDoor = scanner.next().trim().equalsIgnoreCase("Yes");
    }

    @Override
    public void printDetails() {
        super.printDetails();
        System.out.println("Load Volume: " + loadVolume + " m³");
        System.out.println("Sliding Side Door: " + (slidingSideDoor ? "Yes" : "No"));
    }
}