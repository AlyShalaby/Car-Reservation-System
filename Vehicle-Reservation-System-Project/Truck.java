
/**
 The Truck class extends the Commercial class and represents a truck with additional attributes.
 * It provides methods to read truck data from a scanner, print truck details, 
 * and manage a list of truck specific attributes.
 *
 * @author (Aly Shalaby)
 * @version (05/04/2024)
 */
import java.util.Scanner;
import java.util.ArrayList;

public class Truck extends Commercial {
    private ArrayList<String> attributes;
    @Override
    public void readData(Scanner scanner) {
        super.readData(scanner); // Read Commercial fields
        this.attributes = new ArrayList<>();
        while (scanner.hasNext()) {
            String attribute = scanner.next().trim();
            if (!attribute.isEmpty()) {
                attributes.add(attribute);
            }
        }
    }

    @Override
    public void printDetails() {
        super.printDetails();
        System.out.println("Attributes: " + String.join(", ", attributes));
    }
}